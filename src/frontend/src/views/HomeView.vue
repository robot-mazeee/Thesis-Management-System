<template>
  <div class="text-center">
    <LoginPage v-if="!studentStore.isLoggedIn" />
    <StudentHomeView v-else-if="roleStore.isStudent" />
    <SCHomeView v-else-if="roleStore.isAdmin" />
    <CoordinatorHomeView v-else-if="roleStore.isCoordinator" />
    <StaffHomeView v-else-if="roleStore.isStaff" />
    <p v-else>
      Page in Construction :).
    </p>
  </div>
</template>

<script setup lang="ts">
import LoginPage from './login/LoginPage.vue';
import CoordinatorHomeView from './coordinator/CoordinatorHomeView.vue';
import SCHomeView from './sc/SCHomeView.vue';
import StaffHomeView from './staff/StaffHomeView.vue';
import StudentHomeView from './student/StudentHomeView.vue';
import { useRoleStore } from '../stores/role';
import { useStudentStore } from '../stores/student';

const roleStore = useRoleStore();
const studentStore = useStudentStore();

const appName = import.meta.env.VITE_NAME;
</script>
