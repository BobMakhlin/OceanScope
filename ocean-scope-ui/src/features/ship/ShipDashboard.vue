<script setup lang="ts">
import { onMounted, watch } from 'vue'
import { useShips } from '@/features/ship/useShips.ts'
import ShipList from '@/features/ship/ShipList.vue'
import ShipMap from '@/features/ship/ship-map/ShipMap.vue'
import Loader from '@/core/components/Loader.vue'
import { useShipWebSocket } from '@/features/ship/useShipsWebSocket.ts'

const { ships, loadShips, isLoading } = useShips()
const { isConnected, shipMetrics } = useShipWebSocket()

onMounted(() => {
  loadShips()
})

watch(
  [isConnected, shipMetrics],
  ([connected, updatedMetrics]) => {
    if (!connected || !updatedMetrics) {
      return
    }
    console.log('updatedMetrics:', updatedMetrics)

    ships.value.forEach((ship) => {
      const shipNewMetrics = updatedMetrics[ship.id]
      if (shipNewMetrics) {
        ship.metrics = { ...shipNewMetrics }
      }
    })
  },
  { flush: 'post' },
)
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
