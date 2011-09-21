//
//  AzimuthViewController.h
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AzimuthViewController : UIViewController<UIAccelerometerDelegate>

@property (readonly) NSMutableArray *coordinates;

@end
