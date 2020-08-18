
# HybridView

##一个跨进程通信的WebView组件，基于命令模式实现Native和WebView通信
<img src='screenshots/list_demo.gif' height=444 width=250 />
###Android 端只需要实现command接口即可自定义指令
~~~
@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";//指令名称
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        //指令的操作实现
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
~~~

###H5兼容在匿名函数的情况下使用函数调用
~~~
 function login(){
            hybrid.takeNativeActionWithCallback("login", {}, function(res) {
                 var element = document.createElement("div");
                 element.appendChild(document.createTextNode("userName: "+res.accountName));
                 document.getElementById('login_id').appendChild(element);
            });
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
~~~


### 关于作者

```
    name : "vson",
    简书 : "[https://www.jianshu.com/u/b8d32d868a2b](https://www.jianshu.com/u/b8d32d868a2b)"
```

### [](https://github.com/vson1718/HybridView)License

```
Copyright (C)  menxindiaolong, FlyTour Framework Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
