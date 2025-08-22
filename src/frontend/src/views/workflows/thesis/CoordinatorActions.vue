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
        <div v-if="props.workflow.status === 'SUBMITTED_TO_FENIX'">
            <v-text-field
                v-model="selectedDate"
                label="Select a date"
                type="date"
            />
            <v-btn @click="scheduleDefense()" color="warning" class="mb-3">Schedule</v-btn>
        </div>
    </div>
</template>

<script setup lang="ts">
import FileDownload from '../../../components/file/FileDownload.vue';
import FileUpload from '../../../components/file/FileUpload.vue';
import WorkflowDto from '../../../models/WorkflowDto';
import { useWorkflowStore } from '../../../stores/workflows';
import SelectJuriPresidentDialog from '../../dialogs/SelectJuriPresidentDialog.vue';
import { ref, computed } from 'vue';
import DefenseDto from '../../../models/DefenseDto';
import RemoteServices from '../../../services/RemoteService';

const props = defineProps<{
    workflow: WorkflowDto
}>();

const workflowStore = useWorkflowStore();
const selectedDate = ref<string | null>();

const formattedDate = computed(() => {
    return selectedDate.value ? new Date(selectedDate.value).toLocaleDateString() : "Pick a date...";
});

function changeStatus(status: string) {
    workflowStore.updateStatus(props.workflow, status);
}

async function scheduleDefense() {
    if (!selectedDate || !selectedDate.value) {
        alert("Please pick a date for the defense.");
        return;
    }

    try {
        const defense: DefenseDto = {
            date: selectedDate.value,
            student: props.workflow.student
        };
        const response = await RemoteServices.createDefense(defense);
        console.log("created defense: ", response);
    } catch (error) {
        console.error('Error creating defense: ', error);
    }
}
</script>