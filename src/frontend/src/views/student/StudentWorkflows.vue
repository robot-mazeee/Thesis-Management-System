<template>
    <WorkflowTimeline :events="thesisEvents" />
    <WorkflowTimeline :events="defenseEvents" />
    <ProposeJuriDialog v-if="noWorkflow" @workflow-created="getStudentWorkflow" />
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import RemoteServices from '../../services/RemoteService';
import WorkflowDto from '../../models/WorkflowDto';
import { translateWorkflowStatusToIndex } from '../../mappings/workflowMappings';
import { translateDefenseStatusToIndex } from '../../mappings/defenseMappings';
import ProposeJuriDialog from '../dialogs/ProposeJuriDialog.vue';
import WorkflowTimeline from './WorkflowTimeline.vue';
import DefenseDto from '../../models/DefenseDto';

const props = defineProps<{
    studentId: number,
}>();

const thesisEvents = reactive([
    { title: 'Juri Proposal Submitted', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
    { title: 'Approved by Admin', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
    { title: 'Juri President Attributed', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
    { title: 'Document Signed', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
    { title: 'Submitted to Fenix', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
]);

const defenseEvents = reactive([
    { title: 'Defense Scheduled', date: '2025-01', color: 'grey', icon: 'mdi-flag' },
    { title: 'Under Review', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
    { title: 'Graded', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
]);

const workflow = reactive<WorkflowDto>({} as WorkflowDto);
const defense = reactive<DefenseDto>({} as DefenseDto);
const noWorkflow = ref(false);
const noDefense = ref(false);

onMounted(() => {
    getStudentDefense();
})

function updateTimeline() {
    const workflowStatus = workflow.status;
    const workflowIndex = translateWorkflowStatusToIndex(workflowStatus);
    getDotColors(workflowIndex, thesisEvents);

    const defenseStatus = defense.status;
    const defenseIndex = translateDefenseStatusToIndex(defenseStatus);
    getDotColors(defenseIndex, defenseEvents);
}

async function getStudentWorkflow() {
    console.log("Getting student workflow!");
    try {
        const response = await RemoteServices.getWorkflowByStudent(props.studentId);
        console.log("student workflow: ", response);
        if (!response) {
            noWorkflow.value = true;
            return;
        }
        noWorkflow.value = false;
        Object.assign(workflow, response);
        updateTimeline();
    } catch (error) {
        console.error("Error getting student workflow: ", error);
    }
}

async function getStudentDefense() {
    console.log("Getting student defense!");
    try {
        const response = await RemoteServices.getDefenseByStudent(props.studentId);
        console.log("student defense: ", response);
        if (!response) {
            noDefense.value = true;
            return;
        }
        noDefense.value = false;
        Object.assign(defense, response);
        updateTimeline();
    } catch (error) {
        console.error("Error getting student defense: ", error);
    }
}

function getDotColors(index: number, events: Array<any>) {
    for (let i = 0; i < events.length; i++) {
        if (i <= index) {
            events[i].color = 'success';
        } else {
            break;
        }
    }
}

onMounted(async () => {
    await getStudentWorkflow();
    if (noWorkflow.value) {
        console.log('No workflow found');
        return;
    }
    updateTimeline();
});
</script>

<style scoped>
.timeline-label {
    text-align: center;
    width: 100%;
    max-width: 200px;
    margin: 0 auto;
}

.timeline {
    margin-top: 30px;
}

::v-deep(.v-timeline-item__opposite),
::v-deep(.v-timeline-item__body) {
    padding-top: 4px !important;
    padding-bottom: 4px !important;
}

::v-deep(.v-timeline-item__inner) {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
}

button {
    margin-top: 50px;
    margin-bottom: 30px;
}
</style>
