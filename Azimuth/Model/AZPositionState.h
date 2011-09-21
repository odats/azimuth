//
//  AZPositionState.h
//  Azimuth
//
//  Created by Роман Романчук on 22.09.11.
//  Copyright 2011 Epam. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AZPositionState : NSObject

@property (assign) float startingSpeedX;
@property (assign) float startingSpeedY;
@property (assign) float coordinateX;
@property (assign) float coordinateY;
@property (assign) float azimuth;

@end
