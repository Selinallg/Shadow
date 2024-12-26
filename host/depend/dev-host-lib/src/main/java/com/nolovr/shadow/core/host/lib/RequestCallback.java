package com.nolovr.shadow.core.host.lib;

public interface RequestCallback {
    void onSuccess(Object result);
    void onFailure(Object info);
}
