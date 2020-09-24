#import "ByronKline.h"
#import "ByronController.h"

@implementation ByronKline

RCT_EXPORT_MODULE()

- (UIView *)view {
   return [ByronController new];
}

RCT_EXPORT_METHOD(byronController:(nonnull NSNumber *)reactTag commandID:(nonnull NSDictionary*)options) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[ByronController class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting ByronKline, got: %@", view);
        } else {
            dispatch_async(dispatch_get_main_queue(), ^{
                ByronController *bannerView = (ByronController *)viewRegistry[reactTag];
                NSArray *list = options[@"list"];
                NSString *event = options[@"event"];
                if ([@"init" isEqualToString:event]) {
                    [bannerView initChartView];
                }
                if ([@"update" isEqualToString:event]) {
                    [bannerView addHeaderData:list];
                }
                if ([@"add" isEqualToString:event]) {
                    [bannerView addFooterData:list];
                }
            });
        }
    }];
}

RCT_EXPORT_VIEW_PROPERTY(datas, NSArray);
RCT_EXPORT_VIEW_PROPERTY(locales, NSArray);
RCT_EXPORT_VIEW_PROPERTY(indicators, NSArray);
RCT_EXPORT_VIEW_PROPERTY(pricePrecision, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(volumePrecision, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(onRNMoreKLineData, RCTBubblingEventBlock);

@end
