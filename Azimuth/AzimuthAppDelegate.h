//
//  AzimuthAppDelegate.h
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import <UIKit/UIKit.h>

@class AzimuthViewController;

@interface AzimuthAppDelegate : NSObject <UIApplicationDelegate>

@property (nonatomic, retain) IBOutlet UIWindow *window;

@property (nonatomic, retain) IBOutlet AzimuthViewController *viewController;

@end
