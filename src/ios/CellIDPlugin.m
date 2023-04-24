#import <Cordova/CDV.h>
#import <CoreTelephony/CTTelephonyNetworkInfo.h>
#import <CoreTelephony/CTCarrier.h>
#import <CoreTelephony/CTCellularData.h>

@interface CellIDPlugin : CDVPlugin

- (void)getCellID:(CDVInvokedUrlCommand *)command;

@end

@implementation CellIDPlugin

- (void)getCellID:(CDVInvokedUrlCommand *)command {
    [self.commandDelegate runInBackground:^{
        CTCarrier *carrier = [[[CTTelephonyNetworkInfo alloc] init] subscriberCellularProvider];
        NSString *cellID = [carrier mobileNetworkCode];

        CDVPluginResult *pluginResult;
        if (cellID) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:cellID];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Cell ID not available"];
        }

        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
