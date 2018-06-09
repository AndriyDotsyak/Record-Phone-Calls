package com.andriy.recordphonecalls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andriy.recordphonecalls.RecordCalls.RecordCallsService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_activate)
    Button btnActivate;

    @BindView(R.id.btn_Stop)
    Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_activate, R.id.btn_Stop})
    void onSaveClick(View view) {
        Intent intent = new Intent(MainActivity.this, RecordCallsService.class);

        switch (view.getId()) {
            case R.id.btn_activate:
                btnActivate.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);

                startService(intent);
                break;
            case R.id.btn_Stop:
                btnStop.setVisibility(View.GONE);
                btnActivate.setVisibility(View.VISIBLE);

                stopService(intent);
                break;
        }
    }
}
