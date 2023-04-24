declare module 'cordova-plugin-cellid' {
    interface CellIDPlugin {
        getCellID(success: (cellId: any) => void, error: (error: any) => void): void;
    }

    interface CordovaPlugins {
        cellid: CellIDPlugin;
    }
}
