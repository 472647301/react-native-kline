import type { IPeriod } from '../api';

export const dateTimeFormatter: Record<IPeriod, string> = {
  MIN_1: 'MM:dd HH:mm',
  MIN_15: 'MM:dd HH:mm',
  HOUR_1: 'MM:dd HH:mm',
  DAY_1: 'yyyy-MM:dd',
  WEEK_1: 'yyyy-MM:dd',
  MONTH_1: 'yyyy-MM',
};
