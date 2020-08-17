var hybrid = {};
hybrid.os = {};
hybrid.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
hybrid.os.isAndroid = !hybrid.os.isIOS;
hybrid.callbacks = {}

hybrid.callback = function (callbackname, response) {
   var callbackobject = hybrid.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete hybrid.callbacks[callbackname];
       }
   }
}

hybrid.takeNativeAction = function(commandname, parameters){
    console.log("hybrid takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.hybrid.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}

hybrid.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    hybrid.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.hybrid.os.isAndroid){
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}

window.hybrid = hybrid;
