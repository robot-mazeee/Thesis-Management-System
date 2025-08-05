<template>
  <v-row align="center">
    <v-col>
      <h2 class="text-left ml-1">{{ "Worklows" }}</h2>
    </v-col>
  </v-row>

  <v-text-field
    v-model="search"
    label="Search"
    prepend-inner-icon="mdi-magnify"
    variant="outlined"
    hide-details
    single-line
  ></v-text-field>

  <v-data-table
    :headers="workflowsHeaders"
    :items="workflows"
    :search="search"
    :loading="loading"
    :custom-filter="fuzzySearch"
    item-key="id"
    class="text-left"
    no-data-text="No workflows to present."
  >
    <template v-slot:[`item.workflows`]="{ item }">
        <Workflow :workflow="item"/>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-btn @click="updateStatus(item, 'SUBMITTED_TO_FENIX')" class="mb-3" color="secondary">Submit</v-btn>
    </template>

  </v-data-table>

</template>

<script setup lang="ts">
import RemoteServices from '../../services/RemoteService';
import { onMounted, reactive, ref } from 'vue'
import WorkflowDto from '../../models/WorkflowDto'
import Workflow from '../../components/workflow/Workflow.vue';

let search = ref('')
let loading = ref(true)

const workflowsHeaders = [
  { title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
  {
      title: 'Workflows',
      key: 'workflows',
      value: 'workflows',
      sortable: true,
      filterable: true
  },
  {
      title: 'Submit To Fenix',
      key: 'actions',
      value: 'actions',
      sortable: false,
      filterable: false
  },
  ]

  const workflows: WorkflowDto[] = reactive([])

  onMounted(() => {
      getWorkflows();
  });

  async function getWorkflows() { 
        workflows.splice(0, workflows.length)
        workflows.push(...(await RemoteServices.getWorkflows()))

        loading.value = false
  }

  async function updateStatus(proposal: WorkflowDto, status: string) {
      console.log("Sending proposal for approval:", proposal);
      try {
          proposal.status = status
          const response = await RemoteServices.updateWorkflow(proposal);
          console.log("Server Response:", response);
          try {
            await RemoteServices.createDefense();
          } catch(error) {
            console.error("Workflow creation failed:", error.response?.data || error.message);
          }
          getWorkflows();
      } catch (error) {
          console.error("Workflow update failed:", error.response?.data || error.message);
      }
  }
    
  const fuzzySearch = (value: string, search: string) => {
      // Regex to match any character in between the search characters
      let searchRegex = new RegExp(search.split('').join('.*'), 'i')
      return searchRegex.test(value)
  }

</script>  