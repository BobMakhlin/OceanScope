<script setup lang="ts">
import { LMap, LMarker, LTileLayer } from '@vue-leaflet/vue-leaflet'
import type { Ship } from '@/models/Ship.ts'
import { computed } from 'vue'
import { useOpenSeaTileLayers } from './useOpenSeaTileLayers.ts'
import { getShipIcon } from '@/features/ship/ship-map/shipIcon.ts'

const { osmUrl, osmAttribution, openSeaMapUrl, openSeaMapAttribution } = useOpenSeaTileLayers()

const props = defineProps<{
  ships: Ship[]
}>()

const shipsWithIcons = computed(() => {
  return props.ships.map((ship) => ({ ...ship, icon: getShipIcon(ship.type) }))
})
const mapCenter = computed<[number, number]>(() => {
  const defaultCoordinates: [number, number] = [50.5, -1.5]
  if (!props.ships?.length) {
    return defaultCoordinates
  }
  const firstShipMetrics = props.ships[0].metrics
  return [firstShipMetrics.lat, firstShipMetrics.lng] as [number, number]
})
</script>

<template>
  <l-map v-if="ships" ref="map" class="map" :zoom="3" :center="mapCenter">
    <!-- Base tile layer -->
    <l-tile-layer :url="osmUrl" :attribution="osmAttribution" />
    <!-- OpenSeaMap overlay -->
    <l-tile-layer :url="openSeaMapUrl" :attribution="openSeaMapAttribution" />

    <l-marker
      v-for="ship in shipsWithIcons"
      :key="ship.id"
      :lat-lng="[ship.metrics.lat, ship.metrics.lng]"
      :icon="ship.icon"
    />
  </l-map>
</template>
