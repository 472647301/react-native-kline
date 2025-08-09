import type { IPeriod } from '../api';

export const dateTimeFormatter: Record<IPeriod, string> = {
  MIN_1: 'HH:mm',
  MIN_15: 'HH:mm',
  HOUR_1: 'dd:HH',
  DAY_1: 'MM:dd',
  WEEK_1: 'yyyy-MM:dd',
  MONTH_1: 'yyyy-MM',
};
