package com.vson.hybrid.main;

import android.os.RemoteException;

import com.google.gson.Gson;
import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;
import com.vson.hybrid.IWebViewProcessToMainProcessInterface;
import com.vson.hybrid.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author vson
 * 项目描述:
 */
public class MainProcessCommandsManager extends IWebViewProcessToMainProcessInterface.Stub {

    private static volatile MainProcessCommandsManager sInstance;
    private static HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getsInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (sInstance == null) {
                    sInstance = new MainProcessCommandsManager();
                }
            }
        }
        return sInstance;
    }


    private MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    public void executeCommand(String commandName, Map params, ICallbackFromMainprocessToWebViewProcessInterface callBack) {
        if (mCommands.containsKey(commandName)) {
            mCommands.get(commandName).execute(params, callBack);
        }
    }


    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callBack) {
        MainProcessCommandsManager.getsInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class), callBack);
    }
}
