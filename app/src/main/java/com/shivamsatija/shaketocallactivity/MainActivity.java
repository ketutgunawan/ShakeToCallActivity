package com.shivamsatija.shaketocallactivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mAccelerometer;
    ShakeDetector mShakeDetector;
    Vibrator vibrator;
    int r;
    int g;
    int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {

                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
                Random random = new Random();
                r = random.nextInt(255);
                g = random.nextInt(255);
                b = random.nextInt(255);
                int color = Color.rgb(r, g, b);
                RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
                mainLayout.setBackgroundColor(color);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
