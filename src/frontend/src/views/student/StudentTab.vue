<template>
  <v-container>
  <v-row justify="center">
    <v-col cols="12">
      <v-card class="pa-4" elevation="4">
        <!-- Tabs -->
        <v-tabs v-model="activeTab" background-color="primary" dark>
          <v-tab value="profile">Profile</v-tab>
          <v-tab value="workflows">Workflows</v-tab>
        </v-tabs>

        <!-- Tab Content -->
        <v-window v-if="props.student" v-model="activeTab" class="mt-4">
          <v-window-item value="profile">
            <v-row>
              <v-col>Name</v-col>
              <v-col><span class="ma-0">{{ props.student.name }}</span></v-col>
            </v-row>
            <v-row>
              <v-col>Email</v-col>
              <v-col><span class="ma-0">{{ props.student.email }}</span></v-col>
            </v-row>
            <v-row>
              <v-col>IST ID</v-col>
              <v-col><span class="ma-0">{{ props.student.istId }}</span></v-col>
            </v-row>
            <v-row>
              <v-col><EditPersonDialog :person-to-edit="props.student" :can-alter-type="false"/></v-col>
            </v-row>
          </v-window-item>

          <v-window-item value="workflows">
            <div>
              <!-- <p v-for="(workflow, index) in props.student.workflows" :key="index">
                {{ workflow }}
              </p> -->
            </div>
          </v-window-item>
        </v-window>
      </v-card>
    </v-col>
  </v-row>
</v-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import EditPersonDialog from '../dialogs/EditPersonDialog.vue'
import PersonDto from '../../models/PersonDto'

const activeTab = ref('profile')

const props = defineProps<{
  student: PersonDto,
}>()
</script>
