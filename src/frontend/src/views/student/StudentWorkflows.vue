<template>
  <v-timeline align="center" side="start" direction="horizontal" class="timeline">
    <v-timeline-item
      v-for="(event, index) in thesisEvents"
      :key="index"
      :dot-color="event.color"
      align="center"
    >
      <template #opposite>
        <div class="timeline-label">
          {{ event.date }}
        </div>
      </template>

      <template #default>
        <div class="timeline-label">
          {{ event.title }}
        </div>
      </template>
    </v-timeline-item>
  </v-timeline>
  <br>
  <v-divider></v-divider>
  <v-timeline align="center" side="start" direction="horizontal" class="timeline">
    <v-timeline-item
      v-for="(event, index) in defense_events"
      :key="index"
      :dot-color="event.color"
      align="center"
    >
      <template #opposite>
        <div class="timeline-label">
          {{ event.date }}
        </div>
      </template>

      <template #default>
        <div class="timeline-label">
          {{ event.title }}
        </div>
      </template>
    </v-timeline-item>
  </v-timeline>
  <v-btn>Start Thesis Workflow</v-btn>
</template>

<script setup lang="ts">
import { onMounted, reactive } from 'vue';
import RemoteServices from '../../services/RemoteService';
import WorkflowDto from '../../models/WorkflowDto';
import { translateStatusToIndex } from '../../mappings/workflowMappings';

const props = defineProps<{
  studentId: number,
}>()

const thesisEvents = [
  { title: 'Juri Proposal Submitted', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
  { title: 'Approved by SC', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
  { title: 'Juri President Attributed', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
  { title: 'Document Signed', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
  { title: 'Submitted to Fenix', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
];

const defense_events = [
  { title: 'Defense Scheduled', date: '2025-01', color: 'grey', icon: 'mdi-flag' },
  { title: 'Under Review', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
  { title: 'Graded', date: '2025-02', color: 'grey', icon: 'mdi-code-tags' },
];

const workflow = reactive<WorkflowDto>({} as WorkflowDto);

async function getStudentWorkflow() {
  console.log("Getting student workflow!");
  try {
    const response = await RemoteServices.getWorkflowByStudent(props.studentId);
    Object.assign(workflow, response);
  } catch (error) {
    console.error("Error getting student workflow: ", error);
  }
}

function getDotColors(index) {
  let counter = -1;
  thesisEvents.forEach(event => {
    counter += 1
    if (counter <= index)
      event.color = 'success';
    else return;
  });
}

onMounted(async () => {
  await getStudentWorkflow(); 
  const workflowStatus = workflow.workflowStatus;
  const index = translateStatusToIndex[workflowStatus];
  getDotColors(index);
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
