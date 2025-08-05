<template>
    <v-timeline align="center" side="start" direction="horizontal" class="timeline">
    <v-timeline-item
      v-for="(event, index) in props.thesisEvents"
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
      v-for="(event, index) in props.defenseEvents"
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

<script setup lang="ts">

type TimelineEvent = {
  title: string;
  date: string;
  color: string;
};

const props = defineProps<{
  defenseEvents: TimelineEvent[];
  thesisEvents: TimelineEvent[];
}>();

</script>