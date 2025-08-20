<template>
    <div class="d-flex align-center justify-center ga-2">
        <SelectJuriPresidentDialog v-if="props.workflow.status === 'APPROVED_BY_ADMIN'" :workflow="props.workflow" />
        <v-btn 
            v-else-if="props.workflow.status === 'DOCUMENT_SIGNED'"
            @click="changeStatus('JURI_PRESIDENT_ATTRIBUTED')"
            class="mb-3" 
            color="error"
        >Undo</v-btn>
        <FileDownload v-if="props.workflow.status === 'JURI_PRESIDENT_ATTRIBUTED'" :workflow="props.workflow"/>
        <FileUpload v-if="props.workflow.status === 'JURI_PRESIDENT_ATTRIBUTED'" :workflow="props.workflow"/>
        <v-btn 
            v-if="props.workflow.status === 'JURI_PRESIDENT_ATTRIBUTED'"
            @click="changeStatus('APPROVED_BY_ADMIN')"
            class="mb-3" 
            color="error"
        >Undo</v-btn>
    </div>
</template>

<script setup lang="ts">
import FileDownload from '../../../components/file/FileDownload.vue';
import FileUpload from '../../../components/file/FileUpload.vue';
import WorkflowDto from '../../../models/WorkflowDto';
import { useWorkflowStore } from '../../../stores/workflows';
import SelectJuriPresidentDialog from '../../dialogs/SelectJuriPresidentDialog.vue';

const props = defineProps<{
    workflow: WorkflowDto
}>();

const workflowStore = useWorkflowStore();

function changeStatus(status: string) {
    workflowStore.updateStatus(props.workflow, status);
}
</script>