<script setup lang="ts">
import { onMounted } from 'vue'
import { useShips } from '@/features/ship/useShips.ts'
import ShipList from '@/features/ship/ShipList.vue'
import ShipMap from '@/features/ship/ship-map/ShipMap.vue'
import Loader from '@/core/components/Loader.vue'

const { ships, loadShips, isLoading } = useShips()

onMounted(() => {
  loadShips()
})
</script>

<template>
  <Loader v-if="isLoading" />
  <section class="map-container">
    <ShipMap :ships="ships" />
    <ShipList :ships="ships" />
  </section>
</template>

<style scoped>
.map-container {
  height: 100%;
  width: 100%;
  display: grid;
  grid-template-columns: 8fr 2fr;
  gap: 8px;
}
</style>
