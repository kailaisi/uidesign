package cn.com.tcsl.aopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.aspect.TimeLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @TimeLog
    private void initViews() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
