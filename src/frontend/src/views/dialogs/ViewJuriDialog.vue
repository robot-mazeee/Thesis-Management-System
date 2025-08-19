<template>
    <div class="pa-4 text-center">
        <v-dialog v-model="dialog" max-width="400">
            <template v-slot:activator="{ props: activatorProps }">
                <v-btn
                    class="text-none font-weight-regular"
                    text="View Juri Proposal"
                    v-bind="activatorProps"
                    color="contrast"
                ></v-btn>
            </template>

            <v-card>
                <v-card-title>Juri Proposal</v-card-title>
                <v-card-text>
                    <ul>
                        <li v-for="prof in props.workflow.professors" :key="prof.id">
                            {{ prof.name }}
                        </li>
                    </ul>
                </v-card-text>
                <v-card-actions>
					<v-spacer></v-spacer>
					<v-btn 
						text="Close" 
						variant="plain" 
						@click="dialog = false">
					</v-btn>
		
					<v-btn
						text="Approve"
						color="primary"
						variant="tonal"
						@click="emit('approved'), dialog = false;"
					></v-btn>
                    <v-btn
						text="Reject"
						color="error"
						variant="tonal"
						@click="emit('rejected'), dialog = false;"
					></v-btn>
				</v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import WorkflowDto from '../../models/WorkflowDto';

const props = defineProps<{
    workflow: WorkflowDto;
}>();

const emit = defineEmits(['approved', 'rejected']);
const dialog = ref(false);
</script>