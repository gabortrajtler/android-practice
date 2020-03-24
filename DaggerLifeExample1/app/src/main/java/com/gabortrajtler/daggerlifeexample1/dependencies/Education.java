package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;

public class Education {

    @Inject
    public Education() {
        System.out.println("I'm well educated! :)");
    }
}
