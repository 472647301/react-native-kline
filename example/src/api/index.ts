import axios from 'axios';
import dayjs from 'dayjs';
import type { KLineEntity } from 'rn-kline';

export type IPeriod =
  | 'MIN_1'
  | 'MIN_15'
  | 'HOUR_1'
  | 'DAY_1'
  | 'WEEK_1'
  | 'MONTH_1';

export type IResKLine = {
  symbol: string;
  type: string;
  date: string;
  amount: number;
  volume: number;
  open: number;
  close: number;
  high: number;
  low: number;
  id: number;
};

const diff: Record<IPeriod, number> = {
  DAY_1: 1440,
  MIN_1: 1,
  MIN_15: 15,
  HOUR_1: 60,
  MONTH_1: 302400,
  WEEK_1: 10080,
};

export async function fetch_kline_list(
  type: IPeriod,
  last?: number,
  size: number = 300
) {
  const n = diff[type] ?? 1;
  const now = parseInt((Date.now() / 1000).toFixed());
  const from = (last ?? now) - 1 * n * 60 * size;
  console.log(
    'last',
    last,
    dayjs(last ? last * 1000 : now * 1000).format('YYYY-MM-DD HH:mm'),
    dayjs(from * 1000).format('YYYY-MM-DD HH:mm')
  );
  const data = {
    from,
    to: last ?? now,
    symbol: 'btcusdt',
    type,
  };
  console.log('request.params', data);
  const [_, res] = await to(
    axios.post<{ data: IResKLine[] }>(
      'https://xray.byronzhu.cn/api/market/getScaleByDate',
      data
    )
  );
  console.log('res?.data', _, res?.data);
  if (!res || !Array.isArray(res.data.data)) {
    return [];
  }
  const list: KLineEntity[] = [];
  res.data.data.forEach((item) => {
    list.push({ ...item, vol: item.volume });
  });
  return list;
}

/**
 * @param { Promise } promise
 * @param { Object= } errorExt - Additional Information you can pass to the err object
 * @return { Promise }
 */
export async function to<T, U = Error>(
  promise: Promise<T>,
  errorExt?: object
): Promise<[U, undefined] | [null, T]> {
  return promise
    .then<[null, T]>((data: T) => [null, data])
    .catch<[U, undefined]>((err: U) => {
      if (errorExt) {
        const parsedError = Object.assign({}, err, errorExt);
        return [parsedError, undefined];
      }
      return [err, undefined];
    });
}
