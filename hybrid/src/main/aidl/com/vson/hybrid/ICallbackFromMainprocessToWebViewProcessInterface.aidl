// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.vson.hybrid;

// Declare any non-default types here with import statements

interface ICallbackFromMainprocessToWebViewProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onResult(String callBackName, String response);
}
