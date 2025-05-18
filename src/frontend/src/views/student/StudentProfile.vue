<template>
    <h1>Student Profile</h1>
    <div class="p-4 border rounded-lg shadow-md w-80 bg-white" v-if="student">
        <h3 class="text-gray-600">Name: {{ student.name }}</h3>
        <h3 class="text-gray-600">IST ID: {{ student.istId }}</h3>
        <h3 class="text-gray-600">Email: {{ student.email }} </h3>
    </div>
    <div v-else>
        <p>Loading...</p>
    </div>
    <v-divider></v-divider>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import RemoteService from '../../services/RemoteService';
import PersonDto from '../../models/PersonDto';

const route = useRoute();
const studentId = Number(route.params.id);
console.log(route.params);
// const student = ref<PersonDto | null>(null);

const student = ref<PersonDto>({
	name: 'John Doe',
	istId: '110283',
	email: 'johndoe@tecnico.ulisboa.pt',
	type: 'STUDENT'
});

onMounted(async () => {
    try {
        // student.value = await RemoteService.getPerson(studentId);
    } catch (error) {
        console.error('Failed to fetch user data:', error);
    }
});
</script>