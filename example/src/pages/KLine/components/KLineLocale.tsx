import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { KLineState } from 'rn-kline';
import { useTranslation } from 'react-i18next';
import type { IPeriod } from '../../../api';

interface Props {
  kLineState: KLineState;
  setKLineState: (kLineState: KLineState) => void;
  setPeriod: (period: IPeriod) => void;
}
export function KLineLocale(props: Props) {
  const { t, i18n } = useTranslation();
  const { kLineState, setKLineState, setPeriod } = props;

  const onPress = (value: KLineState) => {
    if (value === kLineState) return;
    setKLineState(value);
    if (value === KLineState.TIME_LINE) setPeriod('MIN_1');
  };

  const isZh = i18n.language === 'zh_CN';
  const isTime = kLineState === KLineState.TIME_LINE;

  return (
    <View style={styles.rows}>
      <TouchableOpacity
        style={[styles.item, { borderColor: !isTime ? '#d9d9d9' : '#1677ff' }]}
        onPress={() => onPress(KLineState.TIME_LINE)}
      >
        <Text style={[styles.text, { color: !isTime ? '#000' : '#1677ff' }]}>
          {t('分时图')}
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.item, { borderColor: isTime ? '#d9d9d9' : '#1677ff' }]}
        onPress={() => onPress(KLineState.K_LINE)}
      >
        <Text style={[styles.text, { color: isTime ? '#000' : '#1677ff' }]}>
          {t('K线图')}
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.item, { borderColor: !isZh ? '#d9d9d9' : '#1677ff' }]}
        onPress={() => i18n.changeLanguage('zh_CN')}
      >
        <Text style={[styles.text, { color: !isZh ? '#000' : '#1677ff' }]}>
          中文
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.item, { borderColor: isZh ? '#d9d9d9' : '#1677ff' }]}
        onPress={() => i18n.changeLanguage('en_US')}
      >
        <Text style={[styles.text, { color: isZh ? '#000' : '#1677ff' }]}>
          English
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  rows: {
    flexDirection: 'row',
    justifyContent: 'flex-end',
    marginVertical: 16,
  },
  item: {
    height: 36,
    justifyContent: 'center',
    paddingHorizontal: 12,
    marginHorizontal: 5,
    borderRadius: 6,
    borderWidth: 1,
    borderColor: '#d9d9d9',
  },
  text: {
    fontSize: 14,
    color: '#000',
  },
});
