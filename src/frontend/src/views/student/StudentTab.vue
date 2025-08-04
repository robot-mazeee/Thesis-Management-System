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
              <v-col><EditPersonDialog :person-to-edit="props.student" :can-alter-type="false" @close-dialog="updateStudentInfo"/></v-col>
            </v-row>
          </v-window-item>

          <v-window-item value="workflows">
            <div>
              <StudentWorkflows :student-id="props.student.id"/>
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
import StudentWorkflows from './StudentWorkflows.vue';
import { useStudentStore } from '../../stores/student';

const activeTab = ref('profile')

const emit = defineEmits(['fetch-student']);
const studentStore = useStudentStore();

const props = defineProps<{
  student: PersonDto,
}>()

function updateStudentInfo() {
  if (studentStore.isLoggedIn) {
    studentStore.updateCurrentStudent(props.student.id);
    console.log("current student: ", studentStore.currentStudent);
  }
  emit('fetch-student');
}

console.log(props.student);
</script>
