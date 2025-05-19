<template>
    <v-row justify="center" align="center" class="w-full min-h-screen bg-gray-100">
        <v-col cols="12" class="flex justify-center">
            <StudentProfile :student-id="null" />
        </v-col>
    </v-row>
</template>

  
<script setup lang="ts">
import StudentProfile from './StudentProfile.vue'

</script>