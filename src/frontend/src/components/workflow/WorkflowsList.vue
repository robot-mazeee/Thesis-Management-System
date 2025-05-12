<template>
    <h1>Workflows</h1>
    <div v-if="workflows.length != 0" v-for="workflow in workflows">
        <Workflow :workflow="workflow" />
        <v-btn @click="props.changeWorkflowStatus" color="secondary" class="mb-3">
            {{ getButtonText() }}
        </v-btn>
    </div>
    <div v-else>
        <v-col align="center">
            <h3>
                No Workflows Available.
            </h3>
        </v-col>
    </div>
    <v-row justify="center" align="center">
        <v-col cols="auto">
            <v-btn @click="goHome" color="success" class="mb-3">
                Go Home
            </v-btn>
        </v-col>

        <v-col cols="auto">
            <!-- <v-btn @click="proposeJuri" color="secondary" class="mb-3">
                Done
            </v-btn> -->
        </v-col>
    </v-row>
</template>

<script setup lang="ts">

import { reactive, ref } from 'vue'
import RemoteService from '@/services/RemoteService'
import { onMounted } from 'vue'
import WorkflowDto from '../../models/WorkflowDto'
import { useRouter } from 'vue-router'
import { useRoleStore } from '@/stores/role'
import Workflow from '@/Workflow.vue'
import ThesisWorkflow from './ThesisWorkflow.vue'

const roleStore = useRoleStore()
const router = useRouter()

const goHome = () => {
    if (roleStore.isStudent) {
        router.push("/")
    }
}

const workflows: WorkflowDto[] = reactive([])

onMounted(() => {
    getWorkflows();
});

async function getWorkflows() {
    workflows.splice(0, workflows.length)
    workflows.push(...(await RemoteService.getWorkflows()))
}

// const proposeJuri = async () => {
//     // newWorkflow.value.workflowStatus = statusMappings[newWorkflow.value.workflowStatus as keyof typeof statusMappings]
    
//     if (selectedTeachers.length === 0){
//         alert("No Teachers Selected");
//         return;
//     }
//     else if (selectedTeachers.length > 5){
//         alert("Can only select up to 5 teachers.");
//         return;
//     }
//     try {
//         newWorkflow.value.teachers = selectedTeachers;
//         const response = await RemoteService.proposeJuri(newWorkflow.value);
//         resetWorkflow()
//         console.log('Juri proposal sent:', response);
//         goHome();
//     } catch (error) {
//         console.error('Error sending juri proposal:', error);
//     }
// }

</script>