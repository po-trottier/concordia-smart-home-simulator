package com.concordia.smarthomesimulator.helpers;

import com.concordia.smarthomesimulator.interfaces.IObserver;

import java.util.ArrayList;

public  class ObserverHelper {

    private static ArrayList<IObserver> observers;

    /**
     * Returns list of observers to be used
     *
     * @return observers
     */
    public static ArrayList<IObserver> getObservers(){
        if (observers == null) {
            observers = new ArrayList<>();
        }
        return observers;
    }

    /**
     * Adds an observer to the observers list
     *
     * @param observer observer
     */
    public static void addObserver(IObserver observer){
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }
}

