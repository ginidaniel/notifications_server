package com.pointsoftango.server.notifications.listeners;

public interface DataAccessListener<T> {
    default void onComplete(T t) {};
    void onSuccess(T t);
    default void onFailure(Exception e) {};
    default void onRemove(T t) {};
}

