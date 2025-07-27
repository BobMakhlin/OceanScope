import { type Ref, ref } from 'vue'
import type { Ship } from '@/models/Ship.ts'
import $api from '@/core/http'

export function useShips() {
  const ships: Ref<Ship[]> = ref([])
  const isLoading = ref(false)
  const error = ref<Error | null>(null)

  async function loadShips() {
    isLoading.value = true
    error.value = null
    try {
      const response = await $api.get<Ship[]>('/ships')
      ships.value = response.data
    } catch (err: any) {
      error.value = err
    } finally {
      isLoading.value = false
    }
  }

  return {
    ships,
    isLoading,
    error,
    loadShips,
  }
}
