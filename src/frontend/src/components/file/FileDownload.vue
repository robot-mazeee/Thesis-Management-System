<template>
    <v-btn icon color="secondary" elevation="2" class="rounded-circle" @click="downloadPdf">
        <v-icon>mdi-download</v-icon>
    </v-btn>
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