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

+(AZPositionState *)calculateWith:(float)ax andWith:(float)ay andAzimuth:(float)azimuth andInterval:(float) timeDiff andPreviousState:(AZPositionState *)previousState;

@end
