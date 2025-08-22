<template>
    <v-container v-if="props.defense.status === 'CREATED'">
        <v-date-input :display-value="formattedDate" v-model="selectedDate" label="Select a date"></v-date-input>
        <v-btn @click="scheduleDefense()" color="warning" class="mb-3">Schedule</v-btn>
    </v-container>

    <v-container v-if="props.defense.status === 'IN_REVISION' || props.defense.status === 'DEFENSE_SCHEDULED'">
        <v-text-field v-model="grade" label="Grade" aria-placeholder="grade"></v-text-field>
        <v-btn @click="gradeDefense()" color="secondary" class="mb-3">Submit</v-btn>
    </v-container>
</template>

<script setup lang="ts">
import { defineProps, ref, computed } from 'vue';
import DefenseDto from '../../../models/DefenseDto';
import { useDefenseStore } from '../../../stores/defense';

const props = defineProps<{
    defense: DefenseDto
}>();

const defenseStore = useDefenseStore();
const grade = ref<number | null>();
const selectedDate = ref<string | null>();

const formattedDate = computed(() => {
    return selectedDate.value ? new Date(selectedDate.value).toLocaleDateString() : "Pick a date...";
});

async function gradeDefense() {
    defenseStore.gradeDefense(props.defense.id, grade.value);
}

async function scheduleDefense() {
    defenseStore.scheduleDefense(props.defense.id, selectedDate.value);
}
</script>