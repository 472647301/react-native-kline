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
    [datas addObject:[[KLineModel alloc] initWithDict:item]];
  }
  [DataUtil calculate:datas];
  [KLineStateManager manager].datas = datas;
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeKlineAdapterSpecJSI>(params);
}

@end
