<template>
	<h1>Workflows</h1>
	<h3 v-if="workflows.length === 0">No workflows.</h3>
	<v-container>
		<v-row>
			<v-col
				v-for="workflow in workflows"
				:key="workflow.id"
				cols="12"
				sm="6"
				md="4"
			>
				<v-card>
					<v-card-title>
						{{ workflow.student.name }} ({{ workflow.student.istId }})
					</v-card-title>

					<v-card-subtitle>
						<v-chip :color="getColor(workflow.status)">
							{{ workflow.status }}
						</v-chip>
					</v-card-subtitle>

					<v-card-text>
						Juri President: {{ workflow.juriPresident !== null ? workflow.juriPresident.name : "Not Assigned"}}
					</v-card-text>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import WorkflowDto from '../../models/WorkflowDto';
import RemoteServices from '../../services/RemoteService';
import { getColor } from '../../mappings/workflowMappings';

const workflows = ref<WorkflowDto[]>([]);

onMounted(() => {
	getWorkflows();
})

async function getWorkflows() {
	console.log("getting workflows");
	try {
		const result = await RemoteServices.getWorkflows();
		workflows.value = result;
		console.log("Fetched workflows: ", workflows.value);
	} catch (error) {
		console.error("Error getting workflows: ", error);
	}
}
</script>
