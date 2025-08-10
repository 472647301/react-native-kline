#import "KlineAdapter.h"
#import "KLineStateManager.h"
#import "KLineModel.h"
#import "DataUtil.h"

@implementation KlineAdapter
RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

- (void)addNewData:(JS::NativeKlineAdapter::KLineEntity &)data resetShowPosition:(nonnull NSNumber *)resetShowPosition
{
  NSMutableArray *datas = [[KLineStateManager manager].datas mutableCopy];
  KLineModel   *bar = [[KLineModel alloc] init];
  bar.open = data.open();
  bar.high = data.high();
  bar.low = data.low();
  bar.close = data.close();
  bar.vol = data.vol();
  bar.id = data.id_();
  [datas insertObject:bar atIndex:0];
  [DataUtil calculate:datas];
  [[KLineStateManager manager] setDatas:datas];
}

- (void)changeItem:(NSInteger)position data:(JS::NativeKlineAdapter::KLineEntity &)data
{
  NSMutableArray *datas = [[KLineStateManager manager].datas mutableCopy];
  KLineModel   *bar = [[KLineModel alloc] init];
  bar.open = data.open();
  bar.high = data.high();
  bar.low = data.low();
  bar.close = data.close();
  bar.vol = data.vol();
  bar.id = data.id_();
  // ios 需要倒过来
  NSArray *tempReversedArray = [[datas reverseObjectEnumerator] allObjects];
  [datas setArray:tempReversedArray];
  [datas replaceObjectAtIndex:position withObject: bar];
  NSArray *dataReversedArray = [[datas reverseObjectEnumerator] allObjects];
  [datas setArray:dataReversedArray];
  [DataUtil calculate:datas];
  [[KLineStateManager manager] setDatas:datas];
}

- (void)resetData:(nonnull NSArray *)list resetShowPosition:(nonnull NSNumber *)resetShowPosition resetLastAnim:(nonnull NSNumber *)resetLastAnim
{
  NSMutableArray *datas = [[NSMutableArray alloc] init];
  for (int i = 0; i < list.count; i++) {
    NSDictionary *item = list[i];
    KLineModel   *bar = [[KLineModel alloc] init];
    bar.open = [item[@"open"] floatValue];
    bar.high = [item[@"high"] floatValue];
    bar.low = [item[@"low"] floatValue];
    bar.close = [item[@"close"] floatValue];
    bar.vol = [item[@"vol"] floatValue];
    bar.id = [item[@"id"] doubleValue];
    [datas addObject:bar];
  }
  // iOS 需要倒序
  NSArray *tempReversedArray = [[datas reverseObjectEnumerator] allObjects];
  [datas setArray:tempReversedArray];
  [DataUtil calculate:datas];
  [[KLineStateManager manager] setDatas:datas];
}

- (void)addHistoryData:(NSArray *)list resetShowPosition:(nonnull NSNumber *)resetShowPosition
{
  NSMutableArray *datas = [[NSMutableArray alloc] init];
  for (int i = 0; i < list.count; i++) {
    NSDictionary *item = list[i];
    KLineModel   *bar = [[KLineModel alloc] init];
    bar.open = [item[@"open"] floatValue];
    bar.high = [item[@"high"] floatValue];
    bar.low = [item[@"low"] floatValue];
    bar.close = [item[@"close"] floatValue];
    bar.vol = [item[@"vol"] floatValue];
    bar.id = [item[@"id"] doubleValue];
    [datas addObject:bar];
  }
  // ios 需要倒过来
  NSArray *tempReversedArray = [[datas reverseObjectEnumerator] allObjects];
  [datas setArray:tempReversedArray];
  NSMutableArray *oldDatas = [[KLineStateManager manager].datas mutableCopy];
  [oldDatas addObjectsFromArray:datas];
  [DataUtil calculate:oldDatas];
  [[KLineStateManager manager] setDatas:oldDatas];
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeKlineAdapterSpecJSI>(params);
}

@end
