package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Money {

    public Job mJob;

    @Inject
    public Money(Job mJob) {
        this.mJob = mJob;

        if (mJob != null) {
            System.out.println("I've got money!");
        } else {
            System.out.println("I'm not working yet.");
        }
    }
}
