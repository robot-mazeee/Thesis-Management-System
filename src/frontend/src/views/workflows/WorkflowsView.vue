<template>
    <h1>Workflows</h1>
    <!-- <ThesisWorkflow :workflow="null"></ThesisWorkflow> -->
     <WorkflowsList />
</template>

<script setup lang="ts">

import ThesisWorkflow from '../../components/workflow/ThesisWorkflow.vue';
import WorkflowsList from '../../components/workflow/WorkflowsList.vue';

</script>