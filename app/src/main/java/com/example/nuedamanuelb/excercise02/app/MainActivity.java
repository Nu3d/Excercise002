package com.example.nuedamanuelb.excercise02.app;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class
MainActivity extends AppCompatActivity implements SensorEventListener{


    private Sensor mysensor;
    private SensorManager sensm;
    private float x1;
    private float x2;
    private TextView xx;
    private TextView yy;
    private TextView zz;
    private TextView p1;
    private TextView p2;
    private Boolean p=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //accelometer
       mysensor=sensm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //sesorlistner
        sensm.registerListener(this, mysensor, SensorManager.SENSOR_DELAY_NORMAL);

        xx=(TextView)findViewById(R.id.xx);
        yy=(TextView)findViewById(R.id.yy);
        zz=(TextView)findViewById(R.id.zz);
        p1=(TextView)findViewById(R.id.p1);
        p2=(TextView)findViewById(R.id.p2);

        x1=0f;
        x2=0f;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if(sensorEvent.sensor.getType()==Sensor.TYPE_LINEAR_ACCELERATION) {
            xx.setText(Float.toString(sensorEvent.values[0]));
            yy.setText(Float.toString(sensorEvent.values[1]));
            zz.setText(Float.toString(sensorEvent.values[2]));

            for(float value:sensorEvent.values){
                if(Math.abs(value)>x1){
                    x1=Math.abs(value);

                    if (p){
                        p1.setText(Integer.toString((int)x1));

                    }
                }

                if(Math.abs(value)>x2){
                    x2=Math.abs(value);

                    if (!p){
                        p2.setText(Integer.toString((int)x2));


                    }

                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
   public void reset(View view) {
    }
    public void click(View view) {
        p=true;
        p1.setTextColor(Color.RED);
        p2.setTextColor(Color.BLACK);

    }

    public void pclick(View view) {
        p=false;
        p2.setTextColor(Color.RED);
        p1.setTextColor(Color.BLACK);
    }
}
