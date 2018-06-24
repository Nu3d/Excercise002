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
import android.widget.LinearLayout;
import android.widget.TextView;


public class
MainActivity extends AppCompatActivity implements SensorEventListener {


    private Sensor mysensor;
    private SensorManager sensm;
    private float point1;
    private float point2;
    private TextView player1;
    private TextView player2;
    private TextView winner;
    private Boolean playerturn=true;
    private TextView xx,yy,zz;
    private LinearLayout main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //accelometer
        mysensor = sensm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //sesorlistner
        sensm.registerListener(this, mysensor, SensorManager.SENSOR_DELAY_NORMAL);

      /*xx=(TextView)findViewById(R.id.xx);
        yy=(TextView)findViewById(R.id.yy);
        zz=(TextView)findViewById(R.id.zz);*/
        player1 = (TextView) findViewById(R.id.p1);
        player2 = (TextView) findViewById(R.id.p2);
        winner = (TextView) findViewById(R.id.w);
        main=(LinearLayout)findViewById(R.id.mainActivity);
        main.setBackgroundColor(Color.YELLOW);


        point1 = 0f;
        point2 = 0f;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {

           /* xx.setText(Float.toString(sensorEvent.values[0]));
            yy.setText(Float.toString(sensorEvent.values[1]));
            zz.setText(Float.toString(sensorEvent.values[2]));*/

            for (float value : sensorEvent.values) {
                if (Math.abs(value) > point1) {

                    if (playerturn) {
                        player1.setText(Integer.toString((int) point1));
                        point1 = Math.abs(value);
                    }
                }


                if (Math.abs(value) > point2) {

                    if (!playerturn) {
                        player2.setText(Integer.toString((int) point2));
                        point2 = Math.abs(value);
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void reset(View view) {


        point1 = 0;
        player2.setText(Integer.toString((int) point1));
        point2 = 0;
        player1.setText(Integer.toString((int) point2));

        player1.setTextColor(Color.WHITE);
        player2.setTextColor(Color.WHITE);



        winner.setText("");
    }

    public void click(View view) {
        playerturn = true;
        player1.setTextColor(Color.GREEN);
        player2.setTextColor(Color.RED);

    }

    public void pclick(View view) {
        playerturn = false;
        player2.setTextColor(Color.GREEN);
        player1.setTextColor(Color.RED);
    }

    public void wclick(View view) {
        String win1 = player1.getText().toString();
        float win2 = Float.parseFloat(win1);
        String win01 = player2.getText().toString();
        float win02 = Float.parseFloat(win01);
        if (win2 > win02) {

            winner.setText("Player 1 : " +(int)win2);

        } else if (win02 > win2){

            winner.setText("Player 2 : " +(int)win02);

        }
        else if(win2 == win02) {
            winner.setText("Draw");
        }
        }
    }

