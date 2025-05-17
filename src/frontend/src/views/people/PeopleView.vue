<template>
	<v-row align="center">
		<v-col>
			<h1 class="text-left ml-1">People List</h1>
		</v-col>
		<v-col cols="auto">
			<CreatePersonDialog @close-dialog="getPeople"/>
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
		:headers="peopleHeaders"
		:items="people"
		:search="search"
		:loading="loading"
		:custom-filter="fuzzySearch"
		item-key="id"
		class="text-left"
		no-data-text="No people."
	>
		<template v-slot:[`item.type`]="{ item }">
			<v-chip :color="getColorByType(item.type)" text-color="white">
				{{ translateType(item.type) }}
			</v-chip>
		</template>
		<template v-slot:[`item.actions`]="{ item }">
			<div class="d-flex align-center justify-center ga-2">
				<EditPersonDialog :person-to-edit="item" @close-dialog="getPeople" />
				<v-icon @click="deletePerson(item)" color="red" class="cursor-pointer">
					mdi-delete
				</v-icon>
			</div>
		</template>
	</v-data-table>
</template>

<script setup lang="ts">
import RemoteService from '../../services/RemoteService'
import { reactive, ref } from 'vue'
import PersonDto from '../../models/PersonDto'
import { useRouter } from 'vue-router'
import CreatePersonDialog from '../dialogs/CreatePersonDialog.vue'
import EditPersonDialog from '../dialogs/EditPersonDialog.vue'
import { getColorByType, translateType } from '../mappings/peopleMappings'

let search = ref('')
let loading = ref(true)

const peopleHeaders = [
	{ title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
	{
		title: 'Name',
		key: 'name',
		value: 'name',
		sortable: true,
		filterable: true
	},
	{
		title: 'IST ID',
		key: 'istId',
		value: 'istId',
		sortable: true,
		filterable: true
	},
	{
		title: 'Email',
		key: 'email',
		value: 'email',
		sortable: true,
		filterable: true
	},
	{
		title: 'Role',
		key: 'type',
		value: 'type',
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

const people: PersonDto[] = reactive([])

getPeople() // first render

async function getPeople() { 
	people.splice(0, people.length);
	
	console.log("Getting people!");
	try {
		people.push(...(await RemoteService.getPeople()));
	} catch (error) {
		console.error("Error getting people: ", error);
	}

	loading.value = false;
}

const deletePerson = async (person: PersonDto) => {
	console.log("Deleting person:", person);
	try {
		await RemoteService.deletePerson(person);
		getPeople();
	} catch (error) {
		console.error("Error deleting person: ", error);
	}
}

const fuzzySearch = (value: string, search: string) => {
	// Regex to match any character in between the search characters
	let searchRegex = new RegExp(search.split('').join('.*'), 'i');
	return searchRegex.test(value);
}

</script>
