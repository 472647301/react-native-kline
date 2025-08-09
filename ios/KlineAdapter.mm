#import "KlineAdapter.h"
#import "KLineStateManager.h"
#import "KLineModel.h"
#import "DataUtil.h"

@implementation KlineAdapter
RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}


- (void)addLast:(JS::NativeKlineAdapter::KLineEntity &)data resetShowPosition:(nonnull NSNumber *)resetShowPosition { 
  
}

- (void)changeItem:(NSInteger)position data:(JS::NativeKlineAdapter::KLineEntity &)data { 
  
}

- (void)resetData:(nonnull NSArray *)list resetShowPosition:(nonnull NSNumber *)resetShowPosition resetLastAnim:(nonnull NSNumber *)resetLastAnim { 
  NSArray *newList = [[list reverseObjectEnumerator] allObjects];
  NSMutableArray *datas = [NSMutableArray arrayWithCapacity:newList.count];
  for (int i = 0; i < newList.count; i++) {
    NSDictionary *item = newList[i];
    KLineModel   *bar = [[KLineModel alloc] init];
    bar.open = [item[@"open"] floatValue];
    bar.high = [item[@"high"] floatValue];
    bar.low = [item[@"low"] floatValue];
    bar.close = [item[@"close"] floatValue];
    bar.vol = [item[@"vol"] floatValue];
    bar.id = [item[@"id"] doubleValue];
    [datas addObject:bar];
  }
  [DataUtil calculate:datas];
  [KLineStateManager manager].datas = datas;
}

- (void)appendData:(NSArray *)list {
  NSArray  *models = [KLineStateManager manager].datas;
  NSMutableArray *newDatas = [NSMutableArray arrayWithArray:models];
  NSArray *newList = [[list reverseObjectEnumerator] allObjects];
  for (int i = 0; i < newList.count; i++) {
      NSDictionary *item = newList[i];
      KLineModel   *bar = [[KLineModel alloc] init];
      bar.open = [item[@"open"] floatValue];
      bar.high = [item[@"high"] floatValue];
      bar.low = [item[@"low"] floatValue];
      bar.close = [item[@"close"] floatValue];
      bar.vol = [item[@"vol"] floatValue];
      bar.id = [item[@"id"] doubleValue];
      [newDatas addObject:bar];
  }
  [DataUtil calculate:newDatas];
  [KLineStateManager manager].datas = [newDatas copy];
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeKlineAdapterSpecJSI>(params);
}

@end
