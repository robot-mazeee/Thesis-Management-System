<template>
    <ViewJuriDialog 
        v-if="props.workflow.status === 'JURI_PROPOSAL_SUBMITTED'"
        :workflow="props.workflow" 
        @approved="changeStatus('APPROVED_BY_ADMIN')"
        @rejected="changeStatus('REJECTED')"
    />
    <v-btn 
        v-if="props.workflow.status === 'APPROVED_BY_ADMIN' || props.workflow.status === 'REJECTED'"
        @click="changeStatus('JURI_PROPOSAL_SUBMITTED')"
        class="mb-3" 
        color="error"
    >Undo</v-btn>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import { useWorkflowStore } from '../../stores/workflows';
import ViewJuriDialog from '../dialogs/ViewJuriDialog.vue';

const props = defineProps<{
    workflow: WorkflowDto
}>();

const workflowStore = useWorkflowStore();

function changeStatus(status: string) {
    workflowStore.updateStatus(props.workflow, status);
}
</script>