package com.isobar.sample.architecturepatterns.bus;

import android.support.annotation.WorkerThread;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by fabio.goncalves on 16/01/2017.
 */

public class EventBus {

    private final static EventBus instance = new EventBus();
    private final Bus bus;


    public static EventBus getInstance() {
        return instance;
    }

    public EventBus() {
        bus = new Bus(ThreadEnforcer.ANY);
    }

    public void register(Object listener) {
        bus.register(listener);
    }

    public void unregister(Object listener) {
        bus.unregister(listener);
    }

    @WorkerThread
    public void postOnAnyThread(Event event) {
        bus.post(event);
    }
}