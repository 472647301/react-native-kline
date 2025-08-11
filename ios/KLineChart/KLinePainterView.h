//
//  KLinePainterView.h
//  KLine-Chart-OC
//
//  Created by 何俊松 on 2020/3/10.
//  Copyright © 2020 hjs. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "KLineModel.h"
#import "KLineState.h"

NS_ASSUME_NONNULL_BEGIN

@interface KLinePainterView : UIView

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

@property(nonatomic,strong) NSString* fromat;

@property(nonatomic,strong) UIColor* backgroundFillTopColor;

@property(nonatomic,strong) UIColor* backgroundFillBottomColor;

@property(nonatomic,strong) UIColor* timeLineColor;

@property(nonatomic,strong) UIColor* timeLineFillTopColor;

@property(nonatomic,strong) UIColor* timeLineFillBottomColor;

@property(nonatomic,strong) UIColor* timeLineEndPointColor;

@property(nonatomic,assign) CGFloat timeLineEndRadius;

@property(nonatomic,strong) UIColor* increaseColor;

@property(nonatomic,strong) UIColor* decreaseColor;

@property(nonatomic,copy) void(^showInfoBlock)(KLineModel *model, BOOL isLeft);

- (instancetype)initWithFrame:(CGRect)frame
                        datas:(NSArray<KLineModel *> *)datas
                      scrollX:(CGFloat)scrollX
                       isLine:(BOOL)isLine
                       scaleX:(CGFloat)scaleX
                  isLongPress:(BOOL)isLongPress
                    mainState:(MainState)mainState
               secondaryState:(SecondaryState)secondaryState;

@end

NS_ASSUME_NONNULL_END
