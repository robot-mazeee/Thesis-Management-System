<template>
  <v-timeline align="center" side="start" direction="horizontal" class="timeline">
    <v-timeline-item
      v-for="(event, index) in thesis_events"
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
const thesis_events = [
  { title: 'Juri Proposal Submitted', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Approved by SC', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Juri President Attributed', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Document Signed', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Submitted to Fenix', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
];

const defense_events = [
  { title: 'Defense Scheduled', date: '2025-01', color: 'primary', icon: 'mdi-flag' },
  { title: 'Under Review', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Graded', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
];
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
