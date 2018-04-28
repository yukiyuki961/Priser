package com.valjapan.priser.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.valjapan.priser.R;

public class MainActivity extends AppCompatActivity implements Runnable, View.OnClickListener {
    private long startTime, endTime;
    private TextView timerTextView;
    private final Handler handler = new Handler();
    private volatile boolean stopRun = false;
    private Button startButton, stopButton;
    private Boolean startOrFinish = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timer_text_view);

        startButton = new Button(this);
        startButton = findViewById(R.id.timer_start_button);
        startButton.setOnClickListener(this);

        stopButton = new Button(this);

        stopButton = findViewById(R.id.timer_stop_button);
        stopButton.setOnClickListener(this);


        long hh = 00; // 時
        long mm = 00 / 1000 / 60; // 分
        long ss = 00 / 1000 % 60; // 秒
        String time = String.format("%1$02d:%2$02d:%3$02d", hh, mm, ss);

        timerTextView.setText(time);


    }

    @Override
    public void onClick(View v) {
        Thread thread;
        if (v.getId() == R.id.timer_start_button) {
            stopRun = false;
            thread = new Thread(this);
            thread.start();

            startTime = System.currentTimeMillis();
            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);

            Intent intent = new Intent(getApplicationContext(), CharaTalkActivity.class);
            intent.putExtra("check_time", startOrFinish);
            startOrFinish = false;
            startActivity(intent);


        } else if (v.getId() == R.id.timer_stop_button) {

            stopRun = true;
            long hh = 00; // 時
            long mm = 00 / 1000 / 60; // 分
            long ss = 00 / 1000 % 60; // 秒
            String time = String.format("%1$02d:%2$02d:%3$02d", hh, mm, ss);

            timerTextView.setText(time);
            startButton.setVisibility(View.VISIBLE);
            stopButton.setVisibility(View.GONE);

            Intent intent = new Intent(getApplicationContext(), CharaTalkActivity.class);
            intent.putExtra("check_time", startOrFinish);
            startOrFinish = true;
            startActivity(intent);
        }
    }


    @Override
    public void run() {
        int period = 10;

        while (!stopRun) {
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopRun = true;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    endTime = System.currentTimeMillis();
                    // カウント時間 = 経過時間 - 開始時間

                    long diffTime = (endTime - startTime);


                    long hh = (diffTime / (1000 * 60 * 60)) % 24; // 時
                    long mm = (diffTime / (1000 * 60)) % 60; // 分
                    long ss = (diffTime / 1000) % 60; // 秒
                    String time = String.format("%1$02d:%2$02d:%3$02d", hh, mm, ss);

                    timerTextView.setText(time);


                }
            });
        }
    }


}
