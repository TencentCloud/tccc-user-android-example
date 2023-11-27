package com.tencent.tcccsimpledemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tccc.TCCCCloud;

public class MainActivity extends AppCompatActivity {
    private Button bntStartAudioCall;
    private Button bntStartVideoCall;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntStartAudioCall = findViewById(R.id.btn_startVoipCall);
        bntStartAudioCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallingActivity.class);
                intent.putExtra("callType","audio");
                startActivity(intent);
            }
        });
        bntStartVideoCall = findViewById(R.id.btn_startVedioCall);
        bntStartVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallingActivity.class);
                intent.putExtra("callType","video");
                startActivity(intent);
            }
        });
        textView = findViewById(R.id.textView);
        // 必须先调用这个后才可以获取版本号
        TCCCCloud tcccCloud = TCCCCloud.sharedInstance(this.getApplicationContext());
        // 获取SDK 版本号
         String version = tcccCloud.getSDKVersion();
         textView.setText(getString(R.string.app_version)+"("+version+")");
    }
}