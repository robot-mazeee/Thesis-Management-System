<template>
    <v-row justify="center" align="center">
        <v-col cols="auto">
            <h2 class="text-left ml-1"></h2>
        </v-col>
    </v-row>

    <v-row justify="center">
        <v-col cols="auto">
            <v-btn @click="proposeJuriPage" color="secondary" class="mb-3">
                Start Thesis Workflow (Propose Juri)
            </v-btn>
        </v-col>
    </v-row>
  
  </template>
  
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useRoleStore } from '@/stores/role'

const roleStore = useRoleStore()
const router = useRouter()

const proposeJuriPage = () => {
    if (roleStore.isStudent) {
        router.push("/juri-proposal")
    }
}

</script>