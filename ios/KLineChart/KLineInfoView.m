//
//  KLineInfoView.m
//  KLine-Chart-OC
//
//  Created by 何俊松 on 2020/3/10.
//  Copyright © 2020 hjs. All rights reserved.
//

#import "KLineInfoView.h"
#import "ChartStyle.h"
#import "KLineStateManager.h"

@interface KLineInfoView()
@property (weak, nonatomic) IBOutlet UILabel *timeLable;

@property (weak, nonatomic) IBOutlet UILabel *openLable;
@property (weak, nonatomic) IBOutlet UILabel *highLable;
@property (weak, nonatomic) IBOutlet UILabel *lowLabel;
@property (weak, nonatomic) IBOutlet UILabel *clsoeLabel;
@property (weak, nonatomic) IBOutlet UILabel *IncreaseLabel;
@property (weak, nonatomic) IBOutlet UILabel *amplitudeLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountLable;

@property (weak, nonatomic) IBOutlet UILabel *timeText;

@property (weak, nonatomic) IBOutlet UILabel *openText;
@property (weak, nonatomic) IBOutlet UILabel *highText;
@property (weak, nonatomic) IBOutlet UILabel *lowText;
@property (weak, nonatomic) IBOutlet UILabel *clsoeText;
@property (weak, nonatomic) IBOutlet UILabel *IncreaseText;
@property (weak, nonatomic) IBOutlet UILabel *amplitudeText;
@property (weak, nonatomic) IBOutlet UILabel *amountText;

@end

@implementation KLineInfoView

+(instancetype)lineInfoView {
    NSBundle *bundle = [NSBundle bundleForClass:self.classForCoder];
    KLineInfoView *view = [bundle loadNibNamed:@"KLineInfoView" owner:self options:nil].lastObject;
    view.frame = CGRectMake(0, 0, 120, 145);
    return view;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    self.backgroundColor = ChartColors_bgColor;
    self.layer.borderWidth = 1;
    self.layer.borderColor = ChartColors_gridColor.CGColor;
}

- (void)setSelectedInfoLabels:(NSMutableArray *)selectedInfoLabels {
    if (selectedInfoLabels.count > 6) {
      _timeText.text = selectedInfoLabels[0];
      _openText.text = selectedInfoLabels[1];
      _highText.text = selectedInfoLabels[2];
      _lowText.text = selectedInfoLabels[3];
      _clsoeText.text = selectedInfoLabels[4];
      _IncreaseText.text = selectedInfoLabels[5];
      _amplitudeText.text = selectedInfoLabels[6];
      _amountText.text = selectedInfoLabels[7];
    }
}

- (void)setModel:(KLineModel *)model {
    _model = model;
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.id];
    NSDateFormatter *formater = [[NSDateFormatter alloc] init];
    formater.dateFormat = @"yyyy-MM-dd HH:mm";
    _timeLable.text = [formater stringFromDate:date];
    
    _openLable.text = [NSString stringWithFormat:[KLineStateManager manager].valueFormatter,model.open];
     _highLable.text = [NSString stringWithFormat:[KLineStateManager manager].valueFormatter,model.high];
     _lowLabel.text = [NSString stringWithFormat:[KLineStateManager manager].valueFormatter,model.low];
     _clsoeLabel.text = [NSString stringWithFormat:[KLineStateManager manager].valueFormatter,model.close];
    CGFloat upDown = model.close - model.open;
    NSString *symbol = @"-";
    if(upDown > 0) {
        symbol = @"+";
        self.IncreaseLabel.textColor = ChartColors_upColor;
        self.amplitudeLabel.textColor = ChartColors_upColor;
    } else {
        self.IncreaseLabel.textColor = ChartColors_dnColor;
        self.amplitudeLabel.textColor = ChartColors_dnColor;
    }
    CGFloat upDownPercent = 0.0;
    if (model.open != 0) {
      upDownPercent = upDown / model.open * 100;
    }
    _IncreaseLabel.text = [NSString stringWithFormat:@"%@%.2f",symbol,ABS(upDown)];
    _amplitudeLabel.text = [NSString stringWithFormat:@"%@%.2f%%",symbol,ABS(upDownPercent)];
    _amountLable.text = [NSString stringWithFormat:[KLineStateManager manager].volFormatter,model.vol];
    
}

@end
