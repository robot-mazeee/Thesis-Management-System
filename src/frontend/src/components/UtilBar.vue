<template>
  <v-app-bar color="secondary" :height="36" class="px-2">
    <v-toolbar-items>
      <v-btn
        href="https://dei.tecnico.ulisboa.pt/"
        selected-class="no-active"
        class="dei-title"
        size="small"
      >
        DEI
      </v-btn>
    </v-toolbar-items>
    <v-spacer />
    <span><ChangeRoleDropdown /></span>
    <v-spacer />
    <v-toolbar-items class="align-center">
      <DarkModeSwitch />
    </v-toolbar-items>

    <v-toolbar-items class="ms-2">
      <v-btn size="small" variant="text" @click="logout()">
        Logout
        <v-icon size="small" class="ms-1" icon="mdi-logout"></v-icon>
      </v-btn>
    </v-toolbar-items>
  </v-app-bar>
</template>

<script setup lang="ts">
import DarkModeSwitch from './DarkModeSwitch.vue'
import { useStudentStore } from '../stores/studentStore'
import ChangeRoleDropdown from './ChangeRoleDropdown.vue'

const studentStore = useStudentStore()

function logout() {
  studentStore.logout()
  console.log(studentStore.isLoggedIn)
}
</script>

<style scoped>
.dei-title:hover {
  background-color: transparent !important;
}
</style>
