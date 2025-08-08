import { useEffect, useRef, useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { KLineChart, KLineState } from 'react-native-kline';
import type { KLineChartRef, KLineChartProps } from 'react-native-kline';
import { fetch_kline_list } from './api';

export default function App() {
  const [loading, setLoading] = useState(false);
  const kLineRef = useRef<KLineChartRef>(null);

  const initKLineList = async () => {
    setLoading(true);
    const res = await fetch_kline_list('DAY_1');
    setLoading(false);
    if (!res.length) return;
    kLineRef.current?.resetData(res, true, true);
  };

  useEffect(() => {
    initKLineList();
  }, []);

  const onSlidLeft: KLineChartProps['onSlidLeft'] = (event) => {
    console.log('onSlidLeft', event);
  };

  const onSlidRight: KLineChartProps['onSlidRight'] = (event) => {
    console.log('onSlidRight', event);
  };

  return (
    <View style={styles.container}>
      <KLineChart
        ref={kLineRef}
        loading={loading}
        style={styles.box}
        onSlidLeft={onSlidLeft}
        onSlidRight={onSlidRight}
        kLineState={KLineState.K_LINE}
        dateTimeFormatter="hh:mm"
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: '100%',
    height: 320,
  },
});
