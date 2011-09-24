//
//  AZEngine.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AZEngine.h"
#import "AZPositionState.h"

@interface AZEngine ()
+(double)getDistanceProection:(double) startingSpeed andTime:(double) t andAcceleration:(double) acceleration;
+(double)getXCoordinate:(double) coordinate andAzimuth:(double)azimuth andProjection:(double) projection;
+(double)getYCoordinate:(double) coordinate andAzimuth:(double)azimuth andProjection:(double) projection;
+(double)getEndSpeed:(double) speed andTime:(double)t andAcceleration:(double)acceleration;
@end

@implementation AZEngine

- (id)init
{
    self = [super init];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

+(AZPositionState *)calculateWith:(double)ax andWith:(double)ay andAzimuth:(double)azimuth andInterval:(double) timeDiff andPreviousState:(AZPositionState *)previousState {
    AZPositionState *currentState = [[[AZPositionState alloc] init] autorelease];
    [currentState setAzimuth:azimuth];
    double distanceProectionX = [self getDistanceProection:previousState.startingSpeedX andTime:timeDiff andAcceleration:ax];
    double distanceProectionY = [self getDistanceProection:previousState.startingSpeedY andTime:timeDiff andAcceleration:ay];
   
    [currentState setCoordinateX:[self getXCoordinate:previousState.coordinateX andAzimuth: currentState.azimuth andProjection: distanceProectionX]];
    [currentState setCoordinateY:[self getYCoordinate:previousState.coordinateY andAzimuth: currentState.azimuth andProjection: distanceProectionY]];
    [currentState setStartingSpeedX:[self getEndSpeed:previousState.startingSpeedX andTime:timeDiff andAcceleration:ax]];
    [currentState setStartingSpeedY:[self getEndSpeed:previousState.startingSpeedY andTime:timeDiff andAcceleration:ay]];

    return currentState;
}


+(double)getDistanceProection:(double) startingSpeed andTime:(double) t andAcceleration:(double) acceleration {
    return startingSpeed + 0.5 * 9.8 * acceleration * t * t;
}

+(double)getXCoordinate:(double) coordinate andAzimuth:(double)azimuth andProjection:(double) projection {
    double resultCoordinate = 0;
    if (azimuth > 0 && azimuth < 180) {
        resultCoordinate = coordinate + projection;
    }
    
    if (azimuth > 180 && azimuth < 360) {
        resultCoordinate = coordinate - projection;
    }
    return resultCoordinate;
}

+(double)getYCoordinate:(double) coordinate andAzimuth:(double)azimuth andProjection:(double) projection {
    double resultCoordinate = 0;
    if ((azimuth > 0 && azimuth < 90) || (azimuth > 270 && azimuth <
                                          360)) {
        resultCoordinate = coordinate + projection;
    }
    
    if (azimuth > 90 && azimuth < 270) {
        resultCoordinate = coordinate - projection;
    }
    return resultCoordinate;
}

+(double)getEndSpeed:(double) speed andTime:(double)t andAcceleration:(double)acceleration {
    return speed + 9.8 * acceleration * t;
}

@end
