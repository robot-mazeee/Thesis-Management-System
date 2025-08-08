<template>
    <v-row align="center">
        <v-col>
            <h2 class="text-left ml-1">Juri Proposals</h2>
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
        :headers="juriProposalsHeaders"
        :items="juriProposals"
        :search="search"
        :loading="loading"
        :custom-filter="fuzzySearch"
        item-key="id"
        class="text-left"
        no-data-text="No proposals to present."
    >
        <template v-slot:[`item.juri_proposals`]="{ item }">
            <Workflow :workflow="item" />
        </template>
        <template v-slot:[`item.actions`]="{ item }">
            <v-btn v-if="checkStatus(item.status)" @click="updateStatus(item, 'JURI_PROPOSAL_SUBMITTED')" class="mb-3" color="primary">Undo</v-btn>
            <v-btn v-else @click="updateStatus(item, 'APPROVED_BY_ADMIN')" class="mb-3" color="primary">Approve</v-btn>
        </template>
    </v-data-table>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import WorkflowDto from '../../models/WorkflowDto'
import Workflow from '../../components/workflow/Workflow.vue'
import RemoteServices from '../../services/RemoteService'

let search = ref('')
let loading = ref(true)

const juriProposalsHeaders = [
    { title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
    {
        title: "Juri Proposals",
        key: "juri_proposals",
        value: "juri_proposals",
        sortable: false,
        filterable: false
    },
    {
        title: 'Actions',
        key: 'actions',
        value: 'actions',
        sortable: false,
        filterable: false
    }
]

const juriProposals: WorkflowDto[] = reactive([])

onMounted(() => {
    getWorkflows();
});

async function getWorkflows() { 
    juriProposals.splice(0, juriProposals.length)
    juriProposals.push(...(await RemoteServices.getWorkflows()))

    loading.value = false
}

function checkStatus(status: string) {
    console.log(status)
    switch(status) {
        case "APPROVED_BY_ADMIN":
            return true;
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
        console.log(proposal.status)
        const response = await RemoteServices.updateWorkflow(proposal);
        console.log("Server Response:", response);
        getWorkflows()
    } catch (error) {
        console.error("Approval failed:", error.response?.data || error.message);
    }
}

const fuzzySearch = (value: string, search: string) => {
    // Regex to match any character in between the search characters
    let searchRegex = new RegExp(search.split('').join('.*'), 'i')
    return searchRegex.test(value)
}
  
</script>