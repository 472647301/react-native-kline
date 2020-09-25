
#import "ByronController.h"
#import "DataUtil.h"
#import "KLineChartView.h"
#import "KLineStateManager.h"
#import "KLineModel.h"

@implementation ByronController

- (instancetype)initWithFrame:(CGRect)frame {
    return [super initWithFrame:frame];
}

- (void)initChartView {
    _chartView = [[KLineChartView alloc] initWithFrame:self.bounds];
    _chartView.delegate = self;
    [KLineStateManager manager].klineChart = _chartView;
    [KLineStateManager manager].mainState = MainStateNONE;
    [KLineStateManager manager].secondaryState = SecondaryStateNONE;
    if (_datas != nil) {
        [self setDatas:_datas];
    }
    if (_locales != nil) {
        [self setLocales:_locales];
    }
    if (_indicators != nil) {
        [self setIndicators:_indicators];
    }
    if (_pricePrecision != nil) {
        [self setPricePrecision:_pricePrecision];
    }
    if (_volumePrecision != nil) {
        [self setVolumePrecision:_volumePrecision];
    }
    [self addSubview:_chartView];
}

- (void)addHeaderData:(NSArray *)list {
    if (_chartView == nil) {
        return;
    }
    if (_requestStatus == YES) {
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
        kLineEntity.count = item.count;
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
    if (_chartView == nil) {
        return;
    }
    NSMutableArray *list = [NSMutableArray arrayWithCapacity:datas.count];
    for (int i = 0; i < datas.count; i++) {
      NSDictionary *item = datas[i];
      [list addObject:[[KLineModel alloc] initWithDict:item]];
    }
    [DataUtil calculate:list];
    [KLineStateManager manager].datas = [[list reverseObjectEnumerator] allObjects];
}

- (void)setLocales:(NSArray *)locales {
    _locales = locales;
    if (_chartView == nil) {
        return;
    }
    [KLineStateManager manager].locales = locales;
}

- (void)setIndicators:(NSArray *)indicators {
    _indicators = indicators;
    if (_chartView == nil) {
        return;
    }
    for (int i = 0; i < indicators.count; i++) {
      NSNumber *item = indicators[i];
      [self changeKLineState:item];
    }
}

- (void)setPricePrecision:(NSNumber *)pricePrecision {
    _pricePrecision = pricePrecision;
    if (_chartView == nil) {
        return;
    }
    [KLineStateManager manager].pricePrecision = pricePrecision;
}

- (void)setVolumePrecision:(NSNumber *)volumePrecision {
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

@end
