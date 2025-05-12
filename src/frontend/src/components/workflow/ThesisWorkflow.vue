<template>
    <h3>
        <div>
            student_name_link
            <v-chip :color="getColor('JURI_PROPOSAL_SUBMITTED')">
                Juri Proposal Submitted
            </v-chip>
        </div>
        <v-divider />
        <div>
            <v-btn @click="viewJuri()" color="secondary" class="mb-3">
                View Juri
            </v-btn>

            <v-btn v-if="roleStore.isCoordinator" @click="props.changeWorkflowStatus" color="secondary" class="mb-3">
                View Juri
            </v-btn>
        </div>
    </h3>
</template>

<script setup lang="ts">
import WorkflowDto from '../../models/WorkflowDto';
import { useRoleStore } from '../../stores/role';

const props = defineProps<{ workflow: WorkflowDto }>();
const roleStore = useRoleStore();

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

function viewJuri() {
    const juri_president = props.workflow.juriPresident;
    console.log(juri_president);
}

</script>

<style>
h3 {
    width: 50%;
}
</style>