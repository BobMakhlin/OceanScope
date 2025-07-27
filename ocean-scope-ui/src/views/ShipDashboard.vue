<script setup lang="ts">
import { LMap, LMarker, LTileLayer } from '@vue-leaflet/vue-leaflet'
import { icon as LeafletIcon, Icon, type PointExpression } from 'leaflet'
import { ref, onMounted, onUnmounted, type Ref } from 'vue'

// todo move it from the component script!
// todo feature driven arch?
const shipRedIcon = LeafletIcon({
  iconUrl: '/ship-marker-red.png',
  iconSize: [24, 24],
  iconAnchor: [16, 16],
  popupAnchor: [0, -16],
})

const shipIcon = LeafletIcon({
  iconUrl: '/ship-marker.png',
  iconSize: [24, 24],
  iconAnchor: [16, 16],
  popupAnchor: [0, -16],
})

const ships = ref([
  { id: 1, name: 'Poseidon', lat: 37.77, lng: -30.45, icon: shipRedIcon as Icon },
  { id: 2, name: 'Neptune', lat: 38.12, lng: -30.1, icon: shipIcon as Icon },
])
const center: Ref<PointExpression> = ref([50.5, -1.5])

let intervalId: number
onMounted(() => {
  intervalId = setInterval(() => {
    console.log('simulation turn')
    ships.value.forEach((item) => {
      item.lat += 0.015
    })
  }, 1000)
})
onUnmounted(() => {
  clearInterval(intervalId)
})

const onGoToFirstShipClick = () => {
  center.value = [ships.value[0].lat, ships.value[0].lng]
}

const osmUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
const osmAttribution = '&copy; OpenStreetMap contributors'
const openSeaMapUrl = 'https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png'
const openSeaMapAttribution = '&copy; <a href="http://www.openseamap.org">OpenSeaMap</a>'
</script>

<template>
  <section class="map-container">
    <l-map ref="map" class="map" :zoom="3" :center="center">
      <!-- Base tile layer -->
      <l-tile-layer :url="osmUrl" :attribution="osmAttribution" />

      <!-- OpenSeaMap overlay -->
      <l-tile-layer :url="openSeaMapUrl" :attribution="openSeaMapAttribution" />

      <l-marker
        v-for="ship in ships"
        :key="ship.id"
        :lat-lng="[ship.lat, ship.lng]"
        :icon="ship.icon"
      />
    </l-map>
    <div>
      List here
      <button @click="onGoToFirstShipClick()">Go to the first ship</button>
    </div>
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
