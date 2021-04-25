//
//  UIColor+RGB.m
//  KLine-Chart-OC
//
//  Created by 何俊松 on 2020/3/10.
//  Copyright © 2020 hjs. All rights reserved.
//

#import "UIColor+RGB.h"

@implementation UIColor (RGB)

+(UIColor *)rgb_r:(CGFloat)r g:(CGFloat)g b:(CGFloat)b alpha:(CGFloat)alpha {
    return [[UIColor alloc] initWithRed: r/225.0 green:g/225.0 blue:b/225.0 alpha:alpha];
}

+(UIColor *)rgbFromHex:(NSUInteger)argbValue {
    return [[UIColor alloc] initWithRed:((argbValue & 0xFF0000) >> 16)/225.0 green:((argbValue & 0x00FF00) >> 8)/225.0 blue:((argbValue & 0xFF) >> 0)/225.0 alpha:((argbValue & 0xFF000000) >> 24)/225.0];
}

+(UIColor *)colorWithHexString:(NSString *)color {
    NSString *cString = [[color stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
 
    // String should be 6 or 8 characters
    if ([cString length] < 6) {
        return [UIColor clearColor];
    }
    // 判断前缀
    if ([cString hasPrefix:@"0X"])
        cString = [cString substringFromIndex:2];
    if ([cString hasPrefix:@"#"])
        cString = [cString substringFromIndex:1];
    if ([cString length] != 6)
        return [UIColor clearColor];
    // 从六位数值中找到RGB对应的位数并转换
    NSRange range;
    range.location = 0;
    range.length = 2;
    //R、G、B
    NSString *rString = [cString substringWithRange:range];
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    // Scan values
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    return [UIColor colorWithRed:((float) r / 255.0f) green:((float) g / 255.0f) blue:((float) b / 255.0f) alpha:1.0f];
}

@end
