<template>
    <v-text-field
        v-model="selectedDate"
        label="Select a date"
        type="date"
    />
    <v-btn @click="scheduleDefense()" color="warning" class="mb-3">Schedule</v-btn>
</template>

<script setup lang="ts">
import { useDefenseStore } from '../../../stores/defense';
import RemoteServices from '../../../services/RemoteService';
import { ref } from 'vue';
import DefenseDto from '../../../models/DefenseDto';
import PersonDto from '../../../models/PersonDto';

const props = defineProps<{
    student: PersonDto
}>();

const defenseStore = useDefenseStore();
const selectedDate = ref<string | null>();

async function scheduleDefense() {
    if (!selectedDate || !selectedDate.value) {
        alert("Please pick a date for the defense.");
        return;
    }

    try {
        const defense: DefenseDto = {
            date: selectedDate.value,
            student: props.student
        };
        const response = await RemoteServices.createDefense(defense);
        console.log("created defense: ", response);
        defenseStore.fetchDefenses();
    } catch (error) {
        console.error('Error creating defense: ', error);
    }
}
</script>