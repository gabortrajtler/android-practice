package com.gabortrajtler.daggerlifeexample1.life;

import com.gabortrajtler.daggerlifeexample1.dependencies.Needs;

import javax.inject.Inject;

public class Life {

    public Needs needs;

    @Inject
    public Life(Needs needs) {
        this.needs = needs;
    }

    public void enjoy() {
        if (needs.fulfilled()) {
            System.out.println("I'm enjoying life! :)");
        } else {
            System.out.println("Life is hard.");
        }
    }
}
