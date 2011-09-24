//
//  AzimuthViewController.m
//  Azimuth
//
//  Created by Roman Romanchuk on 21.09.11.
//  Copyright 2011. All rights reserved.
//

#import "AzimuthViewController.h"
#import "AZView.h"
#import "AZPositionState.h"
#import "AZEngine.h"
#import "AZPoint.h"

#define kUpdateFrequency 10

@implementation AzimuthViewController

@synthesize coordinates;
@synthesize states;
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
    states = [NSMutableArray array];
    [states retain];
    AZPositionState *state = [[AZPositionState alloc] init];
    [states addObject:state];
    [state release];
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
    [states release];
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
        AZView *azView = (AZView *)[self view];
        [azView setCoordinates:coordinates];
        [azView setNeedsDisplay];
    }
    NSLog(@"Acc: %f %f", acceleration.x, acceleration.y);
    NSTimeInterval timeDiffInSeconds;
    if (lastTimestamp == 0){
        timeDiffInSeconds = 0.1;
    } else {
        timeDiffInSeconds = ([acceleration timestamp] - lastTimestamp)/1000.0;
    }
    lastTimestamp = [acceleration timestamp];
    AZPositionState *prevState = [states lastObject];
    AZPositionState *state =[AZEngine calculateWith:[acceleration x] andWith:[acceleration y] andAzimuth:30 andInterval:timeDiffInSeconds andPreviousState:prevState];
    [states addObject:state];
    AZPoint *point = [[AZPoint alloc] initWithX:[state coordinateX] andY:[state coordinateY]];
    NSLog(@"Coord: %f %f", state.coordinateX, state.coordinateY);
    [coordinates addObject:point];
    [point release];
    [states removeObject:prevState];    
}
@end
