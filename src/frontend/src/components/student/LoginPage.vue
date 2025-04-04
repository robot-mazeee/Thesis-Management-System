<template>
    <h1>Login as:</h1>
    <div>
        <div v-if="students.length !== 0" v-for="student in students">
            <v-btn @click="selectStudent(student)" color="success" class="mb-3">
                {{ student.name }} ({{ student.istId }})
            </v-btn>
        </div>
        <h3 v-else>
            No Students.
        </h3 v-else>
    </div>
</template>

<script setup lang='ts'>
import { useStudentStore } from '@/stores/studentStore'
import PersonDto from '../../models/PersonDto';
import { onMounted, reactive } from 'vue';
import RemoteServices from '../../services/RemoteService';

const roleStore = useStudentStore()

const students: PersonDto[] = reactive([])

onMounted(() => {
    getStudents();
});

async function getStudents() {
    students.splice(0, students.length)
    students.push(...(await RemoteServices.getStudents()))
}

function selectStudent(student: PersonDto) {
    roleStore.login(student);
}

</script>