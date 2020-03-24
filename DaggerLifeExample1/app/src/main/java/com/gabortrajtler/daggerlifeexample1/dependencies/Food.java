package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;

public class Food {

    public Money mMoney;

    @Inject
    public Food(Money mMoney) {
        this.mMoney = mMoney;

        if (mMoney != null) {
            System.out.println("I can eat delicious dishes!");
        } else {
            System.out.println("I only eat once or twice a day.");
        }
    }
}
