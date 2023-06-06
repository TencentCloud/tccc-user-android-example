package com.tencent.tcccsimpledemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tccc.TCCCCloud;

public class MainActivity extends AppCompatActivity {
    private Button bntStartVideoCall;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntStartVideoCall = findViewById(R.id.btn_startVideoCall);
        bntStartVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallingActivity.class);
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