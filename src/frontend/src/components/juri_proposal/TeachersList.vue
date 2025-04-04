<template>
    <h1>Teachers List</h1>
    <div v-if="teachers.length != 0" v-for="teacher in teachers">
        <Teacher :teacher="teacher" @teacher-added="addTeacher(teacher)" @teacher-removed="removeTeacher(teacher)"/>
    </div>
    <div v-else>
        <v-col align="center">
            <h3>
                No Teachers Available.
            </h3>
        </v-col>
    </div>
    <v-row justify="center" align="center">
        <v-col cols="auto">
            <v-btn @click="goHome" color="success" class="mb-3">
                Go Home
            </v-btn>
        </v-col>

        <v-col cols="auto">
            <v-btn @click="proposeJuri" color="secondary" class="mb-3">
                Done
            </v-btn>
        </v-col>
    </v-row>
</template>

<script setup lang="ts">

import { reactive, ref } from 'vue'
import type PersonDto from '@/models/PersonDto'
import RemoteService from '@/services/RemoteService'
import Teacher from './Teacher.vue'
import { onMounted } from 'vue'
import WorkflowDto from '../../models/WorkflowDto'
import { useRouter } from 'vue-router'
import { useRoleStore } from '@/stores/role'
import { useStudentStore } from '../../stores/studentStore'

const roleStore = useRoleStore()
const studentStore = useStudentStore()
const router = useRouter()

const goHome = () => {
    if (roleStore.isStudent) {
        router.push("/")
    }
}

const teachers: PersonDto[] = reactive([])
const selectedTeachers: PersonDto[] = reactive([])

const newWorkflow = ref<WorkflowDto>({
    workflowStatus: "DEFAULT",
    teachers: [],
    student: studentStore.currentStudent
})

onMounted(() => {
    getTeachers();
});

function resetWorkflow() {
    newWorkflow.value = {
        workflowStatus: "DEFAULT",
        teachers: [],
        student: studentStore.currentStudent
    }
}

function addTeacher(teacher: PersonDto) {
    console.log(selectedTeachers)
    selectedTeachers.push(teacher);
}

function removeTeacher(teacher: PersonDto) {
    const index = selectedTeachers.findIndex(t => t.id === teacher.id);
    if (index !== -1) {
        selectedTeachers.splice(index, 1);
    }
}

async function getTeachers() {
    teachers.splice(0, teachers.length)
    teachers.push(...(await RemoteService.getTeachers()))
}

const proposeJuri = async () => {
    if (selectedTeachers.length === 0){
        alert("No Teachers Selected");
        return;
    }
    else if (selectedTeachers.length > 5){
        alert("Can only select up to 5 teachers.");
        return;
    }
    try {
        newWorkflow.value.teachers = selectedTeachers;
        newWorkflow.value.workflowStatus = 'JURI_PROPOSAL_SUBMITTED'
        console.log("Sending WorkflowDto:", JSON.stringify(newWorkflow.value, null, 2));
        await RemoteService.createWorkflow(newWorkflow.value);
        resetWorkflow()
        alert('Juri proposal sent!');
        goHome();
    } catch (error) {
        console.error('Error sending juri proposal:', error);
    }
}

</script>