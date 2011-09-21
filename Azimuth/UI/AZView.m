//
//  AzimuthView.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AZView.h"
#import "AZPoint.h"

@implementation AZView

@synthesize coordinates;

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        coordinates = [NSArray array];
        // Initialization code
    }
    return self;
}


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    if ([coordinates count] > 0) {
        AZPoint *currentPoint;
        currentPoint = [coordinates objectAtIndex:0];
        
        CGContextRef context = UIGraphicsGetCurrentContext();
        CGContextSetStrokeColorWithColor(context, [UIColor redColor].CGColor);
    
        // Draw them with a 1.0 stroke width so they are a bit more visible.
        CGContextSetLineWidth(context, 1.0);
        
        CGContextMoveToPoint(context, currentPoint.x, currentPoint.y); //start at this point

        for (int i=1; i < [coordinates count]; i++) {
            currentPoint = [coordinates objectAtIndex:i];
            CGContextAddLineToPoint(context, currentPoint.x, currentPoint.y); //draw to this point
        }
        
        // and now draw the Path!
        CGContextStrokePath(context);
    }
}


@end
