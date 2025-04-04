<template>
    <div :class="['teacher', { added }]">
        <h3>
            {{ teacher.name }}   {{ teacher.istId }}
            {{ teacher.email }}
            <v-btn @click="toggleAdded()" :color="added ? 'danger' : 'success'" class="mb-3">
                {{ added ? "Unselect" : "Select" }}
            </v-btn>
        </h3>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import type PersonDto from '@/models/PersonDto';

defineProps<{ teacher: PersonDto }>();

const added = ref(false);
const emit = defineEmits(['teacher-added', 'teacher-removed'])

function toggleAdded() {
    added.value = !added.value;
    if (added.value) {
      emit('teacher-added')
    } else emit('teacher-removed')
}
</script>

<style>
.teacher {
  background: #f4f4f4;
  margin: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

.teacher.added {
  border-left: 5px solid green;
}

.teacher h3 {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
