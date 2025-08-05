<template>
  <h3 v-if="noWorkflow">No Workflow Initiated</h3>
  <WorkflowTimeline :thesis-events="thesisEvents" :defense-events="defenseEvents"/>
  <ProposeJuriDialog v-if="noWorkflow" @workflow-created="getStudentWorkflow"/>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import RemoteServices from '../../services/RemoteService';
import WorkflowDto from '../../models/WorkflowDto';
import { translateStatusToIndex } from '../../mappings/workflowMappings';
import ProposeJuriDialog from '../dialogs/ProposeJuriDialog.vue';
import WorkflowTimeline from './WorkflowTimeline.vue';

const props = defineProps<{
  studentId: number,
}>()

const noWorkflow = ref(false);

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

function updateTimeline() {
  const workflowStatus = workflow.status;
  const index = translateStatusToIndex(workflowStatus);
  getDotColors(index);
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

function getDotColors(index: number) {
  let counter = -1;
  thesisEvents.forEach(event => {
    counter += 1;
    if (counter <= index)
      event.color = 'success';
    else {
      return;
    }
  });
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
