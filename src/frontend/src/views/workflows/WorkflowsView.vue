<template>
	<h1>Thesis Workflows</h1>
	<ThesisWorkflowsList />
	<h1>Defense Workflows</h1>
	<DefenseWorkflowsList />
</template>

<script setup lang="ts">
import DefenseWorkflowsList from './defense/DefenseWorkflowsList.vue';
import ThesisWorkflowsList from './thesis/ThesisWorkflowsList.vue';
</script>