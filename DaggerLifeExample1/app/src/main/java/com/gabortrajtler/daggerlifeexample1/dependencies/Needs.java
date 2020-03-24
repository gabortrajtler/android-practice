package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;

public class Needs {

    public Food mFood;
    public Shelter mShelter;

    @Inject
    public Needs(Food mFood, Shelter mShelter) {
        this.mFood = mFood;
        this.mShelter = mShelter;
    }

    public boolean fulfilled() {
        return mFood != null && mShelter != null;
    }
}
