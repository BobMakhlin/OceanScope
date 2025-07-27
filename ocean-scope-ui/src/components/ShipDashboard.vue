<script setup lang="ts">
import { ref } from 'vue'
import ShipMap from '@/components/ShipMap.vue'
import type { Ship } from '@/models/Ship.ts'
import { ShipType } from '@/models/ShipType.ts'
import ShipList from '@/components/ShipList.vue'

const ships = ref([
  {
    id: '1',
    name: 'Poseidon',
    type: ShipType.Tanker,
    metrics: { lat: 37.77, lng: -30.45, speed: 1, heading: 1 },
  },
  {
    id: '2',
    name: 'Neptune',
    type: ShipType.Container,
    metrics: { lat: 38.12, lng: -30.1, speed: 1, heading: 1 },
  },
] as Ship[])
</script>

<template>
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

.map {
  width: 100%;
  height: 100%;
}
</style>
