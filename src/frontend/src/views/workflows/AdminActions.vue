<template>
    <v-btn 
        v-if="props.workflow.status === 'JURI_PROPOSAL_SUBMITTED'" 
        @click="updateStatus(props.workflow, 'APPROVED_BY_ADMIN')" 
        class="mb-3" 
        color="primary"
    >Approve</v-btn>
    <v-btn 
        v-if="props.workflow.status === 'JURI_PROPOSAL_SUBMITTED'" 
        @click="updateStatus(props.workflow, 'REJECTED')"
        class="mb-3" 
        color="secondary"
    >Reject</v-btn>
    <v-btn 
        v-if="props.workflow.status === 'APPROVED_BY_ADMIN'"
        @click="updateStatus(props.workflow, 'JURI_PROPOSAL_SUBMITTED')"
        class="mb-3" 
        color="error"
    >Undo</v-btn>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import { updateStatus } from './WorkflowActions';

const props = defineProps<{
    workflow: WorkflowDto
}>();
</script>