//
//  AzimuthViewController.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AzimuthViewController.h"
#import "AZView.h"

#define kUpdateFrequency 30

@implementation AzimuthViewController

@synthesize coordinates;

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad
{
    [super viewDidLoad];
    
    coordinates = [NSMutableArray array];
    [coordinates retain];
    lastTimestamp = 0;
    [[UIAccelerometer sharedAccelerometer] setUpdateInterval:1.0 / kUpdateFrequency];
    [[UIAccelerometer sharedAccelerometer] setDelegate:self];
}


- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
    [coordinates release];
    [[UIAccelerometer sharedAccelerometer] setDelegate:nil];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

// UIAccelerometerDelegate method, called when the device accelerates.
-(void)accelerometer:(UIAccelerometer *)accelerometer didAccelerate:(UIAcceleration *)acceleration
{
    // Update the accelerometer graph view
    if ([coordinates count] % 15 == 0) {
        [view setCoordinates:coordinates];
        [view setNeedsDisplay];
    }
    NSTimeInterval timeDiffInSeconds;
    if (lastTimestamp == 0){
        timeDiffInSeconds = 0.2;
    } else {
        timeDiffInSeconds = ([acceleration timestamp] - lastTimestamp) / 1000000000.0;
    }
    lastTimestamp = [acceleration timestamp];
    
}
@end
