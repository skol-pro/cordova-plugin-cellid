package com.skolpro.cellid;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.gsm.GsmCellLocation;

public class CellIDPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getCellID".equals(action)) {
            getCellID(callbackContext);
            return true;
        }
        return false;
    }

    private void getCellID(CallbackContext callbackContext) {
        try {
            Context context = cordova.getActivity().getApplicationContext();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            int cellID = cellLocation.getCid();

            PluginResult result = new PluginResult(PluginResult.Status.OK, cellID);
            callbackContext.sendPluginResult(result);
        } catch (Exception e) {
            callbackContext.error("Error getting Cell ID: " + e.getMessage());
        }
    }
}
