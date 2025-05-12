<template>
  <UtilBar />
  <NavBar :navbarItems="navbarItems" />
</template>

<script setup lang="ts">
import UtilBar from '@/components/UtilBar.vue'
import NavBar from '@/components/NavBar.vue'

const navbarItems = [
  { name: 'Home', path: '/', icon: 'mdi-home' },
  { name: 'Workflows', path: '/workflows', icon: 'mdi-school' },
  { name: 'People', path: '/people', icon: 'mdi-account-group' },
  { name: 'Statistics', path: '/statistics', icon: 'mdi-chart-bar' },
]
</script>
