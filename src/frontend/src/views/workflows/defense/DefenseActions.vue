<template>
    <v-container v-if="props.defense.status === 'IN_REVISION' || props.defense.status === 'DEFENSE_SCHEDULED'">
        <v-text-field v-model="grade" label="Grade" aria-placeholder="grade"></v-text-field>
        <v-btn @click="gradeDefense()" color="secondary" class="mb-3">Submit</v-btn>
    </v-container>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import DefenseDto from '../../../models/DefenseDto';
import { useDefenseStore } from '../../../stores/defense';

const props = defineProps<{
    defense: DefenseDto
}>();

const defenseStore = useDefenseStore();
const grade = ref<number | null>();

async function gradeDefense() {
    defenseStore.gradeDefense(props.defense.id, grade.value);
}
</script>