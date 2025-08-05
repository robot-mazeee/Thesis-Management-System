<template>
    <div v-if="workflows.length != 0" v-for="workflow in workflows">
        <Workflow :workflow="workflow" />
    </div>
    <div v-else>
        <v-col align="center">
            <h3>
                No Workflows Available.
            </h3>
        </v-col>
    </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import RemoteServices from '../../services/RemoteService';
import { onMounted } from 'vue';
import WorkflowDto from '../../models/WorkflowDto';
import Workflow from './Workflow.vue';

const workflows: WorkflowDto[] = reactive([])

onMounted(() => {
    getWorkflows();
});

async function getWorkflows() {
    workflows.splice(0, workflows.length)
    workflows.push(...(await RemoteServices.getWorkflows()))
}
</script>