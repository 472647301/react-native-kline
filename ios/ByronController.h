

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

@property (nonatomic, assign) BOOL requestStatus;

@property(nonatomic,strong) KLineChartView *chartView;

@end
