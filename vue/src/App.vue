<template>
  <RouterView :key="router.currentRoute.value.fullPath" />
</template>

<script setup>
import router from "@/router/index.js";

</script>