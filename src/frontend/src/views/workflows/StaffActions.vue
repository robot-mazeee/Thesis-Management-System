<template>
    <v-btn 
        v-if="props.workflow.status === 'DOCUMENT_SIGNED'" 
        @click="changeStatus('SUBMITTED_TO_FENIX')" 
        class="mb-3" 
        color="primary"
    >Submit</v-btn>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import { useWorkflowStore } from '../../stores/workflows';

const props = defineProps<{
    workflow: WorkflowDto
}>();

const workflowStore = useWorkflowStore();

function changeStatus(status: string) {
    workflowStore.updateStatus(props.workflow, status);
}
</script>