package com.isobar.sample.architecturepatterns.model;

/**
 * Created by fabio.goncalves on 19/01/2017.
 */

public class RandomFailureException extends RuntimeException {
    public RandomFailureException(String s) {
        super(s);
    }
}
