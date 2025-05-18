<template>
    <div class="pa-4 text-center">
		<v-dialog v-model="dialog" max-width="400">
			<template v-slot:activator="{ props: activatorProps }">
				<v-btn
					class="text-none font-weight-regular"
					prepend-icon="mdi-plus"
					text="Add Person"
					v-bind="activatorProps"
					color="primary"
				></v-btn>
			</template>
	
			<v-card prepend-icon="mdi-account" title="New">
				<v-card-text>
					<v-text-field 
						label="Name*"
						required
						v-model="newPerson.name">
					</v-text-field>
		
					<v-text-field 
						label="IST ID*"
						required
						v-model="newPerson.istId">
					</v-text-field>
		
					<v-text-field 
						label="Email*"
						required
						v-model="newPerson.email">
					</v-text-field>
					
					<v-select
						:items="['Coordinator', 'Staff', 'Student', 'Professor', 'Admin']"
						label="Role*"
						required
						v-model="newPerson.type"
					></v-select>
				</v-card-text>
	
				<v-divider></v-divider>
		
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn 
						text="Close" 
						variant="plain" 
						@click="dialog = false">
					</v-btn>
		
					<v-btn
						text="Save"
						color="primary"
						variant="tonal"
						@click="
							dialog = false,
							savePerson()
						"
					></v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
    </div>
</template>
  
<script setup lang="ts">
import { ref } from 'vue'
import type PersonDto from '../../models/PersonDto'
import RemoteService from '../../services/RemoteService'
import { typeMappings } from '../../mappings/peopleMappings'

const dialog = ref(false)
const emit = defineEmits(['close-dialog'])

const newPerson = ref<PersonDto>({
	name: '',
	istId: '',
	email: '',
	type: ''
})

const savePerson = async () => {
	newPerson.value.type = typeMappings[newPerson.value.type as keyof typeof typeMappings];
	
	try {
		await RemoteService.createPerson(newPerson.value);
		console.log('Person saved!');
	} catch (error) {
		console.error("Error creating person: ", error);
	}

	newPerson.value = {
		name: '',
		istId: '',
		email: '',
		type: ''
	}

	emit('close-dialog');
}
</script>
  