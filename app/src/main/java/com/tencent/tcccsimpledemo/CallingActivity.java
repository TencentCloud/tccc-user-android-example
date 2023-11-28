package com.tencent.tcccsimpledemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.debug.GenerateTestUserSig;
import com.tencent.tccc.TCCCCloud;
import com.tencent.tccc.TCCCCloudDef;
import com.tencent.tccc.TCCCCloudListener;
import com.tencent.tccc.TCCCDeviceManager;
import com.tencent.tccc.TXCallback;
import com.tencent.tccc.ui.TXVideoView;
import com.tencent.tcccsimpledemo.base.TCCCBaseActivity;

import java.util.UUID;

public class CallingActivity extends TCCCBaseActivity {
    private TXVideoView txvMainVideoView;
    private TXVideoView txvSmallView;

    private TextView txt_tips;
    private TCCCCloud mTCCCCloud;
    private Handler mainHandler;

    private RelativeLayout ll_hangup;
    private ImageView img_hangup;
    private TextView  tv_hangup;
    private RelativeLayout ll_mute_mic;
    private ImageView img_mute_mic;
    private TextView  tv_mute_mic;
    protected boolean mIsMuteMic     = false;  // 是否静音
    protected boolean mIsCalling     = false; // 正在通话中
    protected boolean isAudioCall    = false; // 是否为音频通话

