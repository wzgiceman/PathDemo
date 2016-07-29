package com.view.pathdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.view.pathdemo.view.ProgressCircleView;

public class MainActivity extends AppCompatActivity {
    ProgressCircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView=(ProgressCircleView)findViewById(R.id.progress);

        handler.sendEmptyMessageDelayed(1,2000);
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            circleView.setCurrentPosition(circleView.getCurrentPosition() + 1 > 4 ? 0 : circleView.getCurrentPosition() + 1);
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };
}
