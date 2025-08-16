<template>
	<h1>Workflows</h1>
	<h3 v-if="!workflowStore.loading && workflowStore.workflows.length === 0">
        No workflows.
    </h3>
	<v-container>
		<v-row>
			<v-col
				v-for="workflow in workflowStore.workflows"
				:key="workflow.id"
				cols="12"
				sm="6"
				md="4"
			>
				<v-card>
					<v-card-title>
						{{ workflow.student.name }} ({{ workflow.student.istId }})
					</v-card-title>

					<v-card-subtitle class="text-caption">
						Status:
						<v-chip :color="getColor(workflow.status)" size="small">
							{{ workflow.status }}
						</v-chip>
					</v-card-subtitle>

					<v-card-text>
						Juri President: {{ workflow.juriPresident !== null ? workflow.juriPresident.name : "Not Assigned"}}
					</v-card-text>
					<AdminActions v-if="roleStore.isAdmin" :workflow="workflow"/>
					<CoordinatorActions v-if="roleStore.isCoordinator" :workflow="workflow"/>
					<StaffActions v-if="roleStore.isStaff" :workflow="workflow"/>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useWorkflowStore } from '../../stores/workflows';
import { getColor } from '../../mappings/workflowMappings';
import { useRoleStore } from '../../stores/role';
import AdminActions from './AdminActions.vue';
import CoordinatorActions from './CoordinatorActions.vue';
import StaffActions from './StaffActions.vue';

const workflowStore = useWorkflowStore();
const roleStore = useRoleStore();

onMounted(() => {
    workflowStore.fetchWorkflows();
});
</script>