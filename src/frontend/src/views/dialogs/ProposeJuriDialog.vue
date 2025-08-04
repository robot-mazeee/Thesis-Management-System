<template>
    <div class="pa-4 text-center">
        <v-dialog v-model="dialog" max-width="400">
            <template v-slot:activator="{ props: activatorProps }">
                <v-btn
                    class="text-none font-weight-regular"
                    prepend-icon="mdi-plus"
                    text="Initate Thesis Worklow"
                    v-bind="activatorProps"
                    color="contrast"
                ></v-btn>
            </template>

            <v-card>
                <v-card-title>Select Juri</v-card-title>
                <v-card-text>
                    <v-checkbox 
                        v-for="prof in professors"
                        :key="prof"
                        :label="prof.name"
                        :value="prof"
                        v-model="selectedProfessors"
                        hide-details
                        density="compact"
                    ></v-checkbox>
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
						@click="
							dialog = false,
							proposeJuri()
						"
					></v-btn>
				</v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import RemoteServices from '../../services/RemoteService';
import PersonDto from '../../models/PersonDto';
import WorkflowDto from '../../models/WorkflowDto';
import { useStudentStore } from '../../stores/student';

const dialog = ref(false);
const professors = ref<PersonDto[]>([]);
const selectedProfessors = ref<PersonDto[]>([]);
const studentStore = useStudentStore();
const emit = defineEmits(['workflow-created'])

onMounted(() => {
    getProfessors();
})

async function getProfessors() {
    try {
        const result = await RemoteServices.getProfessors();
        professors.value = result;
    } catch (error) {
        console.error("Error getting professors: ", error);
    }
}

async function proposeJuri() {
    try {
        const workflow: WorkflowDto = {
            workflowStatus: 'JURI_PROPOSAL_SUBMITTED',
            professors: selectedProfessors.value,
            studentId: studentStore.getId,
            juriPresident: null
        }
        await RemoteServices.createWorkflow(workflow);
        emit('workflow-created')
    } catch (error) {
        console.log("Error creating juri proposal: ", error);
    }
}
</script>