/**
 * Provides OpenStreetMap and OpenSeaMap tile layer URLs and attributions.
 * Static, non-reactive composable.
 */
export function useOpenSeaTileLayers() {
  const osmUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
  const osmAttribution = '&copy; OpenStreetMap contributors'

  const openSeaMapUrl = 'https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png'
  const openSeaMapAttribution = '&copy; <a href="http://www.openseamap.org">OpenSeaMap</a>'

  return {
    osmUrl,
    osmAttribution,
    openSeaMapUrl,
    openSeaMapAttribution,
  }
}
