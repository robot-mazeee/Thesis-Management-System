<!-- This is just a mockup of the StatisticsView.vue component -->
<!-- It is not meant to be your final implementation -->
<!-- You should replace this file with your own implementation -->
<!-- You are free to explore the available javascript libraries for charts and graphs -->
<!-- Some popular libraries are Chart.js, D3.js, vue-chartjs, ... -->
<!-- You can also use Vuetify components to enhance the visual appeal of your statistics -->

<template>
  <v-container>
    <v-row class="mb-6">
      <v-col cols="12">
        <h1 class="text-h4 text-center mb-4">PhD Thesis and Defenses Statistics</h1>
      </v-col>
    </v-row>

    <!-- Thesis Progress Section -->
    <v-row class="mb-6">
      <v-col cols="12">
        <h2 class="text-h5 mb-4">Thesis Progress</h2>
        <v-row>
          <v-col
            v-for="(count, step) in thesisProgress"
            :key="step"
            cols="12"
            sm="6"
            md="4"
            lg="3"
          >
            <v-card class="stat-card">
              <v-card-text class="text-center">
                <h3 class="text-h6">{{ step }}</h3>
                <p class="text-h4 font-weight-bold">{{ count }}</p>
                <p class="text-caption">students</p>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <!-- Defense Progress Section -->
    <v-row class="mb-6">
      <v-col cols="12">
        <h2 class="text-h5 mb-4">Defense Progress</h2>
        <v-row>
          <v-col
            v-for="(count, step) in defenseProgress"
            :key="step"
            cols="12"
            sm="6"
            md="4"
            lg="3"
          >
            <v-card class="stat-card">
              <v-card-text class="text-center">
                <h3 class="text-h6">{{ step }}</h3>
                <p class="text-h4 font-weight-bold">{{ count }}</p>
                <p class="text-caption">students</p>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
  
</template>

<script setup lang="ts">
import { ref } from 'vue'
import RemoteServices from '../../services/RemoteService';
import { onMounted } from 'vue';

// Mock data for thesis progress
const thesisProgress = ref({
  'Jury Proposal Submitted': 0,
  'Approved by SC': 0,
  'Jury President Assigned': 0,
  'Submitted to Fenix': 0,
  'Verified by PGA': 0
})

// Mock data for defense progress
const defenseProgress = ref({
  'Scheduled': 4,
  'Completed': 3,
  'Pending Review': 2
})

async function fetchThesisProgress() {
  thesisProgress.value = {
    'Jury Proposal Submitted': (await RemoteServices.getJuriProposals()).length,
    'Approved by SC': (await RemoteServices.getApprovedJuriProposals()).length,
    'Jury President Assigned': (await RemoteServices.juriPresidentSelectedWorkflows()).length,
    'Submitted to Fenix': (await RemoteServices.getWorkflowSubmissions()).length,
    'Verified by PGA': (await RemoteServices.getSignedDocuments()).length
  };
}

// Fetch data when the component is mounted
onMounted(fetchThesisProgress);

</script>

<style scoped>
.stat-card {
  transition: transform 0.2s ease-in-out;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>