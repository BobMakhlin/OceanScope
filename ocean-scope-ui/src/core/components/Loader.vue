<template>
  <div class="loader-overlay">
    <div class="spinner"></div>
  </div>
</template>

<script setup lang="ts">
// no props needed unless you want to customize it
</script>

<style scoped>
.loader-overlay {
  position: fixed;
  inset: 0;
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(2px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999; /* ensure it's on top of everything */
}

.spinner {
  width: 48px;
  height: 48px;
  border: 5px solid #ccc;
  border-top-color: #0073b1;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
</style>
