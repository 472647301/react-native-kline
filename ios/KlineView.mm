#import "KlineView.h"

#import <react/renderer/components/KlineViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/KlineViewSpec/EventEmitters.h>
#import <react/renderer/components/KlineViewSpec/Props.h>
#import <react/renderer/components/KlineViewSpec/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"

#import "KLineStateManager.h"

using namespace facebook::react;

@interface KlineView () <RCTKlineViewViewProtocol>

@end

@implementation KlineView {
    UIView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<KlineViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const KlineViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];

    self.contentView = _view;
  }
  _klineWidth = _view.bounds.size.width;
  _klineHeight = _view.bounds.size.height;
  if(_klineCharView == nil) {
    _klineCharView = [[KLineChartView alloc] initWithFrame:_view.bounds];
    _klineCharView.delegate = self;
  }
  [KLineStateManager manager].klineChart = self.klineCharView;
  [_view addSubview:self.klineCharView];
  return self;
}

- (void)onSlidLeft
{
  if(_eventEmitter == nil) return;
  const auto &defaultEvent = *std::static_pointer_cast<KlineViewEventEmitter const>(_eventEmitter);
  defaultEvent.onSlidLeft({});
}

- (void)onSlidRight
{
  if(_eventEmitter == nil) return;
  const auto &defaultEvent = *std::static_pointer_cast<KlineViewEventEmitter const>(_eventEmitter);
  defaultEvent.onSlidRight({});
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
  const auto &oldViewProps = *std::static_pointer_cast<KlineViewProps const>(_props);
  const auto &newViewProps = *std::static_pointer_cast<KlineViewProps const>(props);
  if (_klineWidth != _view.bounds.size.width || _klineHeight != _view.bounds.size.height) {
    _klineWidth = _view.bounds.size.width;
    _klineHeight = _view.bounds.size.height;
    _klineCharView.frame = _view.bounds;
  }
  // K线与分时线切换
  if (oldViewProps.kLineState != newViewProps.kLineState) {
    switch (newViewProps.kLineState) {
      case 4103:
        // 分时图
        [KLineStateManager manager].isLine = NO;
        break;
      default:
        [KLineStateManager manager].isLine = YES;
        break;
    }
  }
  // 主图MA/BOLL切换
  if (oldViewProps.mainDrawType != newViewProps.mainDrawType) {
    switch (newViewProps.mainDrawType) {
      case 1002:
        [KLineStateManager manager].mainState = MainStateBOLL;
        break;
      case 1003:
        [KLineStateManager manager].mainState = MainStateNONE;
        break;
      default:
        [KLineStateManager manager].mainState = MainStateMA;
        break;
    }
  }
  // 子图指标图切换
  if (oldViewProps.indexdDrawType != newViewProps.indexdDrawType) {
    switch (newViewProps.indexdDrawType) {
      case 4002:
        [KLineStateManager manager].secondaryState = SecondaryStateMacd;
        break;
      case 4003:
        [KLineStateManager manager].secondaryState = SecondaryStateKDJ;
        break;
      case 4004:
        [KLineStateManager manager].secondaryState = SecondaryStateRSI;
        break;
      case 4005:
        [KLineStateManager manager].secondaryState = SecondaryStateWR;
        break;
      default:
        [KLineStateManager manager].secondaryState = SecondaryStateNONE;
        break;
    }
  }
  // 设置十字线触发模式 ⚠️暂时忽略 selectedTouchModle
  // 主视图所占高度比例 占比,用0-1之间的数表示 ⚠️暂时忽略 mainPresent
  // 成交量视图所占高度比例 占比,用0-1之间的数表示 ⚠️暂时忽略 volPresent
  // 切换显示/隐藏交易量
  if (oldViewProps.volShowState != newViewProps.volShowState) {
    if (newViewProps.volShowState == NO) {
      _klineCharView.volState = VolStateNONE;
    } else {
      _klineCharView.volState = VolStateVOL;
    }
  }
  // 设置交易量绘制 BarChart/LineChart ⚠️暂时忽略 volChartStatues
  // 是否隐藏信息框 默认false ⚠️暂时忽略 hideMarketInfoBox
  // 网格列
  if (oldViewProps.gridColumns != newViewProps.gridColumns) {
    _klineCharView.gridColumns = newViewProps.gridColumns;
  }
  // 网格行
  if (oldViewProps.gridRows != newViewProps.gridRows) {
    _klineCharView.gridRows = newViewProps.gridRows;
  }
  // Slide left K line inside indent width ⚠️暂时忽略 overScrollRange
  // Y值精度格式化
  if (oldViewProps.valueFormatter != newViewProps.valueFormatter) {
    if (newViewProps.valueFormatter.size() != 0) {
      NSString * fromat = [[NSString alloc] initWithUTF8String: newViewProps.valueFormatter.c_str()];
      _klineCharView.valueFormatter = fromat;
    }
  }
  // 成交量格式化
  if (oldViewProps.volFormatter != newViewProps.volFormatter) {
    if (newViewProps.volFormatter.size() != 0) {
      NSString * fromat = [[NSString alloc] initWithUTF8String: newViewProps.volFormatter.c_str()];
      _klineCharView.volFormatter = fromat;
    }
  }
  // 时间格式化
  if (oldViewProps.dateTimeFormatter != newViewProps.dateTimeFormatter) {
    if (newViewProps.dateTimeFormatter.size() != 0) {
      NSString * fromat = [[NSString alloc] initWithUTF8String: newViewProps.dateTimeFormatter.c_str()];
      _klineCharView.dateTimeFormatter = fromat;
    }
  }
  // 主视图Formatte
  if (oldViewProps.mainValueFormatter != newViewProps.mainValueFormatter) {
    if (newViewProps.mainValueFormatter.size() != 0) {
      NSString * fromat = [[NSString alloc] initWithUTF8String: newViewProps.mainValueFormatter.c_str()];
      _klineCharView.mainValueFormatter = fromat;
    }
  }
  // 设置选中X轴坐标背景色 ⚠️暂时忽略 selectedXLabelBackgroundColor
  // 设置价格线上的文字颜色 ⚠️暂时忽略 priceLabelInLineTextColor
  // 设置价格线上的文字大小 ⚠️暂时忽略 priceLabelInLineTextSize
  // 设置选中X坐标文字颜色 ⚠️暂时忽略 selectedLabelTextColor
  // 设置选中X坐标文字大小 ⚠️暂时忽略 selectedLabelTextSize
  // 设置价格线上价格框离右距离 ⚠️暂时忽略 priceLabelInLineBoxMarginRight
  // 价格线上价格图形宽 ⚠️暂时忽略 priceLabelInLineShapeWidth
  // 价格线上价格图形高 ⚠️暂时忽略 priceLabelInLineShapeHeight
  // 设置价格线上价格框高度 ⚠️暂时忽略 priceLabelInLineBoxHeight
  // 设置价格线上价格框内边距 ⚠️暂时忽略 priceLabelInLineBoxPadding
  // 设置价格线价格框可点击 ⚠️暂时忽略 priceLabelInLineClickable
  // 设置右侧价格框背景色 ⚠️暂时忽略 priceLabelInLineBoxBackgroundColor
  // 设置价格线右侧框背景 ⚠️暂时忽略 priceLabelRightBackgroundColor
  // 设置价格线右侧框边框颜色 ⚠️暂时忽略 priceLabelInLineBoxBorderColor
  // 设置价格线框边框宽度 ⚠️暂时忽略 priceLabelInLineBoxBorderWidth
  // 设置价格线上价格框圆角半径 ⚠️暂时忽略 priceLabelInLineBoxRadius
  // 设置价格线右侧价格文字的颜色 ⚠️暂时忽略 priceLabelRightTextColor
  // 设置价格线右侧的颜色 ⚠️暂时忽略 priceLineRightColor
  // 设置价格线的宽度 ⚠️暂时忽略 priceLineWidth
  // 设置显示价格线 ⚠️暂时忽略 showPriceLine
  // 设置显示价格线上的价格 ⚠️暂时忽略 showPriceLabelInLine
  // 设置价格线颜色 ⚠️暂时忽略 priceLineColor
  // 价格线虚线实心宽度 ⚠️暂时忽略 priceLineDotSolidWidth
  // 价格线实心间隔 ⚠️暂时忽略 priceLineDotStrokeWidth
  // 显示右侧虚线和价格 ⚠️暂时忽略 showRightDotPriceLine
  // 设置选择器横线宽 ⚠️暂时忽略 selectedXLineWidth
  // 设置十字线竖线宽度 ⚠️暂时忽略 selectedYLineWidth
  // 设置十字线横线颜色 ⚠️暂时忽略 selectedXLineColor
  // 设置十字线竖线画笔颜色 ⚠️暂时忽略 selectedYLineColor
  // 选中的线的Y轴颜色 ⚠️暂时忽略 selectedYColor
  // 设置都十字线选中点外圆颜色 ⚠️暂时忽略 selectedCrossBigColor
  // 设置选中时是否显示十字线的交点圆 ⚠️暂时忽略 selectedShowCrossPoint
  // 设置选中Y值背景色 ⚠️暂时忽略 selectedPriceBoxBackgroundColor
  // 设置选择器文字大小 ⚠️暂时忽略 selectedInfoTextSize
  // 选中时价格label的横向padding ⚠️暂时忽略 selectedPriceBoxHorizontalPadding
  // 选中时价格label的纵向padding ⚠️暂时忽略 selectedPriceBoxVerticalPadding
  // 选中信息框内边距,上下为此值*2 ⚠️暂时忽略 selectedInfoBoxPadding
  // 选中行弹出框与边缘的距离 ⚠️暂时忽略 selectedInfoBoxMargin
  // 设置选择器弹出框 ⚠️暂时忽略 selectedInfoBox
  // 选中时X坐标边框线宽 ⚠️暂时忽略 selectedLabelBorderWidth
  // 选中时X坐标边框线颜色 ⚠️暂时忽略 selectedLabelBorderColor
  // 设置选中框的文本 ⚠️暂时忽略 selectedInfoLabels
  // 设置十字线跟随手势移动/显示收盘价 ⚠️暂时忽略 crossFollowTouch
  // 设置y轴上Label与视图右边距 ⚠️暂时忽略 yLabelMarginBorder
  // 设置背景色顶部颜色
  if (oldViewProps.backgroundFillTopColor != newViewProps.backgroundFillTopColor) {
    if (newViewProps.backgroundFillTopColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.backgroundFillTopColor.c_str()];
      _klineCharView.backgroundFillTopColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 设置背景色底部颜色
  if (oldViewProps.backgroundFillBottomColor != newViewProps.backgroundFillBottomColor) {
    if (newViewProps.backgroundFillBottomColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.backgroundFillBottomColor.c_str()];
      _klineCharView.backgroundFillBottomColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 设置分时线颜色
  if (oldViewProps.timeLineColor != newViewProps.timeLineColor) {
    if (newViewProps.timeLineColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.timeLineColor.c_str()];
      _klineCharView.timeLineColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 设置分时线填充渐变的顶部颜色
  if (oldViewProps.timeLineFillTopColor != newViewProps.timeLineFillTopColor) {
    if (newViewProps.timeLineFillTopColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.timeLineFillTopColor.c_str()];
      _klineCharView.timeLineFillTopColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 设置分时线填充渐变的底部颜色
  if (oldViewProps.timeLineFillBottomColor != newViewProps.timeLineFillBottomColor) {
    if (newViewProps.timeLineFillBottomColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.timeLineFillBottomColor.c_str()];
      _klineCharView.timeLineFillBottomColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 分时线呼吸灯的颜色
  if (oldViewProps.timeLineEndPointColor != newViewProps.timeLineEndPointColor) {
    if (newViewProps.timeLineEndPointColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.timeLineEndPointColor.c_str()];
      _klineCharView.timeLineEndPointColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 分时线呼吸灯的颜色半径
  if (oldViewProps.timeLineEndRadius != newViewProps.timeLineEndRadius) {
    _klineCharView.timeLineEndRadius = newViewProps.timeLineEndRadius;
  }
  // timeLineEndMultiply ⚠️暂时忽略
  // 选中十字线X轴坐标连框纵向内边距 ⚠️暂时忽略 selectedDateBoxVerticalPadding
  // 选中十字线X轴坐标连框横向内边距 ⚠️暂时忽略 selectedDateBoxHorizontalPadding
  // 设置主实图图例距离视图上边缘的距离 ⚠️暂时忽略 mainLegendMarginTop
  // 设置图例距离视图左边缘的距离 ⚠️暂时忽略 legendMarginLeft
  // 设置涨的颜色
  if (oldViewProps.increaseColor != newViewProps.increaseColor) {
    if (newViewProps.increaseColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.increaseColor.c_str()];
      _klineCharView.increaseColor = [self hexStringToColor:colorToConvert];
    }
  }
  // 设置跌的颜色
  if (oldViewProps.decreaseColor != newViewProps.decreaseColor) {
    if (newViewProps.decreaseColor.size() != 0) {
      NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.decreaseColor.c_str()];
      _klineCharView.decreaseColor = [self hexStringToColor:colorToConvert];
    }
  }
  [super updateProps:props oldProps:oldProps];
}

Class<RCTComponentViewProtocol> KlineViewCls(void)
{
    return KlineView.class;
}

- hexStringToColor:(NSString *)stringToConvert
{
    NSString *noHashString = [stringToConvert stringByReplacingOccurrencesOfString:@"#" withString:@""];
    NSScanner *stringScanner = [NSScanner scannerWithString:noHashString];

    unsigned hex;
    if (![stringScanner scanHexInt:&hex]) return nil;
    int r = (hex >> 16) & 0xFF;
    int g = (hex >> 8) & 0xFF;
    int b = (hex) & 0xFF;

    return [UIColor colorWithRed:r / 255.0f green:g / 255.0f blue:b / 255.0f alpha:1.0f];
}

@end
