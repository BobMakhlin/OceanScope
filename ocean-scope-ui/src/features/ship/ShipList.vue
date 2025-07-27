<script setup lang="ts">
import type { Ship } from '@/models/Ship.ts'

defineProps<{
  ships: Ship[]
}>()
</script>

<template>
  <div v-if="ships" class="ship-list">
    <div v-for="ship in ships" :key="ship.id" class="ship-item">
      <div class="header">
        <span class="name">{{ ship.name }}</span>
        <span class="type">{{ ship.type }}</span>
      </div>
      <div class="metrics">
        <div>📍 {{ ship.metrics.lat.toFixed(2) }}, {{ ship.metrics.lng.toFixed(2) }}</div>
        <div>🚢 Speed: {{ ship.metrics.speed.toFixed(1) }} kn</div>
        <div>🧭 Heading: {{ ship.metrics.heading.toFixed(0) }}°</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ship-list {
  padding: 1rem;
  background-color: #fff;
  border-radius: 8px;
  height: 100%;
  overflow-y: auto;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
  border-bottom: 1px solid #eee;
  padding-bottom: 0.5rem;
}

.ship-item {
  border: 1px solid #ddd;
  border-radius: 6px;
  padding: 0.75rem;
  margin-bottom: 0.75rem;
  background-color: #f9f9f9;
  transition: background-color 0.2s;
}

.ship-item:hover {
  background-color: #f1f1f1;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 500;
}

.name {
  font-size: 1rem;
}

.type {
  font-size: 0.75rem;
  padding: 2px 6px;
  background-color: #e0f0ff;
  color: #0073b1;
  border-radius: 4px;
  text-transform: capitalize;
}

.metrics {
  margin-top: 0.5rem;
  font-size: 0.85rem;
  color: #555;
}
</style>
