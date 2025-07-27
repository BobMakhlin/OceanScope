import { ShipType } from './ShipType'
import type { ShipMetrics } from '@/models/ShipMetrics.ts'

export interface Ship {
  id: string;
  name: string;
  type: ShipType;
  metrics: ShipMetrics;
}
