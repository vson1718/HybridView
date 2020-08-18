package com.vson.common.eventbus;

/**
 * @author vson
 */
public class LoginEvent {
    public String userName;
    public LoginEvent(String userName) {
        this.userName = userName;
    }
}
