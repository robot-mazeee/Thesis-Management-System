<template>
  <div class="text-center">
    <h1>Welcome to DEI-PMS!</h1>

    <LoginPage v-if="!studentStore.isLoggedIn && roleStore.isStudent"/>
    
    <StudentHomeView v-else-if="roleStore.isStudent" />
    <SCHomeView v-else-if="roleStore.isSc" />
    <CoordinatorHomeView v-else-if="roleStore.isCoordinator" />
    <StaffHomeView v-else-if="roleStore.isStaff" />
    <p v-else>
      Page in Construction :)
    </p>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import LoginPage from '../components/student/LoginPage.vue';
import CoordinatorHomeView from './coordinator/CoordinatorHomeView.vue';
import SCHomeView from './sc/SCHomeView.vue';
import StaffHomeView from './staff/StaffHomeView.vue';
import StudentHomeView from './student/StudentHomeView.vue';
import { useRoleStore } from '@/stores/role'
import { useStudentStore  } from '@/stores/studentStore'

const roleStore = useRoleStore()
const studentStore = useStudentStore()

onMounted(() => {
    studentStore.logout();
});

const appName = import.meta.env.VITE_NAME;
</script>
