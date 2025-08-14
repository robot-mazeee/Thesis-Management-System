<template>
    <SelectJuriPresidentDialog v-if="props.workflow.status === 'APPROVED_BY_ADMIN'" :workflow="props.workflow" />
    <v-btn 
        v-else-if="props.workflow.status === 'JURI_PRESIDENT_ATTRIBUTED'"
        @click="changeStatus('APPROVED_BY_ADMIN')"
        class="mb-3" 
        color="error"
    >Undo</v-btn>
    <FileUpload v-if="props.workflow.status === 'JURI_PRESIDENT_ATTRIBUTED'" :workflow="props.workflow"/>
</template>

<script setup lang="ts">
import FileUpload from '../../components/files/FileUpload.vue';
import WorkflowDto from '../../models/WorkflowDto';
import { useWorkflowStore } from '../../stores/workflows';
import SelectJuriPresidentDialog from '../dialogs/SelectJuriPresidentDialog.vue';

const props = defineProps<{
    workflow: WorkflowDto
}>();

const workflowStore = useWorkflowStore();

function changeStatus(status: string) {
    workflowStore.updateStatus(props.workflow, status);
}
</script>