package com.calaxar.bluetoothled;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static SendFragment sendFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (findViewById(R.id.fragment) != null) {
                    switch (item.getItemId()) {
                        case R.id.navigation_send:
                            getFragmentManager().beginTransaction().replace(R.id.fragment, sendFragment).commit();
                            return true;
                        case R.id.navigation_saved:
                            return true;
                        case R.id.navigation_sequence:
                            return true;
                    }
                }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (findViewById(R.id.fragment) != null) {
            sendFragment = new SendFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment, sendFragment).commit();
        }
    }

    public static void sendColor(int r, int g, int b) {
        Log.d(TAG, "sendColor: " + r + g + b);
    }
}
