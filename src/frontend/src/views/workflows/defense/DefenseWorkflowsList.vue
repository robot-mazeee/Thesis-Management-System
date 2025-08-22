<template>
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
		:items="defenseStore.defenses"
		:search="search"
		:loading="loading"
		:custom-filter="fuzzySearch"
		item-key="id"
		class="text-left"
		no-data-text="No defenses."
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
                <DefenseActions v-if="roleStore.isCoordinator" :defense="item" />
			</div>
		</template>
	</v-data-table>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useDefenseStore } from '../../../stores/defense';
import { useRoleStore } from '../../../stores/role';
import { getColor } from '../../../mappings/workflowMappings';
import DefenseActions from './DefenseActions.vue';

let search = ref('');
let loading = ref(true);

const defensesHeaders = [
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

const defenseStore = useDefenseStore();
const roleStore = useRoleStore();

onMounted(() => {
    defenseStore.fetchDefenses();
    loading.value = false;
});

const fuzzySearch = (value: string, search: string) => {
	let searchRegex = new RegExp(search.split('').join('.*'), 'i');
	return searchRegex.test(value);
}
</script>