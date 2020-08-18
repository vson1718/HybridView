// IWebViewProcessToMainProcessInterface.aidl
package com.vson.hybrid;

// Declare any non-default types here with import statements
import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;
interface IWebViewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void  handleWebCommand(String commandName,String jsonParams,ICallbackFromMainprocessToWebViewProcessInterface callBack);
}
