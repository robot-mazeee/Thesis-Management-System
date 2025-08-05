<template>
  <v-app-bar color="secondary" :height="36" class="px-2">
    <v-container fluid class="d-flex align-center justify-space-between">
      <div class="d-flex align-center">
        <v-btn
          href="https://dei.tecnico.ulisboa.pt/"
          selected-class="no-active"
          class="dei-title"
        >
          DEI
        </v-btn>
      </div>
      <v-spacer />
      <div class="d-flex align-center justify-center" style="flex: 1">
        <ChangeRoleDropdown />
      </div>
      <v-spacer />
      <!-- <v-toolbar-items class="align-center">
        <DarkModeSwitch />
      </v-toolbar-items> -->

      <div class="d-flex align-center">
          <v-btn variant="text" @click="logout()">
            Logout
            <v-icon class="ms-1" icon="mdi-logout"></v-icon>
          </v-btn>
      </div>
    </v-container>
  </v-app-bar>
</template>

<script setup lang="ts">
import DarkModeSwitch from './DarkModeSwitch.vue'
import { useStudentStore } from '../stores/student'
import ChangeRoleDropdown from './ChangeRoleDropdown.vue'
import router from '../router'

const studentStore = useStudentStore()

function logout() {
  studentStore.logout()
  router.push("/")
  console.log("Is logged in? ", studentStore.isLoggedIn, "generic student created? ", studentStore.isStudentCreated);
}
</script>

<style scoped>
.dei-title:hover {
  background-color: transparent !important;
}
</style>
