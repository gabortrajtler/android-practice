package com.gabortrajtler.daggerlifeexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gabortrajtler.daggerlifeexample1.life.DaggerLifeComponent;
import com.gabortrajtler.daggerlifeexample1.life.Life;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Life life = DaggerLifeComponent.create().getLife();
        life.enjoy();
    }
}
