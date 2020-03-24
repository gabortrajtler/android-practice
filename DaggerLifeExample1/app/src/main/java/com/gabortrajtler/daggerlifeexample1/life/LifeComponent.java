package com.gabortrajtler.daggerlifeexample1.life;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface LifeComponent {
    Life getLife();
}
