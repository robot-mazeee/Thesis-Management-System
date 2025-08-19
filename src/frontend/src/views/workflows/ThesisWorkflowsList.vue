<template>
	<v-row align="center">
		<v-col>
			<h1 class="text-left ml-1">Workflows List</h1>
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
		:items="workflowStore.workflows"
		:search="search"
		:loading="loading"
		:custom-filter="fuzzySearch"
		item-key="id"
		class="text-left"
		no-data-text="No workflows."
	>
        <template v-slot:[`item.student`]="{ item }">
            {{ item.student.name }} ({{ item.student.istId }})
		</template>
		<template v-slot:[`item.status`]="{ item }">
			<v-chip :color="getColor(item.status)" text-color="white">
				{{ item.status }}
			</v-chip>
		</template>
		<template v-slot:[`item.actions`]="{ item }">
			<div class="d-flex align-center justify-center ga-2">
				<AdminActions v-if="roleStore.isAdmin" :workflow="item" />
                <CoordinatorActions v-if="roleStore.isCoordinator" :workflow="item" />
                <StaffActions v-if="roleStore.isStaff" :workflow="item" />
			</div>
		</template>
	</v-data-table>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useWorkflowStore } from '../../stores/workflows';
import { useRoleStore } from '../../stores/role';
import AdminActions from './AdminActions.vue';
import CoordinatorActions from './CoordinatorActions.vue';
import StaffActions from './StaffActions.vue';
import { getColor } from '../../mappings/workflowMappings';

let search = ref('');
let loading = ref(true);

const workflowsHeaders = [
	{ title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
	{
		title: 'Student',
		key: 'student',
		value: 'student',
		sortable: true,
		filterable: true
	},
	{
		title: 'Status',
		key: 'status',
		value: 'status',
		sortable: true,
		filterable: true
	},
	{
		title: 'Actions',
		key: 'actions',
		value: 'actions',
		sortable: false,
		filterable: false,
		align: 'center'
	}
]

const workflowStore = useWorkflowStore();
const roleStore = useRoleStore();

onMounted(() => {
    workflowStore.fetchWorkflows();
    loading.value = false;
});

const fuzzySearch = (value: string, search: string) => {
	let searchRegex = new RegExp(search.split('').join('.*'), 'i');
	return searchRegex.test(value);
}

</script>