package com.boost.leonid.accelerometermvp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;

    @BindView(R.id.btn_upload_file)
    Button mUploadBtn;
    @BindView(R.id.btn_accelerometer)
    Button mAccelerometerBtn;

    @OnClick({R.id.btn_accelerometer, R.id.btn_upload_file})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_accelerometer:
                startActivity(new Intent(this, SessionHistoryListActivity.class));
                this.finish();
                break;
            case R.id.btn_upload_file:
                // TODO: create choicer activity for upload file into server
                Toast.makeText(this, "Upload file", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
    }
}
