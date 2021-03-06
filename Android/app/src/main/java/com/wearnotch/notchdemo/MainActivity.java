//This class was pre-existing
//Added functionality to do with OSCService

package com.wearnotch.notchdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wearnotch.service.NotchAndroidService;
import com.wearnotch.notchdemo.oscService;

import butterknife.ButterKnife;

import java.net.*;
import java.util.*;

import com.illposed.osc.*;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }

        Intent controlServiceIntent = new Intent(this, NotchAndroidService.class);
        startService(controlServiceIntent);

        // to develop app UI without notches you can use a 'mock' version of the SDK
        // it returns success for all SDK calls, to use it uncomment this line
        // controlServiceIntent.putExtra("MOCK", true);

        bindService(controlServiceIntent, this, BIND_AUTO_CREATE);

        Intent oscServiceIntent = new Intent(this, oscService.class);
        startService(oscServiceIntent);
        bindService(oscServiceIntent, this, BIND_AUTO_CREATE);

    }
}
