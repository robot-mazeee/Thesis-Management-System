<template>
    <v-container fluid class="p-6 w-full">
        <h1>Student Profile</h1>
        <div v-if="student" class="p-6 border rounded-xl shadow-lg w-full max-w-2xl bg-white mx-auto">
            <h3 class="text-gray-600">Name: {{ student.name }}</h3>
            <h3 class="text-gray-600">IST ID: {{ student.istId }}</h3>
            <h3 class="text-gray-600">Email: {{ student.email }} </h3>
            <EditPersonDialog :person-to-edit="student" :can-alter-type="false"/>
        </div>
        <div v-else>
            <p>Loading...</p>
        </div>
    </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useStudentStore } from '../../stores/student';
import RemoteService from '../../services/RemoteService';
import EditPersonDialog from '../dialogs/EditPersonDialog.vue';

const props = defineProps<{ studentId: Number }>();
const studentStore = useStudentStore();
let student = ref(null);

onMounted(async () => {
    if (props.studentId) {
        try {
            student.value = await RemoteService.getPerson(props.studentId);
        } catch (error) {
            console.error('Failed to fetch user data:', error);
        }
    }
    else if (studentStore.isLoggedIn) {
        student.value = studentStore.currentStudent;
    }
});
</script>