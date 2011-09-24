//
//  AZEngine.h
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import <Foundation/Foundation.h>

@class AZPositionState;

@interface AZEngine : NSObject

+(AZPositionState *)calculateWith:(double)ax andWith:(double)ay andAzimuth:(double)azimuth andInterval:(double) timeDiff andPreviousState:(AZPositionState *)previousState;

@end
