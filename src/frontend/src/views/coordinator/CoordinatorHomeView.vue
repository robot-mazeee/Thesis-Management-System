<template>
    <v-row align="center">
      <v-col>
        <h2 class="text-left ml-1">{{ "Thesis Workflows"}}</h2>
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
        <v-btn v-if="checkStatus(item.status)" @click="updateStatus(item, 'APPROVED_BY_SC')" class="mb-3" color="primary">Undo</v-btn>
        <SelectPresidentDialog v-else :juriProposal="item"/>
      </template>
      <template v-slot:[`item.download`]>
        <v-btn href="/workflow.pdf" download>
          <v-icon>mdi-download</v-icon>
        </v-btn>
      </template>
      <template v-slot:[`item.upload`]="{ item }">
        <FileUpload :workflow="item" @file-uploaded="getWorkflows"/>
      </template>
  
    </v-data-table>

    <v-row align="center">
      <v-col>
        <h2 class="text-left ml-1">{{ "Defense Workflows"}}</h2>
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
      :headers="defensesHeaders"
      :items="defenses"
      :search="search"
      :loading="loading"
      :custom-filter="fuzzySearch"
      item-key="id"
      class="text-left"
      no-data-text="No workflows to present."
    >
      <template v-slot:[`item.status`]="{ item }">
        <v-chip :color="getColor(item.defenseStatus)">
          {{ item.defenseStatus}}
        </v-chip>
      </template>
      <template v-slot:[`item.schedule`]="{ item }">
        <v-container>
          <v-date-input :display-value="formattedDate" v-model="selectedDate" label="Select a date"></v-date-input>
          <v-btn @click="scheduleDefense(item)" color="warning" class="mb-3">Schedule</v-btn>
        </v-container>
      </template>
      <template v-slot:[`item.grade`] = "{ item }">
        <v-text-field v-model="grade" label="Label" aria-placeholder="grade"></v-text-field>
        <v-btn @click="gradeThesis(item)" color="secondary" class="mb-3">Submit</v-btn>
      </template>
  
    </v-data-table>
  
  </template>
  
<script setup lang="ts">
import RemoteServices from '../../services/RemoteService';
import { computed, onMounted, reactive, ref } from 'vue'
import WorkflowDto from '../../models/WorkflowDto'
import DefenseDto from '../../models/DefenseDto'
import SelectPresidentDialog from '../dialogs/SelectJuriPresidentDialog.vue';
import Workflow from '../../components/workflow/Workflow.vue';
import FileUpload from './FileUpload.vue';
import { getColor } from '../../mappings/workflowMappings';

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
      title: 'Select Juri President',
      key: 'actions',
      value: 'actions',
      sortable: false,
      filterable: false
    },
    {
      title: 'Download Document',
      key: 'download',
      value: 'download',
      sortable: false,
      filterable: false
    },
    {
        title: 'Upload Document',
        key: 'upload',
        value: 'upload',
        sortable: false,
        filterable: false
    }
]

const defensesHeaders = [
    { title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
    {
        title: 'Status',
        key: 'status',
        value: 'status',
        sortable: true,
        filterable: true
    },
    {
      title: 'Schedule Defense',
      key: 'schedule',
      value: 'schedule',
      sortable: false,
      filterable: false
    },
    {
      title: 'Grade',
      key: 'grade',
      value: 'grade',
      sortable: false,
      filterable: false
    },
    ]

    const workflows: WorkflowDto[] = reactive([])
    const defenses: DefenseDto[] = reactive([])
    let selectedDate = ref(null)
    let grade = ref(null)

    const currentDate = computed(() => new Date().toISOString().split('T')[0])

    const formattedDate = computed(() => {
      return selectedDate.value ? new Date(selectedDate.value).toLocaleDateString() : "Pick a date...";
    });

    onMounted(() => {
        getWorkflows();
        getDefenses();
    });

    async function getWorkflows() { 
        workflows.splice(0, workflows.length)
        workflows.push(...(await RemoteServices.getWorkflows()))

        loading.value = false
    }

    function checkStatus(status: string) {
      console.log(status)
      switch(status) {
          case "JURI_PRESIDENT_ATTRIBUTED":
              return true;
          case "DOCUMENT_SIGNED":
              return true;
          case "SUBMITTED_TO_FENIX":
              return true;
          default:
              return false;
      }
    }

    async function updateStatus(proposal: WorkflowDto, status: string) {
        console.log("Sending proposal for approval:", proposal);
        try {
            proposal.status = status
            const response = await RemoteServices.updateWorkflow(proposal);
            console.log("Server Response:", response);
            getWorkflows()
        } catch (error) {
            console.error("Approval failed:", error.response?.data || error.message);
        }
    }

    async function updateDefense(defense: DefenseDto, status: string) {
      console.log("Updating defense:", defense.id, defense.date, defense.grade);
      try {
          defense.defenseStatus = status
          console.log('NEW STATUS: ', defense.defenseStatus)
          const response = await RemoteServices.updateDefense(defense);
          console.log("Server Response:", response);
          getDefenses();
      } catch (error) {
          console.error("Workflow update failed:", error.response?.data || error.message);
      }
  }

    async function getDefenses() { 
      defenses.splice(0, defenses.length);
      const fetchedDefenses = await RemoteServices.getDefenses();
      console.log("Fetched Defenses:", fetchedDefenses); // Debugging
      defenses.push(...fetchedDefenses);
      loading.value = false;
    }

    function scheduleDefense(defense: DefenseDto) {
      if (!selectedDate.value) {
        alert("Please select a defense date.");
        return;
      }

      defense.date = selectedDate.value;
      const defenseTimestamp = new Date(selectedDate.value).getTime();
      const currentTimestamp = new Date(currentDate.value).getTime();

      const state = defenseTimestamp > currentTimestamp ? 'DEFENSE_SCHEDULED' : 'IN_REVISION';
      updateDefense(defense, state);
    }

    function gradeThesis(defense: DefenseDto) {
      if (grade.value === null) {
        alert("Please grade the defense.")
        return;
      }
      defense.grade = grade.value;
      updateDefense(defense, 'SUBMITTED_TO_FENIX')
    }

    const fuzzySearch = (value: string, search: string) => {
        // Regex to match any character in between the search characters
        let searchRegex = new RegExp(search.split('').join('.*'), 'i')
        return searchRegex.test(value)
    }

    import { VDateInput } from 'vuetify/labs/VDateInput'

</script>  