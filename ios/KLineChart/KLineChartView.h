//
//  KLineChartView.h
//  KLine-Chart-OC
//
//  Created by 何俊松 on 2020/3/10.
//  Copyright © 2020 hjs. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "KLineModel.h"
#import "KLineState.h"

NS_ASSUME_NONNULL_BEGIN

@protocol KLineDelegate <NSObject>

@required
- (void) onMoreKLineData;

@end

@interface KLineChartView : UIView

@property (nonatomic, weak) id<KLineDelegate> delegate;

@property(nonatomic,strong) NSArray<KLineModel *> *datas;

@property(nonatomic,assign) CGFloat scrollX;

@property(nonatomic,assign) CGFloat startX;

@property(nonatomic,assign) BOOL isLine;

@property(nonatomic,assign) CGFloat scaleX;

@property(nonatomic,assign) BOOL isLongPress;

@property(nonatomic,assign) CGFloat longPressX;

@property(nonatomic,assign) MainState mainState;

@property(nonatomic,assign) VolState volState;

@property(nonatomic,assign) SecondaryState secondaryState;

@property(nonatomic,assign) KLineDirection direction;

- (void) setMainBackgroundColor:(NSString *)mainBackgroundColor;

@end

NS_ASSUME_NONNULL_END
