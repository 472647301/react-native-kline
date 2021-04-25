
#import "ByronController.h"
#import "DataUtil.h"
#import "KLineChartView.h"
#import "KLineStateManager.h"
#import "KLineModel.h"
#import "KLineChart/Style/ChartStyle.h"

@implementation ByronController

- (instancetype)initWithFrame:(CGRect)frame {
    return [super initWithFrame:frame];
}

- (void)initChartView {
    _chartView = [[KLineChartView alloc] initWithFrame:self.bounds];
    _chartView.delegate = self;
    [KLineStateManager manager].klineChart = _chartView;
    [self initKLineState];
    [self setDatas:_datas];
    [self setLocales:_locales];
    [self setIndicators:_indicators];
    [self setPricePrecision:_pricePrecision];
    [self setVolumePrecision:_volumePrecision];
    [self setIncreaseColor:_increaseColor];
    [self setDecreaseColor:_decreaseColor];
    [_chartView setMainBackgroundColor:_mainBackgroundColor];
    [self addSubview:_chartView];
}

- (void)addHeaderData:(NSArray *)list {
    if (_chartView == nil) {
        return;
    }
    KLineModel * item = [[KLineModel alloc] initWithDict:list.firstObject];
    KLineModel *model = [KLineStateManager manager].datas.firstObject;
    if(model != nil && [KLineStateManager manager].datas.count > 1) {
        KLineModel *last1 = [KLineStateManager manager].datas.firstObject;
        KLineModel * last2 = [[KLineStateManager manager].datas objectAtIndex:1];
        int differ = last1.id - last2.id;
        int limit = item.id - last1.id;
        KLineModel *kLineEntity = [[KLineModel alloc] init];
        kLineEntity.open = item.open;
        kLineEntity.high = item.high;
        kLineEntity.low = item.low;
        kLineEntity.close = item.close;
        kLineEntity.vol = item.vol;
        kLineEntity.amount = item.amount;
        if (limit < differ) {
            kLineEntity.id = model.id;
            NSArray  *models = [KLineStateManager manager].datas;
            NSMutableArray *newDatas = [NSMutableArray arrayWithArray:models];
            [newDatas replaceObjectAtIndex:0 withObject:kLineEntity];
            if ([KLineStateManager manager].isLine) {
                [DataUtil addLastData:models data:kLineEntity];
            } else {
                [DataUtil calculate:newDatas];
            }
            [KLineStateManager manager].datas = [newDatas copy];
        } else {
            kLineEntity.id = item.id;
            NSArray  *models = [KLineStateManager manager].datas;
            NSMutableArray *newDatas = [NSMutableArray arrayWithArray:models];
            [newDatas insertObject:kLineEntity atIndex:0];
            if ([KLineStateManager manager].isLine) {
                [DataUtil addLastData:models data:kLineEntity];
            } else {
                [DataUtil calculate:newDatas];
            }
            [KLineStateManager manager].datas = [newDatas copy];
        }
    }
}

- (void)addFooterData:(NSArray *)list {
    if (_chartView == nil) {
        return;
    }
    NSArray  *models = [KLineStateManager manager].datas;
    NSMutableArray *newDatas = [NSMutableArray arrayWithArray:models];
    NSArray *newList = [[list reverseObjectEnumerator] allObjects];
    for (int i = 0; i < newList.count; i++) {
        NSDictionary *item = newList[i];
        [newDatas addObject:[[KLineModel alloc] initWithDict:item]];
    }
    [DataUtil calculate:newDatas];
    [KLineStateManager manager].datas = [newDatas copy];
    if (_requestStatus == YES) {
        _requestStatus = NO;
    }
}

