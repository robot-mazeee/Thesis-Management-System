<template>
  <v-timeline align="center" side="start" direction="horizontal">
    <v-timeline-item
      v-for="(event, index) in events"
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
</template>

<script setup>
const events = [
  { title: 'Start', date: '2025-01', color: 'primary', icon: 'mdi-flag' },
  { title: 'Juri Proposal Submitted', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Approved by SC', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Juri President Attributed', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Document Signed', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
  { title: 'Submitted to Fenix', date: '2025-02', color: 'success', icon: 'mdi-code-tags' },
];
</script>

<style scoped>
.timeline-label {
  text-align: center;
  width: 100%;
  max-width: 120px;
  margin: 0 auto;
}
</style>

