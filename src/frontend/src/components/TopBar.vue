<template>
    <UtilBar />
    <NavBar :navbarItems="navbarItems" />
</template>

<script setup lang="ts">
import UtilBar from './UtilBar.vue';
import NavBar from './NavBar.vue';

const navbarItems = [
    { name: 'Home', path: '/home', icon: 'mdi-home' },
    { name: 'Workflows', path: '/workflows', icon: 'mdi-school' },
    { name: 'People', path: '/people', icon: 'mdi-account-group' },
    { name: 'Statistics', path: '/statistics', icon: 'mdi-chart-bar' },
]
</script>
