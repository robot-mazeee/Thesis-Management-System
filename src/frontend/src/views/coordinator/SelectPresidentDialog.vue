<template>
  <div class="pa-4 text-center">
    <v-dialog v-model="dialog" max-width="400">
      <template v-slot:activator="{ props: activatorProps }">
        <v-btn
          class="text-none font-weight-regular"
          prepend-icon="mdi-plus"
          text="Select Juri President"
          v-bind="activatorProps"
          color="primary"
        ></v-btn>
      </template>

      <v-card prepend-icon="mdi-account" title="Select Juri President" class="mx-auto" max-width="300">
        <v-container>
          <v-list>
            <v-list-item v-for="(item, index) in items" :key="index">
              <v-checkbox
                v-model="selectedItem"
                :label="`${item.name} (${item.email})`"
                :value="item"
                @change="updateSelection(item)"
              />
            </v-list-item>
          </v-list>
          <p>Selected: {{ selectedItem ? selectedItem.name : 'None' }}</p>
        </v-container>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text="Close" variant="plain" @click="dialog = false"></v-btn>
          <v-btn
            color="primary"
            text="Save"
            variant="tonal"
            @click="dialog = false, saveJuriPresident()"
          ></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';
import type PersonDto from '../../models/PersonDto';
import RemoteServices from '../../services/RemoteService';
import WorkflowDto from '../../models/WorkflowDto';

const props = defineProps<{
  juriProposal: WorkflowDto | null;
}>();

const selectedItem = ref<PersonDto | null>(null);

const items = computed(() => {
  return props.juriProposal?.professors ?? [];
});

const dialog = ref(false);

// Update the selected item when a checkbox is changed
const updateSelection = (item: PersonDto) => {
  selectedItem.value = item;
};

const saveJuriPresident = async () => {
  console.log("Saving selection:", selectedItem.value);
  try {
    props.juriProposal.juriPresident = selectedItem.value;
    props.juriProposal.status = 'JURI_PRESIDENT_ATTRIBUTED'
    const response = await RemoteServices.selectJuriPresident(props.juriProposal);
    console.log("Server Response:", response);
  } catch(error) {
    console.log('Failed to save juri president: ', selectedItem.value)
  }
};

</script>
