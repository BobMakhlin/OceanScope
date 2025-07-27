import { type Icon, icon } from 'leaflet'
import { ShipType } from '@/models/ShipType.ts'

const shipContainerIcon = icon({
  iconUrl: '/ship-container-marker.png',
  iconSize: [24, 24],
  iconAnchor: [16, 16],
  popupAnchor: [0, -16],
})

const shipTankerIcon = icon({
  iconUrl: '/ship-tanker-marker.png',
  iconSize: [24, 24],
  iconAnchor: [16, 16],
  popupAnchor: [0, -16],
})

const shipIcons: Record<ShipType, Icon> = {
  [ShipType.Tanker]: shipTankerIcon,
  [ShipType.Container]: shipContainerIcon,
}

export function getShipIcon(shipType: ShipType) {
  return shipIcons[shipType];
}