    private LinearLayout ll_senddtmfDiv;
    private TextView tx_dail;
    protected GridLayout dialPad;
    private  EditText numberInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        initView();
        if (checkPermission()) {
            initTCCC();
        } else {
            //
        }
    }

    @Override
    protected void onPermissionGranted() {
        initTCCC();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TCCCCloud.destroySharedInstance();
        mTCCCCloud = null;
    }

    private void initView() {
        txvMainVideoView = findViewById(R.id.txv_main);
        txvSmallView = findViewById(R.id.txv_small);

        txt_tips = findViewById(R.id.txt_tips);
        ll_hangup = findViewById(R.id.ll_hangup);
        img_hangup = findViewById(R.id.img_hangup);
        tv_hangup = findViewById(R.id.tv_hangup);
        ll_mute_mic = findViewById(R.id.ll_mute_mic);
        img_mute_mic = findViewById(R.id.img_mute_mic);
        tv_mute_mic = findViewById(R.id.tv_mute_mic);
        ll_senddtmfDiv = findViewById(R.id.ll_senddtmfDiv);
        dialPad = findViewById(R.id.dial_pad);
        numberInput = findViewById(R.id.number_input);
        ll_senddtmfDiv.setVisibility(View.GONE);
        tx_dail = findViewById(R.id.tx_dail);
        mainHandler = new Handler(this.getApplicationContext().getMainLooper());

        Intent intent = getIntent();
        String callType = intent.getStringExtra("callType");
        if ("audio".equals(callType)) {
            isAudioCall = true;
            findViewById(R.id.tx_dail).setVisibility(View.VISIBLE);
            findViewById(R.id.dial_btn).setVisibility(View.VISIBLE);
            txvMainVideoView.setVisibility(View.GONE);
            txvSmallView.setVisibility(View.GONE);
//            ll_camera.setVisibility(View.GONE);
//            ll_soundMode.setVisibility(View.VISIBLE);
        } else {
//            ll_camera.setVisibility(View.VISIBLE);
//            ll_soundMode.setVisibility(View.GONE);
            findViewById(R.id.dial_btn).setVisibility(View.GONE);
            findViewById(R.id.tx_dail).setVisibility(View.GONE);
            txvMainVideoView.setVisibility(View.VISIBLE);
            txvSmallView.setVisibility(View.VISIBLE);
        }

        initListener();
    }

    private void initListener(){
        ll_mute_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsMuteMic = !mIsMuteMic;
                img_mute_mic.setActivated(mIsMuteMic);
                // 暂停/恢复发布本地的音频流
                if (mIsMuteMic) {
                    mTCCCCloud.mute();
                } else {
                    mTCCCCloud.unmute();
                }
                tv_mute_mic.setText(mIsMuteMic?R.string.calling_toast_enable_mute:R.string.calling_toast_disable_mute);
            }
        });
        ll_hangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsCalling = false;
                // 用户主动结束呼叫或者结束通话
                mTCCCCloud.terminate();
            }
        });
        findViewById(R.id.dial_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tx_dail.getText().equals("关闭拨号盘")) {
                    numberInput.setText("");
                    ll_senddtmfDiv.setVisibility(View.GONE);
                    tx_dail.setText(R.string.calling_toast_dial);
                } else {
                    ll_senddtmfDiv.setVisibility(View.VISIBLE);
                    tx_dail.setText("关闭拨号盘");
                }
            }
        });
        for (int i=0;i < dialPad.getChildCount();i++) {
            Button item = (Button) dialPad.getChildAt(i);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String keyNumber = item.getText().toString();
                    numberInput.setText(numberInput.getText().toString()+keyNumber);
                    mTCCCCloud.sendDTMF(keyNumber.charAt(0), new TXCallback() {
                        @Override
                        public void onSuccess() {
                            //
                        }

                        @Override
                        public void onError(int i, String s) {
                            //
                        }
                    });
                }
            });
        }
    }

    /**
     * 获取安卓设备的唯一ID。
     * @return
     */
    private String getAndroidUuid(){
        String uuid = "";
        SharedPreferences preferences= this.getApplicationContext().getSharedPreferences("TCCC_UserID", Context.MODE_PRIVATE);
        String userId = preferences.getString("userId", "");
        if("".equals(userId)){
            SharedPreferences.Editor editor=preferences.edit();
            uuid =  UUID.randomUUID().toString().replaceAll("-","");
            editor.putString("userId",uuid);
            editor.commit();
        } else{
            uuid = userId;
        }
        return uuid;
    }

    /**
     *
     */
    private TCCCCloudListener mTCCCCloudListener = new TCCCCloudListener() {
        @Override
        public void onError(int errCode, String errMsg, Bundle extraInfo) {
            super.onError(errCode, errMsg, extraInfo);
        }

        @Override
        public void onWarning(int warningCode, String warningMsg, Bundle extraInfo) {
            super.onWarning(warningCode, warningMsg, extraInfo);
        }

        @Override
        public void onEnded(EndedReason reason, String reasonMessage, String sessionId) {
            super.onEnded(reason, reasonMessage, sessionId);
            String endMsg = "系统异常挂断";
            if (EndedReason.LocalBye == reason) {
                endMsg ="挂断成功";
            } else if(EndedReason.RemoteBye == reason){
                endMsg ="坐席已挂断";
            } else if(EndedReason.Timeout == reason){
                endMsg ="坐席接听超时";
            } else if (EndedReason.RemoteCancel == reason) {
                endMsg ="坐席取消";
            } else if (EndedReason.LocalCancel == reason) {
                endMsg ="取消成功";
            } else if (EndedReason.Rejected == reason) {
                endMsg ="坐席拒绝";
            }
            stopNgoBack(endMsg);
        }

        @Override
        public void onAccepted(String sessionId) {
            super.onAccepted(sessionId);
            mIsCalling = true;
            txt_tips.setText("已接通");
        }

        @Override
        public void onUserVideoAvailable(String userId, boolean available) {
            super.onUserVideoAvailable(userId,available);
            if(available) {
                // 显示坐席端画面
                mTCCCCloud.startRemoteView(userId,TCCCCloudDef.TCCC_VIDEO_STREAM_TYPE_BIG,txvMainVideoView);
                txvMainVideoView.setVisibility(View.VISIBLE);
            }
            else {
                txvMainVideoView.setVisibility(View.GONE);
            }
        }
        @Override
        public void onAudioVolume(TCCCCloudDef.TCCCVolumeInfo volumeInfo) {
            super.onAudioVolume(volumeInfo);
        }

        @Override
        public void onNetworkQuality(TCCCCloudDef.TCCCQualityInfo localQuality, TCCCCloudDef.TCCCQualityInfo remoteQuality) {
            super.onNetworkQuality(localQuality, remoteQuality);
        }

        @Override
        public void onConnectionLost(TCCCServerType serverType) {
            super.onConnectionLost(serverType);
        }

        @Override
        public void onTryToReconnect(TCCCServerType serverType) {
            super.onTryToReconnect(serverType);
        }

        @Override
        public void onConnectionRecovery(TCCCServerType serverType) {
            super.onConnectionRecovery(serverType);
        }
    };

    private void initTCCC(){
        /// 创建实例和设置事件回调
        mTCCCCloud = TCCCCloud.sharedInstance(getApplicationContext());

        /// 设置事件回调
        mTCCCCloud.setListener(mTCCCCloudListener);
        if (!isAudioCall) {
            TCCCCloudDef.TCCCRenderParams tcccRenderParams = new TCCCCloudDef.TCCCRenderParams();
            tcccRenderParams.fillMode = TCCCCloudDef.TCCC_VIDEO_RENDER_MODE_FILL;
            mTCCCCloud.setLocalRenderParams(tcccRenderParams);
            // 开启摄像头预览
            mTCCCCloud.startLocalPreview(true, txvSmallView);
        }
        // 开启本地音频采集
        mTCCCCloud.startLocalAudio(TCCCCloudDef.TCCC_AUDIO_QUALITY_SPEECH);
        mTCCCCloud.getDeviceManager().setAudioRoute(TCCCDeviceManager.TCCCAudioRoute.TCCCAudioRouteSpeakerphone);
        txt_tips.setText("准备呼叫...");
        String clinetUserId = "testUserId";
        // 正确的 UserSig 签发方式是将 UserSig 的计算代码集成到您的服务端，并提供面向 App 的接口，在需要 UserSig 时由您的 App 向业务服务器发起请求获取动态 UserSig。
        // 更多详情请参见 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260)
        GenerateTestUserSig.genTestUserSig(clinetUserId, new GenerateTestUserSig.UserSigCallBack() {
            @Override
            public void onSuccess(String userSig) {
                startInnerCall(clinetUserId,userSig,isAudioCall?TCCCCloudDef.TCCCCallType.Voip:TCCCCloudDef.TCCCCallType.Video);
            }

            @Override
            public void onError(int code, String desc) {
                stopNgoBack("计算UserSig签名失败");
            }
        });
    }

    /// 发起呼叫
    private void startInnerCall(String clinetUserId, String userSig,TCCCCloudDef.TCCCCallType callType) {
        mIsCalling = false;
        txt_tips.setText("呼叫中...");
        // 发起音频呼叫，请每次呼叫前重新生成userSig。
        TCCCCloudDef.TCCCCallParams callParams = new TCCCCloudDef.TCCCCallParams();
        if(callType == TCCCCloudDef.TCCCCallType.Voip) {
            callParams.channelId = GenerateTestUserSig.AUDIO_CHANNELID;
        } else {
            callParams.channelId = GenerateTestUserSig.VIDEO_CHANNELID;
        }
        callParams.sdkAppId = GenerateTestUserSig.SDKAPPID;
        callParams.userSig = userSig;
        callParams.userId = clinetUserId;
        callParams.type = callType;
        mTCCCCloud.startCall(callParams, new TXCallback() {
            @Override
            public void onSuccess() {
                mIsCalling = true;
                txt_tips.setText("等待接听...");
            }

            @Override
            public void onError(int i, String s) {
                stopNgoBack("呼叫失败，"+s);
            }
        });
    }

    private void stopNgoBack(String msg){
        txt_tips.setText(msg);
        showToast(msg);
        mainHandler.postDelayed(()->{
            Intent intent = new Intent(CallingActivity.this, MainActivity.class);
            startActivity(intent);
        },3000);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}