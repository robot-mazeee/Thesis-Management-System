<template>
    <v-container fluid class="p-6 w-full">
        <StudentTab v-if="student.value" :student="student.value"/>
    </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useStudentStore } from '../../stores/student';
import RemoteService from '../../services/RemoteService';
import StudentTab from './StudentTab.vue';

const props = defineProps<{ studentId: number }>();
const studentStore = useStudentStore();
let student = ref(null);

onMounted(async () => {
    // Get student through id or show current logged in student
    if (props.studentId !== null) {
        try {
            student.value = await RemoteService.getPerson(props.studentId);
        } catch (error) {
            console.error('Failed to fetch user data:', error);
        }
    }
    else if (studentStore.isLoggedIn) {
        student.value = studentStore.currentStudent;
    }
    else {
        console.error('Could not fetch student')
    }
});
</script>