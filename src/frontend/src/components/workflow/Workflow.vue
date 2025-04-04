<template>
    <h3>
        Student: {{ props.workflow.student.name }}
        <v-divider></v-divider>
        {{ props.workflow.student.istId }}
        <v-divider></v-divider>
        <v-chip :color="getColor(props.workflow.workflowStatus)">
            {{ props.workflow.workflowStatus }}
        </v-chip>
    </h3>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';

const props = defineProps<{ workflow: WorkflowDto }>();

const getColor = (status: string) => {
    console.log(status)
    switch(status) {
        case "JURI_PROPOSAL_SUBMITTED":
            return 'pink';
        case "APPROVED_BY_SC":
            return 'blue';
        case "JURI_PRESIDENT_ATTRIBUTED":
            return 'red';
        case "DOCUMENT_SIGNED":
            return 'green';
        case "SUBMITTED_TO_FENIX":
            return 'yellow';
        default:
            return 'red';
    }
}

</script>