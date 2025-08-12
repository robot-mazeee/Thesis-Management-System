<template>
    <SelectJuriPresidentDialog 
        v-if="props.workflow.status === 'APPROVED_BY_ADMIN'" 
        :workflow="props.workflow" 
    />
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import SelectJuriPresidentDialog from '../dialogs/SelectJuriPresidentDialog.vue';

const props = defineProps<{
    workflow: WorkflowDto
}>();
</script>