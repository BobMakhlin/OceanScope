import { onMounted, onUnmounted, ref } from 'vue'
import { WS_URL } from '../../../config.ts'
// todo provide more elegant fix.
// @ts-ignore
import SockJS from 'sockjs-client/dist/sockjs'
import Stomp, { Client } from 'webstomp-client'
import type { ShipMetricsUpdatedWsEvent } from '@/models/ShipMetricsUpdatedWsEvent.ts'

export function useShipWebSocket() {
  let stompClient: Client | null = null
  const isConnected = ref(false)
  const shipMetrics = ref<ShipMetricsUpdatedWsEvent>()

  onMounted(() => {
    const socket = new SockJS(`${WS_URL}`)
    stompClient = Stomp.over(socket)

    stompClient.connect(
      {},
      () => {
        isConnected.value = true
        stompClient?.subscribe('/topic/ships/all', (tick) => {
          shipMetrics.value = JSON.parse(tick.body) as ShipMetricsUpdatedWsEvent
        })
      },
      (error) => {
        console.log(error)
        isConnected.value = false
      },
    )
  })

  onUnmounted(() => {
    stompClient?.disconnect()
    isConnected.value = false
  })

  return { isConnected, shipMetrics }
}
