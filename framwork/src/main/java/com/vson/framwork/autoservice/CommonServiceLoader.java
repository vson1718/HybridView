package com.vson.framwork.autoservice;

import java.util.ServiceLoader;

/**
 * @author vson
 */
public final class CommonServiceLoader {
    private CommonServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
