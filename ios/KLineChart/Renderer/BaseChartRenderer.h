//
//  BaseChartRenderer.h
//  KLine-Chart-OC
//
//  Created by 何俊松 on 2020/3/10.
//  Copyright © 2020 hjs. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "KLineModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface BaseChartRenderer : NSObject
@property(nonatomic,assign) CGFloat maxValue;
@property(nonatomic,assign) CGFloat minValue;
@property(nonatomic,assign) CGRect chartRect;
@property(nonatomic,assign) CGFloat candleWidth;
@property(nonatomic,assign) CGFloat scaleY;
@property(nonatomic,assign) CGFloat topPadding;

- (instancetype)initWithMaxValue:(CGFloat)maxValue
                        minValue:(CGFloat)minValue
                       chartRect:(CGRect)chartRect
                     candleWidth:(CGFloat)candleWidth
                      topPadding:(CGFloat)topPadding;

-(void)drawGrid:(CGContextRef)context
       gridRows:(NSUInteger)gridRows
     gridColums:(NSUInteger)gridColums;

-(void)drawRightText:(CGContextRef)context
            gridRows:(NSUInteger)gridRows
          gridColums:(NSUInteger)gridColums
      valueFormatter:(NSString *)valueFormatter
        volFormatter:(NSString *)volFormatter;

-(void)drawTopText:(CGContextRef)context
          curPoint:(KLineModel *)curPoint
mainValueFormatter:(NSString *)mainValueFormatter
      volFormatter:(NSString *)volFormatter;

-(void)drawBg:(CGContextRef)context backgroundFillTopColor:(UIColor *)backgroundFillTopColor backgroundFillBottomColor:(UIColor *) backgroundFillBottomColor;

-(void)drawChart:(CGContextRef)context
        lastPoit:(KLineModel *)lastPoint
        curPoint:(KLineModel *)curPoint
            curX:(CGFloat)curX
   timeLineColor:(UIColor *)timeLineColor
timeLineFillTopColor:(UIColor *) timeLineFillTopColor
timeLineFillBottomColor:(UIColor *) timeLineFillBottomColor
timeLineEndPointColor:(UIColor *) timeLineEndPointColor
timeLineEndRadius:(CGFloat) timeLineEndRadius
   increaseColor:(UIColor *) increaseColor
   decreaseColor:(UIColor *) decreaseColor;

-(void)drawLine:(CGContextRef)context
      lastValue:(CGFloat)lastValue
       curValue:(CGFloat)curValue
           curX:(CGFloat)curX
          color:(UIColor *)color;

-(CGFloat)getY:(CGFloat)value;

//下列提供给子类的工具方法
-(void)drawText:(NSString *)text
        atPoint:(CGPoint)point
       fontSize:(CGFloat)size
      textColor:(UIColor *)color;

-(NSString *)volFormat:(CGFloat)value
          volFormatter:(NSString *)volFormatter;
@end

NS_ASSUME_NONNULL_END
