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
						Status:
						<v-chip :color="getColor(workflow.status)">
							{{ workflow.status }}
						</v-chip>
					</v-card-subtitle>

					<v-card-text>
						Juri President: {{ workflow.juriPresident !== null ? workflow.juriPresident.name : "Not Assigned"}}
					</v-card-text>
					<AdminActions v-if="roleStore.isAdmin" :workflow="workflow"/>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import WorkflowDto from '../../models/WorkflowDto';
import { getWorkflows } from './WorkflowActions';
import { getColor } from '../../mappings/workflowMappings';
import { useRoleStore } from '../../stores/role';
import AdminActions from './AdminActions.vue';

const workflows = ref<WorkflowDto[]>([]);
const roleStore = useRoleStore();

onMounted(async () => {
	workflows.value = await getWorkflows();
})
</script>
