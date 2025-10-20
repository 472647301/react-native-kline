import { useEffect, useRef, useState } from 'react';
import { ActivityIndicator, StyleSheet, View } from 'react-native';
import { KLineChart, KLineState } from 'rn-kline';
import type { KLineChartProps } from 'rn-kline';
import { fetch_kline_list, type IPeriod } from '../../api';
import { SafeAreaView } from 'react-native-safe-area-context';
import { KLinePeriod } from './components/KLinePeriod';
import { KLineLocale } from './components/KLineLocale';
import { dateTimeFormatter } from '../../config';
import { useTranslation } from 'react-i18next';
import dayjs from 'dayjs';

export function KLinePage() {
  const { t } = useTranslation();
  const [loading, setLoading] = useState(false);
  const [period, setPeriod] = useState<IPeriod>('MIN_15');
  const [kLineState, setKLineState] = useState(KLineState.K_LINE);

  const kLineRef = useRef<KLineChart>(null);
  const lastId = useRef<number>(undefined);
  const isLoadingHistory = useRef(false);
  const updateId = useRef(0);

  const initKLineList = async () => {
    setLoading(true);
    const res = await fetch_kline_list(period);
    setLoading(false);
    if (!res.length) return;
    lastId.current = res[0]?.id;
    kLineRef.current?.resetData(res, true, true);
  };

  const simulationUpdate = () => {
    if (isLoadingHistory.current) return;
    let diff = 0;
    const list = kLineRef.current?.getData();
    if (!list || !list.length) return;
    if (list.length >= 2) {
      diff = list[1]!.id - list[0]!.id;
    }
    if (diff < 0) {
      diff = Math.abs(diff);
    }
    const item = list.pop();
    if (!item) return;
    updateId.current += 1;
    const rate = Number((Math.random() / 1000).toFixed(3));
    if (updateId.current % 2) {
      item.open -= Math.ceil(item.open * rate);
      item.high -= Math.ceil(item.high * rate);
      item.low -= Math.ceil(item.low * rate);
      item.close -= Math.ceil(item.close * rate);
      item.vol -= Math.ceil(item.vol * rate);
    } else {
      item.open += Math.ceil(item.open * rate);
      item.high += Math.ceil(item.high * rate);
      item.low += Math.ceil(item.low * rate);
      item.close += Math.ceil(item.close * rate);
      item.vol += Math.ceil(item.vol * rate);
    }
    const updateTime = item.id + diff;
    const now = Math.ceil(Date.now() / 1000);
    if (updateTime < now) {
      item.id = updateTime;
      console.log(
        'addNewData',
        dayjs(item.id * 1000).format('YYYY-MM-DD HH:mm'),
        item
      );
      kLineRef.current?.addNewData(item);
    } else {
      console.log('changeItem', item);
      kLineRef.current?.changeItem(list.length - 1, item);
    }
  };

  useEffect(() => {
    initKLineList();
    const interval = setInterval(simulationUpdate, 25000);
    return () => {
      clearInterval(interval);
    };
  }, [period]);

  const onLoadingHistory = async () => {
    if (isLoadingHistory.current) return;
    isLoadingHistory.current = true;
    const res = await fetch_kline_list(period, lastId.current);
    isLoadingHistory.current = false;
    if (!res.length) return;
    lastId.current = res[0]?.id;
    kLineRef.current?.addHistoryData(res);
  };

  const onSlidLeft: KLineChartProps['onSlidLeft'] = () => {
    onLoadingHistory();
    console.log('onSlidLeft');
  };

  const onSlidRight: KLineChartProps['onSlidRight'] = () => {
    console.log('onSlidRight');
  };

  const labels = ['时间', '开', '高', '低', '收', '涨跌额', '涨跌幅', '成交量'];

  return (
    <SafeAreaView style={styles.page}>
      <KLineLocale
        kLineState={kLineState}
        setKLineState={setKLineState}
        setPeriod={setPeriod}
      />
      <View style={styles.kline}>
        <KLineChart
          ref={kLineRef}
          loading={loading}
          style={styles.kline}
          kLineState={kLineState}
          onSlidLeft={onSlidLeft}
          onSlidRight={onSlidRight}
          dateTimeFormatter={dateTimeFormatter[period]}
          selectedInfoLabels={labels.map((str) => `${t(str)}   `)}
          // backgroundFillBottomColor="#1677ff"
          // backgroundFillTopColor="#eb2f96"
          // timeLineFillBottomColor='#1677ff'
          // timeLineFillTopColor='#eb2f96'
          // timeLineColor='#722ed1'
          // increaseColor='#eb2f96'
          // decreaseColor='#722ed1'
          // gridColumns={4}
          // gridRows={3}
          // volFormatter="%.02f"
          // valueFormatter="%.04f"
          // mainValueFormatter="%.03f"
        />
        {loading ? (
          <View style={styles.loading}>
            <ActivityIndicator color={'#fff'} />
          </View>
        ) : null}
      </View>
      <KLinePeriod period={period} setPeriod={setPeriod} loading={loading} />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  page: {
    flex: 1,
    justifyContent: 'flex-end',
  },
  kline: {
    height: 260,
    width: '100%',
  },
  loading: {
    position: 'absolute',
    width: '100%',
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
