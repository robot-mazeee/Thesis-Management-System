<template>
    <div class="pa-4 text-center">
        <v-dialog v-model="dialog" max-width="400">
            <template v-slot:activator="{ props: activatorProps }">
                <v-btn
                    class="text-none font-weight-regular"
                    prepend-icon="mdi-plus"
                    text="Select Juri President"
                    v-bind="activatorProps"
                    color="contrast"
                ></v-btn>
            </template>

            <v-card>
                <v-card-title>Select Juri President</v-card-title>
                <v-card-text>
                    <v-radio-group v-model="selectedPresident">
                        <v-radio
                            v-for="prof in props.workflow.professors"
                            :key="prof.id"
                            :label="prof.name"
                            :value="prof"
                        />
                    </v-radio-group>
                </v-card-text>
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
						@click="async () => { 
                            const response = await saveJuriPresident();
                            if (response) 
                                dialog = false; 
                        }"
					></v-btn>
				</v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import type PersonDto from '../../models/PersonDto';
import { useWorkflowStore } from '../../stores/workflows';
import WorkflowDto from '../../models/WorkflowDto';

const props = defineProps<{
  workflow: WorkflowDto;
}>();

const selectedPresident = ref<PersonDto | null>(null);
const dialog = ref(false);
const workflowStore = useWorkflowStore();

async function saveJuriPresident() {
    if (selectedPresident.value === null) {
        alert("Please select a juri president.");
        return false;
    }
    workflowStore.saveJuriPresident(props.workflow.id, selectedPresident.value);
    return true;
}
</script>
