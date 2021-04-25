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

RCT_EXPORT_VIEW_PROPERTY(mainBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedXLabelBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineTextSize, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedLabelTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedLabelTextSize, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxMarginRight, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineShapeWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineShapeHeight, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxHeight, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxShapeTextMargin, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineClickable, BOOL);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLabelRightBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxBorderColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxBorderWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelInLineBoxRadius, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLineRightLabelBackGroundAlpha, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLabelRightTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLineRightColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLineColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(priceLineDotSolidWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(priceLineDotStrokeWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedXLineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedYLineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedXLineColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedYLineColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedYColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedCrossBigColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedCrossPointRadius, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedCrossPointColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedShowCrossPoint, BOOL);
RCT_EXPORT_VIEW_PROPERTY(selectedPriceBoxBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoTextSize, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedPriceBoxHorizontalPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedPriceBoxVerticalPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoBoxPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoBoxMargin, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoBoxTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoBoxBorderColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedInfoBoxBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(selectedLabelBorderWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedLabelBorderColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(backgroundFillTopColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(backgroundFillBottomColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(timeLineColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(timeLineFillTopColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(timeLineFillBottomColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(timeLineEndPointColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(timeLineEndRadius, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedDateBoxVerticalPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(selectedDateBoxHorizontalPadding, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(mainLegendMarginTop, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(legendMarginLeft, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(increaseColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(decreaseColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(betterXLabel, BOOL);
RCT_EXPORT_VIEW_PROPERTY(labelTextSize, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(labelTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(yLabelAlign, BOOL);
RCT_EXPORT_VIEW_PROPERTY(betterSelectedXLabel, BOOL);
RCT_EXPORT_VIEW_PROPERTY(commonTextSize, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(mainMarginTop, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(paddingBottom, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(childPaddingTop, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(commonTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(lineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(candleWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(candleLineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(limitTextColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(candleHollow, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(gridLineWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(gridLineColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(gridLineRows, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(gridLineColumns, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(macdStrokeWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(macdIncreaseColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(macdDecreaseColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(macdWidth, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(difColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(deaColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(macdColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(wr1Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(wr2Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(wr3Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(kColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(dColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(jColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(rsi1Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(rsi2Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(rsi3Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(ma1Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(ma2Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(ma3Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(volMa1Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(volMa2Color, NSString);
RCT_EXPORT_VIEW_PROPERTY(volLegendColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(volLineChartColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(volLegendMarginTop, NSNumber);

@end
