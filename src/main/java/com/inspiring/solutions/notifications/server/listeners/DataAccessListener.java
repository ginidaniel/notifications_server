package com.inspiring.solutions.notifications.server.listeners;

public interface DataAccessListener<T> {
    default void onComplete(T t) {};
    void onSuccess(T t);
    default void onFailure(Exception e) {};
    default void onRemove(T t) {};
}

