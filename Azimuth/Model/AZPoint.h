//
//  AZPoint.h
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AZPoint : NSObject

@property (assign) double x;
@property (assign) double y;

- (id)initWithX:(double) xCoord andY:(double) yCoord;

@end
