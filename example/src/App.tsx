import { useEffect, useState } from 'react';
import { RouterContainer } from './router';
import { getLocales } from 'react-native-localize';
import { initReactI18next } from 'react-i18next';
import enUS from './locales/en_US.json';
import i18n from 'i18next';

const isZh = getLocales()[0]?.languageCode.indexOf('zh') !== -1;

i18n.use(initReactI18next);

export default function App() {
  const [loading, setLoading] = useState(true);

  const initI18n = async () => {
    setLoading(true);
    const zhCN = Object.keys(enUS).reduce<Record<string, string>>(
      (pre, cur) => {
        pre[cur] = cur;
        return pre;
      },
      {}
    );
    await i18n.init({
      debug: __DEV__,
      lng: isZh ? 'zh_CN' : 'en_US',
      resources: {
        zh_CN: { translation: zhCN },
        en_US: { translation: enUS },
      },
    });
    setLoading(false);
  };

  useEffect(() => {
    initI18n();
  }, []);

  if (loading) return <></>;

  return <RouterContainer />;
}
