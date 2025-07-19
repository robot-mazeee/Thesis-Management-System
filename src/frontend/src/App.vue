<template>
  <v-layout id="app">
    <TopBar v-if="studentStore.isLoggedIn"/>
    <v-main
      style="margin-top: 96px"
      class="pa-10 d-flex flex-column flex-grow-1 flex-shrink-1 text-center"
    >
      <ErrorMessage />
      <LoadingOverlay />
      <RouterView />
    </v-main>
  </v-layout>
</template>

<script setup lang="ts">
import TopBar from './components/TopBar.vue';
import ErrorMessage from './components/ErrorMessage.vue';
import LoadingOverlay from './components/LoadingOverlay.vue';

import { RouterView } from 'vue-router'

import { useAppearanceStore } from './stores/appearance';
import { onMounted, onUnmounted } from 'vue';
import { useStudentStore } from './stores/student';

const appearanceStore = useAppearanceStore();
const studentStore = useStudentStore();

const setWidth = () => (appearanceStore.windowWidth = window.innerWidth);
setWidth();

onMounted(() => {
  window.addEventListener('resize', setWidth);
  appearanceStore.clearErrors();
  if (!studentStore.isStudentCreated)
    studentStore.createGenericStudent();
});

onUnmounted(() => {
  window.removeEventListener('resize', setWidth);
});
</script>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
}
</style>
