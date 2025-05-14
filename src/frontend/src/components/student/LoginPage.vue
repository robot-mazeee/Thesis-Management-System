<template>
    <h1></h1>
    <div>
         <PersonDialog />
         <v-btn>Create Account</v-btn>
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

// se nao houver a pessoa na base de dados, criar uma nova pessoa com os dados
// nome, istID, email, tipo

async function getStudents() {
    students.splice(0, students.length)
    students.push(...(await RemoteServices.getStudents()))
}

function selectStudent(student: PersonDto) {
    roleStore.login(student);
}

</script>