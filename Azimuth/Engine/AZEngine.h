//
//  AZEngine.h
//  Azimuth
//
//  Created by Роман Романчук on 22.09.11.
//  Copyright 2011 Epam. All rights reserved.
//

#import <Foundation/Foundation.h>

@class AZPositionState;

@interface AZEngine : NSObject

+(AZPositionState *)calculateWith:(float)ax andWith:(float)ay andAzimuth:(float)azimuth andInterval:(float) timeDiff andPreviousState:(AZPositionState *)previousState;

@end
