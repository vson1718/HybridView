var hybrid = {};
hybrid.os = {};
hybrid.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
hybrid.os.isAndroid = !hybrid.os.isIOS;
hybrid.callbacks = {}

hybrid.callback = function (callBackName, response) {
   var callbackobject = hybrid.callbacks[callBackName];
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete hybrid.callbacks[callBackName];
       }
   }
}

hybrid.takeNativeAction = function(commandname, parameters){
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.hybrid.os.isAndroid){
        window.hybridWebView.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.hybridWebView.postMessage(JSON.stringify(request))
    }
}

hybrid.takeNativeActionWithCallback = function(commandName, parameters, callback) {
    var callBackName = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    hybrid.callbacks[callBackName] = {callback:callback};
    var request = {};
    request.name = commandName;
    request.param = parameters;
    request.param.callBackName = callBackName;
    if(window.hybrid.os.isAndroid){
        window.hybridWebView.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.hybridWebView.postMessage(JSON.stringify(request))
    }
}

window.hybrid = hybrid;
