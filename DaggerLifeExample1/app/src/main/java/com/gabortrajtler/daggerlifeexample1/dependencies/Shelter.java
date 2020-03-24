package com.gabortrajtler.daggerlifeexample1.dependencies;

import javax.inject.Inject;

public class Shelter {

    public Money mMoney;

    @Inject
    public Shelter(Money mMoney) {
        this.mMoney = mMoney;

        if (mMoney != null) {
            System.out.println("I've my own house to live!");
        } else {
            System.out.println("I don't have my own house yet.");
        }
    }
}
