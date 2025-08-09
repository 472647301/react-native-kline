import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { KLinePage } from '../pages/KLine';
import { DepthPage } from '../pages/Depth';
import { Image } from 'react-native';
import { useTranslation } from 'react-i18next';

const Tab = createBottomTabNavigator();
const Stack = createNativeStackNavigator();

function HomeTabs() {
  const { t } = useTranslation();
  return (
    <Tab.Navigator screenOptions={{ headerShown: false }}>
      <Tab.Screen
        name="KLine"
        component={KLinePage}
        options={{
          title: t('K线图'),
          tabBarIcon: (props) => (
            <Image
              source={require('../assets/kline.png')}
              style={{ width: 24, height: 24, tintColor: props.color }}
            />
          ),
        }}
      />
      <Tab.Screen
        name="Depth"
        component={DepthPage}
        options={{
          title: t('深度图'),
          tabBarIcon: (props) => (
            <Image
              source={require('../assets/depth.png')}
              style={{ width: 20, height: 20, tintColor: props.color }}
            />
          ),
        }}
      />
    </Tab.Navigator>
  );
}

export function RouterContainer() {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Home" component={HomeTabs} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
