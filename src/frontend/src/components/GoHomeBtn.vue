<template>
    <v-col cols="auto">
        <v-btn @click="goHome" color="success" class="mb-3">
            Go Home
        </v-btn>
    </v-col>
</template>

<script setup lang="ts">
import router from '../router';

const goHome = () => {
    router.push("/")
}
</script>