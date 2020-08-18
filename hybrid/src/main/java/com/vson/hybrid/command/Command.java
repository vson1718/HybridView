package com.vson.hybrid.command;

import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;

import java.util.Map;

/**
 * @author vson
 */
public interface Command {

    /**
     * 指令名称
     *
     * @return String
     */
    String name();

    /**
     * 执行指令
     *
     * @param parameters map
     * @param callback   ICallbackFromMainprocessToWebViewProcessInterface
     */
    void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback);

}
