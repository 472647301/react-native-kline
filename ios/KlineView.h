#import <React/RCTViewComponentView.h>
#import <UIKit/UIKit.h>

#import "KLineChartView.h"

#ifndef KlineViewNativeComponent_h
#define KlineViewNativeComponent_h

NS_ASSUME_NONNULL_BEGIN

@interface KlineView : RCTViewComponentView <KLineDelegate>

@property(nonatomic,strong) KLineChartView *klineCharView;

@property(nonatomic,assign) CGFloat klineHeight;
@property(nonatomic,assign) CGFloat klineWidth;

@end

NS_ASSUME_NONNULL_END

#endif /* KlineViewNativeComponent_h */
