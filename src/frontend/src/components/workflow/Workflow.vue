<template>
    <h3>
        Student: {{ student.name }}
        <v-divider></v-divider>
        {{ student.istId }}
        <v-divider></v-divider>
        <v-chip :color="getColor(props.workflow.status)">
            {{ props.workflow.status }}
        </v-chip>
    </h3>
</template>

<script setup lang="ts">
import { onMounted, reactive } from 'vue';
import PersonDto from '../../models/PersonDto';
import WorkflowDto from '../../models/WorkflowDto';
import RemoteServices from '../../services/RemoteService';

const props = defineProps<{ workflow: WorkflowDto }>();
const student = reactive<PersonDto>({} as PersonDto);

onMounted(() => {
    getStudent();
})

async function getStudent() {
    const studentId = props.workflow.studentId;
    try {
        console.log("Fetching student/workflow owner");
        const result = await RemoteServices.getPerson(studentId);

        Object.assign(student, result);
        console.log("Fetched student: ", student);
    } catch (error) {
        console.log("Error fetching student/workflow owner: ", error);
    }
}

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