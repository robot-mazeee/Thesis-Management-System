<template>
	<div class="text-center">
		<LoginPage v-if="!studentStore.isLoggedIn" />
		<StudentHomeView v-else-if="roleStore.isStudent" />
		<WorkflowsView v-else />
	</div>
</template>

<script setup lang="ts">
import LoginPage from './login/LoginPage.vue';
import StudentHomeView from './student/StudentHomeView.vue';
import { useRoleStore } from '../stores/role';
import { useStudentStore } from '../stores/student';
import WorkflowsView from './workflows/WorkflowsView.vue';

const roleStore = useRoleStore();
const studentStore = useStudentStore();

const appName = import.meta.env.VITE_NAME;
</script>
