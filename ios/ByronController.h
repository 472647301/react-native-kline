

#import <UIKit/UIKit.h>

#import <React/RCTComponent.h>
#import "KLineChartView.h"

@interface ByronController : UIView <KLineDelegate>

- (void)initChartView;
- (void)addHeaderData:(NSArray *)list;
- (void)addFooterData:(NSArray *)list;

@property (nonatomic, copy) RCTBubblingEventBlock onRNMoreKLineData;

@property (nonatomic, strong) NSArray *datas;
@property (nonatomic, strong) NSArray *locales;
@property (nonatomic, strong) NSArray *indicators;
@property (nonatomic, strong) NSNumber *pricePrecision;
@property (nonatomic, strong) NSNumber *volumePrecision;
@property (nonatomic, strong) NSString *mainBackgroundColor;
@property (nonatomic, strong) NSString *selectedXLabelBackgroundColor;
@property (nonatomic, strong) NSString *priceLabelInLineTextColor;
@property (nonatomic, strong) NSNumber *priceLabelInLineTextSize;
@property (nonatomic, strong) NSString *selectedLabelTextColor;
@property (nonatomic, strong) NSNumber *selectedLabelTextSize;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxMarginRight;
@property (nonatomic, strong) NSNumber *priceLabelInLineShapeWidth;
@property (nonatomic, strong) NSNumber *priceLabelInLineShapeHeight;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxHeight;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxPadding;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxShapeTextMargin;
@property (nonatomic, assign) BOOL *priceLabelInLineClickable;
@property (nonatomic, strong) NSString *priceLabelInLineBoxBackgroundColor;
@property (nonatomic, strong) NSString *priceLabelRightBackgroundColor;
@property (nonatomic, strong) NSString *priceLabelInLineBoxBorderColor;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxBorderWidth;
@property (nonatomic, strong) NSNumber *priceLabelInLineBoxRadius;
@property (nonatomic, strong) NSNumber *priceLineRightLabelBackGroundAlpha;
@property (nonatomic, strong) NSString *priceLabelRightTextColor;
@property (nonatomic, strong) NSString *priceLineRightColor;
@property (nonatomic, strong) NSNumber *priceLineWidth;
@property (nonatomic, strong) NSString *priceLineColor;
@property (nonatomic, strong) NSNumber *priceLineDotSolidWidth;
@property (nonatomic, strong) NSNumber *priceLineDotStrokeWidth;
@property (nonatomic, strong) NSNumber *selectedXLineWidth;
@property (nonatomic, strong) NSNumber *selectedYLineWidth;
@property (nonatomic, strong) NSString *selectedXLineColor;
@property (nonatomic, strong) NSString *selectedYLineColor;
@property (nonatomic, strong) NSString *selectedYColor;
@property (nonatomic, strong) NSString *selectedCrossBigColor;
@property (nonatomic, strong) NSNumber *selectedCrossPointRadius;
@property (nonatomic, strong) NSString *selectedCrossPointColor;
@property (nonatomic, assign) BOOL *selectedShowCrossPoint;
@property (nonatomic, strong) NSString *selectedPriceBoxBackgroundColor;
@property (nonatomic, strong) NSNumber *selectedInfoTextSize;
@property (nonatomic, strong) NSNumber *selectedPriceBoxHorizontalPadding;
@property (nonatomic, strong) NSNumber *selectedPriceBoxVerticalPadding;
@property (nonatomic, strong) NSNumber *selectedInfoBoxPadding;
@property (nonatomic, strong) NSNumber *selectedInfoBoxMargin;
@property (nonatomic, strong) NSString *selectedInfoBoxTextColor;
@property (nonatomic, strong) NSString *selectedInfoBoxBorderColor;
@property (nonatomic, strong) NSString *selectedInfoBoxBackgroundColor;
@property (nonatomic, strong) NSNumber *selectedLabelBorderWidth;
@property (nonatomic, strong) NSString *selectedLabelBorderColor;
@property (nonatomic, strong) NSString *backgroundFillTopColor;
@property (nonatomic, strong) NSString *backgroundFillBottomColor;
@property (nonatomic, strong) NSString *timeLineColor;
@property (nonatomic, strong) NSString *timeLineFillTopColor;
@property (nonatomic, strong) NSString *timeLineFillBottomColor;
@property (nonatomic, strong) NSString *timeLineEndPointColor;
@property (nonatomic, strong) NSNumber *timeLineEndRadius;
@property (nonatomic, strong) NSNumber *selectedDateBoxVerticalPadding;
@property (nonatomic, strong) NSNumber *selectedDateBoxHorizontalPadding;
@property (nonatomic, strong) NSNumber *mainLegendMarginTop;
@property (nonatomic, strong) NSNumber *legendMarginLeft;
@property (nonatomic, strong) NSString *increaseColor;
@property (nonatomic, strong) NSString *decreaseColor;
@property (nonatomic, assign) BOOL *betterXLabel;
@property (nonatomic, strong) NSNumber *labelTextSize;
@property (nonatomic, strong) NSString *labelTextColor;
@property (nonatomic, assign) BOOL *yLabelAlign;
@property (nonatomic, assign) BOOL *betterSelectedXLabel;
@property (nonatomic, strong) NSNumber *commonTextSize;
@property (nonatomic, strong) NSNumber *mainMarginTop;
@property (nonatomic, strong) NSNumber *paddingBottom;
@property (nonatomic, strong) NSNumber *childPaddingTop;
@property (nonatomic, strong) NSString *commonTextColor;
@property (nonatomic, strong) NSNumber *lineWidth;
@property (nonatomic, strong) NSNumber *candleWidth;
@property (nonatomic, strong) NSNumber *candleLineWidth;
@property (nonatomic, strong) NSString *limitTextColor;
@property (nonatomic, strong) NSNumber *candleHollow;
@property (nonatomic, strong) NSNumber *gridLineWidth;
@property (nonatomic, strong) NSString *gridLineColor;
@property (nonatomic, strong) NSNumber *gridLineRows;
@property (nonatomic, strong) NSNumber *gridLineColumns;
@property (nonatomic, strong) NSNumber *macdStrokeWidth;
@property (nonatomic, strong) NSString *macdIncreaseColor;
@property (nonatomic, strong) NSString *macdDecreaseColor;
@property (nonatomic, strong) NSNumber *macdWidth;
@property (nonatomic, strong) NSString *difColor;
@property (nonatomic, strong) NSString *deaColor;
@property (nonatomic, strong) NSString *macdColor;
@property (nonatomic, strong) NSString *wr1Color;
@property (nonatomic, strong) NSString *wr2Color;
@property (nonatomic, strong) NSString *wr3Color;
@property (nonatomic, strong) NSString *kColor;
@property (nonatomic, strong) NSString *dColor;
@property (nonatomic, strong) NSString *jColor;
@property (nonatomic, strong) NSString *rsi1Color;
@property (nonatomic, strong) NSString *rsi2Color;
@property (nonatomic, strong) NSString *rsi3Color;
@property (nonatomic, strong) NSString *ma1Color;
@property (nonatomic, strong) NSString *ma2Color;
@property (nonatomic, strong) NSString *ma3Color;
@property (nonatomic, strong) NSString *volMa1Color;
@property (nonatomic, strong) NSString *volMa2Color;
@property (nonatomic, strong) NSString *volLegendColor;
@property (nonatomic, strong) NSString *volLineChartColor;
@property (nonatomic, strong) NSNumber *volLegendMarginTop;

@property (nonatomic, assign) BOOL requestStatus;

@property(nonatomic,strong) KLineChartView *chartView;

@end
