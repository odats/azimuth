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
+(float)getDistanceProection:(float) startingSpeed andTime:(float) t andAcceleration:(float) acceleration;
+(float)getXCoordinate:(float) coordinate andAzimuth:(float)azimuth andProjection:(float) projection;
+(float)getYCoordinate:(float) coordinate andAzimuth:(float)azimuth andProjection:(float) projection;
+(float)getEndSpeed:(float) speed andTime:(float)t andAcceleration:(float)acceleration;
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

+(AZPositionState *)calculateWith:(float)ax andWith:(float)ay andAzimuth:(float)azimuth andInterval:(float) timeDiff andPreviousState:(AZPositionState *)previousState {
    AZPositionState *currentState = [[[AZPositionState alloc] init] autorelease];
    [currentState setAzimuth:azimuth];
    double distanceProectionX = [self getDistanceProection:previousState.startingSpeedX andTime:timeDiff andAcceleration:ax];
    double distanceProectionY = [self getDistanceProection:previousState.startingSpeedY andTime:timeDiff andAcceleration:ay];
    
    [currentState setCoordinateX:[self getXCoordinate:previousState.coordinateX andAzimuth: currentState.azimuth andProjection:distanceProectionX]];
    [currentState setCoordinateY:[self getYCoordinate:previousState.coordinateY andAzimuth: currentState.azimuth andProjection: distanceProectionY]];
    
    [currentState setStartingSpeedX:[self getEndSpeed:previousState.startingSpeedX andTime:timeDiff andAcceleration:ax]];
    [currentState setStartingSpeedY:[self getEndSpeed:previousState.startingSpeedY andTime:timeDiff andAcceleration:ay]];

    return currentState;
}


+(float)getDistanceProection:(float) startingSpeed andTime:(float) t andAcceleration:(float) acceleration {
    return startingSpeed + 0.5 * acceleration * t * t;
}

+(float)getXCoordinate:(float) coordinate andAzimuth:(float)azimuth andProjection:(float) projection {
    float resultCoordinate = 0;
    if (azimuth > 0 && azimuth < 180) {
        resultCoordinate = coordinate + projection;
    }
    
    if (azimuth > 180 && azimuth < 360) {
        resultCoordinate = coordinate - projection;
    }
    return resultCoordinate;
}

+(float)getYCoordinate:(float) coordinate andAzimuth:(float)azimuth andProjection:(float) projection {
    float resultCoordinate = 0;
    if ((azimuth > 0 && azimuth < 90) || (azimuth > 270 && azimuth <
                                          360)) {
        resultCoordinate = coordinate + projection;
    }
    
    if (azimuth > 90 && azimuth < 270) {
        resultCoordinate = coordinate - projection;
    }
    return coordinate;
}

+(float)getEndSpeed:(float) speed andTime:(float)t andAcceleration:(float)acceleration {
    return speed + acceleration * t;
}

@end