- (void)setDatas:(NSArray *)datas {
    _datas = datas;
    if (_chartView == nil || _datas.count < 1) {
        return;
    }
    NSArray *newDatas = [[datas reverseObjectEnumerator] allObjects];
    NSMutableArray *list = [NSMutableArray arrayWithCapacity:newDatas.count];
    for (int i = 0; i < newDatas.count; i++) {
      NSDictionary *item = newDatas[i];
      [list addObject:[[KLineModel alloc] initWithDict:item]];
    }
    [DataUtil calculate:list];
    [KLineStateManager manager].datas = list;
}

- (void)setLocales:(NSArray *)locales {
    _locales = locales;
    if (_chartView == nil || _locales.count < 1) {
        return;
    }
    [KLineStateManager manager].locales = locales;
}

- (void)setIndicators:(NSArray *)indicators {
    _indicators = indicators;
    if (_chartView == nil) {
        return;
    }
    if (indicators.count == 0) {
        [self initKLineState];
        return;
    }
    for (int i = 0; i < indicators.count; i++) {
      NSNumber *item = indicators[i];
      [self changeKLineState:item];
    }
}

- (void)setPricePrecision:(NSNumber *)pricePrecision {
    if (pricePrecision == nil) {
        return;
    }
    _pricePrecision = pricePrecision;
    if (_chartView == nil) {
        return;
    }
    [KLineStateManager manager].pricePrecision = pricePrecision;
}

- (void)setVolumePrecision:(NSNumber *)volumePrecision {
    if (volumePrecision == nil) {
        return;
    }
    _volumePrecision = volumePrecision;
    if (_chartView == nil) {
        return;
    }
    [KLineStateManager manager].volumePrecision = volumePrecision;
}

- (void)onMoreKLineData {
    if (self.onRNMoreKLineData == nil) {
        return;
    }
    if (_requestStatus == YES) {
        return;
    }
    _requestStatus = YES;
    KLineModel *model = [KLineStateManager manager].datas.lastObject;
    self.onRNMoreKLineData(@{@"id":@(model.id)});
}

- (void)initKLineState {
    if (_chartView == nil) {
      return;
    }
    [KLineStateManager manager].mainState = MainStateNONE;
    [KLineStateManager manager].secondaryState = SecondaryStateNONE;
    [KLineStateManager manager].isLine = NO;
//    _chartView.volState = VolStateNONE;
}

- (void)changeKLineState:(NSNumber *)index {
  if (_chartView == nil) {
    return;
  }
  int state = [(index) intValue];
  switch (state) {
      // 显示MA
    case 0:
      [KLineStateManager manager].mainState = MainStateMA;
    break;
      // 显示BOLL
    case 1:
      [KLineStateManager manager].mainState = MainStateBOLL;
    break;
    // 隐藏主图指标
    case 2:
      [KLineStateManager manager].mainState = MainStateNONE;
    break;
    // 显示MACD
    case 3:
      [KLineStateManager manager].secondaryState = SecondaryStateMacd;
    break;
    // 显示KDJ
    case 4:
      [KLineStateManager manager].secondaryState = SecondaryStateKDJ;
    break;
    // 显示RSI
    case 5:
      [KLineStateManager manager].secondaryState = SecondaryStateRSI;
    break;
    // 显示WR
    case 6:
      [KLineStateManager manager].secondaryState = SecondaryStateWR;
    break;
    // 隐藏副图
    case 7:
      [KLineStateManager manager].secondaryState = SecondaryStateNONE;
    break;
    case 8:
      [KLineStateManager manager].isLine = YES;
    break;
    case 9:
      [KLineStateManager manager].isLine = NO;
    break;
    case 10: // 显示成交量
      _chartView.volState = VolStateVOL;
    break;
    case 11: // 隐藏成交量
      _chartView.volState = VolStateNONE;
    break;
    default:
    break;
  }
}

- (void)setMainBackgroundColor:(NSString *)mainBackgroundColor {
    _mainBackgroundColor = mainBackgroundColor;
    [_chartView setMainBackgroundColor:_mainBackgroundColor];
}


@end
