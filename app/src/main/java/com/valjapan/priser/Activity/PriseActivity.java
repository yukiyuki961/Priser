package com.valjapan.priser.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.valjapan.priser.Adapter.PriseRecyclerViewAdapter;
import com.valjapan.priser.Data.DetailData;
import com.valjapan.priser.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PriseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PriseRecyclerViewAdapter priseRecyclerViewAdapter;
    private ArrayList<String> detailList;

    private String timeResult, nowTime;
    private TextView cpuTextView;

    private DetailData data = new DetailData();
    private List<DetailData> dataSet = new ArrayList<>();


    final Calendar calendar = Calendar.getInstance();
    int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    final int minute = calendar.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Prise Activity");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.priseRecyclerView);
        cpuTextView = (TextView) findViewById(R.id.cpu_text_view);
        priseRecyclerViewAdapter = new PriseRecyclerViewAdapter(this.createDataset());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(priseRecyclerViewAdapter);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            timeResult = (String) bundle.get("result_time");
        }


        Log.d("prise_activity", "渡された値は " + timeResult + " です");

        getTime();
        Log.d("month/day hour:minute,", "渡された値は " + nowTime + " です");

    }

    private List<DetailData> createDataset() {

        if (timeResult != null) {
            resultChange();
        } else {

            getTime();
            data.setTime(nowTime);
            data.setDetail("これから運動を始めるよ！");
            dataSet.add(data);

            Log.d("do createDataSet", "動作を確認");

        }
        return dataSet;

    }


    private List<DetailData> addDataset() {
        getTime();
        data.setTime(nowTime);
        data.setDetail(timeResult);
        dataSet.add(data);

        Log.d("do addDataSet", "動作を確認");

        return dataSet;
    }

    private void getTime() {

        String.format("%02d", month, Locale.getDefault());
        String.format("%02d", day, Locale.getDefault());
        String.format("%02d", hour, Locale.getDefault());
        String.format("%02d", minute, Locale.getDefault());
        nowTime = (month + 1) + "/" + day + " " + hour + ":" + minute;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resultChange() {
        addDataset();


        cpuTextView.setText("今日もお疲れ様！ " + timeResult + "分もやったの！？すごーい！！ ");

    }

}