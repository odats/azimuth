//
//  AZPositionState.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AZPositionState.h"

@implementation AZPositionState

@synthesize startingSpeedX;
@synthesize startingSpeedY;
@synthesize coordinateX;
@synthesize coordinateY;
@synthesize azimuth;

- (id)init
{
    self = [super init];
    if (self) {
        // Initialization code here.
        startingSpeedX = 0;
        startingSpeedY = 0;
        coordinateX = 0;
        coordinateY = 0;
        azimuth = 30;
    }
    
    return self;
}

@end
