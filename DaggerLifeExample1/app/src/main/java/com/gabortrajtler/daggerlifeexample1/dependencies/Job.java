package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;

public class Job {
    public Education mEducation;

    @Inject
    public Job(Education mEducation) {
        this.mEducation = mEducation;

        if (mEducation != null) {
            System.out.println("I've Job!");
        } else {
            System.out.println("I'm not well educated.");
        }
    }
}
