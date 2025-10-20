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
- (void) onSlidLeft;
- (void) onSlidRight;

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

@property(nonatomic,assign) NSInteger gridColumns;

@property(nonatomic,assign) NSInteger gridRows;

@property(nonatomic,strong) UIColor* backgroundFillTopColor;

@property(nonatomic,strong) UIColor* backgroundFillBottomColor;

@property(nonatomic,strong) UIColor* timeLineColor;

@property(nonatomic,strong) UIColor* timeLineFillTopColor;

@property(nonatomic,strong) UIColor* timeLineFillBottomColor;

@property(nonatomic,strong) UIColor* timeLineEndPointColor;

@property(nonatomic,assign) CGFloat timeLineEndRadius;

@property(nonatomic,strong) UIColor* increaseColor;

@property(nonatomic,strong) UIColor* decreaseColor;

@property(nonatomic,strong) NSString* valueFormatter;

@property(nonatomic,strong) NSString* volFormatter;

@property(nonatomic,strong) NSString* dateTimeFormatter;

@property(nonatomic,strong) NSString* mainValueFormatter;

@property(nonatomic,strong) NSMutableArray* selectedInfoLabels;

@end

NS_ASSUME_NONNULL_END
