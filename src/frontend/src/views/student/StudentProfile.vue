<template>
  <v-container fluid class="p-6 w-full">
    <StudentTab v-if="ready" :student="student" />
  </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useStudentStore } from '../../stores/student';
import RemoteService from '../../services/RemoteService';
import StudentTab from './StudentTab.vue';
import PersonDto from '../../models/PersonDto';

const props = defineProps<{ studentId: number | null }>();
const studentStore = useStudentStore();

const student = ref<PersonDto | null>(null);
const ready = ref(false);

onMounted(async () => {
  console.log("Fetching student")
    if (props.studentId === null || studentStore.isLoggedIn) {
        student.value = studentStore.currentStudent;
        console.log("STUDENT", student.value)
    } else {
        try {
            student.value = await RemoteService.getPerson(props.studentId);
            console.log(student.value)
        } catch (error) {
            console.error('Failed to fetch user data:', error);
        }
    }

    if (student.value) {
        ready.value = true;
    }
});

</script>
