//
//  AZPoint.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AZPoint.h"

@implementation AZPoint

@synthesize x;
@synthesize y;

- (id)initWithX:(double) xCoord andY:(double) yCoord
{
    self = [super init];
    if (self) {
        // Initialization code here.
        x = xCoord;
        y = yCoord;
    }
    
    return self;
}

@end
