<template>
    <v-btn 
        @click="downloadPdf"
        class="mb-3" 
        color="warning"
    >Download PDF</v-btn>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import RemoteServices from '../../services/RemoteService';

const props = defineProps<{
    workflow: WorkflowDto
}>();

async function downloadPdf() {
    try {
        await RemoteServices.downloadWorkflowPdf(props.workflow.id);
    } catch (error) {
        console.error("Failed to download PDF", error);
    }
}
</script>