<template>
    <v-btn 
        v-if="props.workflow.status === 'JURI_PROPOSAL_SUBMITTED'" 
        @click="changeStatus('APPROVED_BY_ADMIN')" 
        class="mb-3" 
        color="primary"
    >Approve</v-btn>
    <v-btn 
        v-if="props.workflow.status === 'JURI_PROPOSAL_SUBMITTED'" 
        @click="changeStatus('REJECTED')"
        class="mb-3" 
        color="secondary"
    >Reject</v-btn>
    <v-btn 
        v-if="props.workflow.status === 'APPROVED_BY_ADMIN'"
        @click="changeStatus('JURI_PROPOSAL_SUBMITTED')"
        class="mb-3" 
        color="error"
    >Undo</v-btn>
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