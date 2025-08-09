import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useTranslation } from 'react-i18next';
import type { IPeriod } from '../../../api';

interface ItemT {
  title: string;
  period: IPeriod;
}
interface Props {
  period: IPeriod;
  setPeriod: (period: IPeriod) => void;
  loading?: boolean;
}
export function KLinePeriod(props: Props) {
  const { t } = useTranslation();
  const { period, setPeriod, loading } = props;

  const onPress = (item: ItemT) => {
    if (period === item.period) return;
    setPeriod(item.period);
  };

  const periodList: ItemT[] = [
    {
      period: 'MIN_1',
      title: t('{{period}}分钟', { period: '1' }),
    },
    {
      period: 'MIN_15',
      title: t('{{period}}分钟', { period: '15' }),
    },
    {
      period: 'HOUR_1',
      title: t('{{period}}小时', { period: '1' }),
    },
    {
      period: 'DAY_1',
      title: t('日线'),
    },
    {
      period: 'WEEK_1',
      title: t('周线'),
    },
    {
      period: 'MONTH_1',
      title: t('月线'),
    },
  ];

  return (
    <View style={styles.btns}>
      {periodList.map((item) => (
        <TouchableOpacity
          key={item.period}
          disabled={loading}
          style={styles.btnPeriod}
          onPress={() => onPress(item)}
        >
          <Text
            style={[
              styles.btnText,
              { color: period === item.period ? '#1677ff' : '#333' },
            ]}
          >
            {item.title}
          </Text>
        </TouchableOpacity>
      ))}
    </View>
  );
}

const styles = StyleSheet.create({
  btns: {
    height: 42,
    flexDirection: 'row',
    borderTopWidth: 1,
    borderBottomWidth: 1,
    borderLeftWidth: 1,
    borderColor: '#d9d9d9',
    marginVertical: 16,
  },
  btnPeriod: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    borderRightWidth: 1,
    borderRightColor: '#d9d9d9',
  },
  btnText: {
    fontSize: 12,
  },
});
