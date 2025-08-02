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

            <v-card prepend-icon="mdi-account" title="New">
                <v-card-title>Select Juri</v-card-title>
                <v-card-text>
                    <v-checkbox 
                        v-for="prof in professors"
                        :key="prof"
                        :label="prof"
                        :value="prof"
                        v-model="selectedProfessors"
                        hide-details
                        density="compact"
                    ></v-checkbox>
                </v-card-text>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import RemoteServices from '../../services/RemoteService';
import PersonDto from '../../models/PersonDto';

const dialog = ref(false);
const professors = ref<PersonDto[]>([]);
const selectedProfessors = ref([]);

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
</script>