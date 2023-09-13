

## API 概览
### 创建实例和事件回调
| API | 描述 |
|-----|-----|
| [sharedInstance](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a22af858f9d59111b6cf8f6a6258b7c11) | 创建 TCCCCloud 实例（单例模式） |
| [destroySharedInstance](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a201ba05e2bc74c4059a5300aaf494054) | 销毁 TCCCCloud 实例（单例模式）  |
| [setListener](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a15d9d2786df1d0982eda5d681fbd1643) | 设置 TCCC 事件回调 |

#### 创建实例和设置事件回调示例代码
```java
// 创建实例和设置事件回调
TCCCCloud mTCCCCloud = TCCCCloud.sharedInstance(this.getApplicationContext());
mTCCCCloud.setListener(new TCCCCloudListener() {});
```

### 呼叫相关接口函数
| API | 描述 |
|-----|-----|
| [startCall](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a5c1803884a887125ab4884962bd7a468) | 发起通话 |
| [terminate](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#add4f899b65a7c6fe5b8374052cb6cd82) | 结束通话 |
| [sendDTMF](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a9f00d6ed9bf6c9fa1137a4e4c72f4bfd) | 发送 DTMF（双音多频信号） |


#### 发起呼叫和结束呼叫示例代码
```java
// 发起音频呼叫
TCCCCloudDef.TCCCCallParams params =new TCCCCloudDef.TCCCCallParams();
params.channelId = "";  // 呼叫频道Id
params.userId= "";  // 用户标识（必填）
params.userSig = "";    // 用户数据签名（一次性有效，请每次重新生成）
params.clientData = ""; // 用户签名数据
params.sdkAppId = 0;    // 腾讯云联络中心应用ID（必填）,1400开头的
mTCCCCloud.startCall(params, new TXCallback() {
    @Override
    public void onSuccess() {
        // 发起呼叫中
    }

    @Override
    public void onError(int code, String desc) {
        // 呼叫失败
    }
});
// 用户主动结束呼叫或者结束通话
mTCCCCloud.terminate();

```

### 音频相关接口函数
| API | 描述 |
|-----|-----|
| [enableAudioVolumeEvaluation](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a5866b48256ee303f594779ca4c443d2b) | 启用音量大小提示 |
| [mute](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#ac03dba575826214db1ef67c0f754f4c3) | 静音 |
| [unmute](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a7f261eb0cd53b276626da36694583e6a) | 取消静音 |
| [startLocalAudio](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#ab3148068d47300eac5bb31ad15696de0) | 开启本地音频的采集和发布 |
| [setAudioRoute](https://tccc.qcloud.com/assets/doc/user/javaApiV2/interfacecom_1_1tencent_1_1tccc_1_1_t_c_c_c_device_manager.html#a3fc968a31ed5130fb960d0b594b7ac9b) | 设置音频路由（仅适用于移动端） |

#### 开启本地音频和停止本地音频示例代码
```java
// 开启本地音频采集
mTCCCCloud.startLocalAudio(TCCCCloudDef.TCCC_AUDIO_QUALITY_MUSIC);
// 静音
mTCCCCloud.mute();
// 取消静音
mTCCCCloud.unmute();
```

### 调试相关接口
| API | 描述 |
|-----|-----|
| [getSDKVersion](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#aaa77906b829b017001577c2629a8f789) | 获取 SDK 版本信息 |
| [setLogLevel](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#aba3e9b9071cfc262a4718d24f1b8d775) | 设置 Log 输出级别 |
| [setConsoleEnabled](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a3e1a5f4ce07b4969cea46c8c13f4245f) | 启用/禁用控制台日志打印 |
| [callExperimentalAPI](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud.html#a9ceb6b2d65f136922424bac65e4bec6c) | 调用实验性接口 |

#### 获取SDK版本示例代码
```java
// 获取SDK 版本号
mTCCCCloud.getSDKVersion();
```


### 错误和警告事件
| API | 描述 |
|-----|-----|
| [onError](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#a0608e16b140142c8e709ef4dc602ee8b) | 错误事件回调 |
| [onWarning](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#a11c8223c4ca981a7c05c677401895a70) | 警告事件回调 |
#### 处理错误回调事件回调示例代码
```java
mTCCCCloud.setListener(new TCCCCloudListener() {
    /**
        * 错误事件回调
        * 错误事件，表示 SDK 抛出的不可恢复的错误，比如进入房间失败或设备开启失败等。
        * @param errCode   错误码
        * @param errMsg    错误信息
        * @param extraInfo 扩展信息字段，个别错误码可能会带额外的信息帮助定位问题
        */
    @Override
    public void onError(int errCode, String errMsg, Bundle extraInfo) {
        super.onError(errCode, errMsg, extraInfo);
    }

    /**
        * 警告事件回调
        * 警告事件，表示 SDK 抛出的提示性问题，比如视频出现卡顿或 CPU 使用率太高等。
        * @param warningCode 警告码
        * @param warningMsg  警告信息
        * @param extraInfo   扩展信息字段，个别警告码可能会带额外的信息帮助定位问题
        */
    @Override
    public void onWarning(int warningCode, String warningMsg, Bundle extraInfo) {
        super.onWarning(warningCode, warningMsg, extraInfo);
    }
});
```

### 呼叫相关事件回调
| API | 描述 |
|-----|-----|
| [onAccepted](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#acced7a9ef75a95cf563a335a04c2e8b1) | 对方接听事件 |
| [onEnded](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#af4fd61e9ea213ede2325e67571dd3af0) | 通话结束事件 |

#### 处理接听和坐席挂断事件回调示例代码
```java
mTCCCCloud.setListener(new TCCCCloudListener() {
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
    }

    @Override
    public void onAccepted(String sessionId) {
        super.onAccepted(sessionId);
        // 已接通
    }
});
```

### 音视和网络质量相关事件回调
| API | 描述 |
|-----|-----|
| [onNetworkQuality](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#a7f6d647f01ae71bd9fcba258464a4e5d) | 网络质量的实时统计回调 |
| [onAudioVolume](https://tccc.qcloud.com/assets/doc/user/javaApiV2/classcom_1_1tencent_1_1tccc_1_1_t_c_c_c_cloud_listener.html#a71311b8e7e252ad48c1392f0cf3305c3) | 音量大小的反馈回调 |

```java
mTCCCCloud.setListener(new TCCCCloudListener() {
    @Override
    public void onAudioVolume(TCCCCloudDef.TCCCVolumeInfo volumeInfo) {
        super.onAudioVolume(volumeInfo);
    }

    @Override
    public void onNetworkQuality(TCCCCloudDef.TCCCQualityInfo localQuality, TCCCCloudDef.TCCCQualityInfo remoteQuality) {
        super.onNetworkQuality(localQuality, remoteQuality);
    }
});
```

### 与云端连接情况的事件回调
| API | 描述 |
|-----|-----|
| [onConnectionLost]() | SDK 与云端的连接已经断开 |
| [onTryToReconnect]() | SDK 正在尝试重新连接到云端 |
| [onConnectionRecovery]() | SDK 与云端的连接已经恢复 |

#### 与云端连接情况的事件回调示例代码

```java

mTCCCCloud.setListener(new TCCCCloudListener() {
    @Override
    public void onConnectionLost(TCCCServerType serverType) {
        super.onConnectionLost(serverType);
        // 与云端的连接已经断开
    }

    @Override
    public void onTryToReconnect(TCCCServerType serverType) {
        super.onTryToReconnect(serverType);
        // SDK 正在尝试重新连接到云端
    }

    @Override
    public void onConnectionRecovery(TCCCServerType serverType) {
        super.onConnectionRecovery(serverType);
        // SDK 与云端的连接已经恢复
    }
});

```



## API 错误码
### 基础错误码

| 符号 | 值 | 含义 |
|---|---|---|
|ERR_NONE|0|无错误|
|ERR_SIP_SUCCESS|200|成功|
|TCCC_SYSTEM_ERROR|-1002|TCCC 系统错误|
|ERR_HTTP_REQUEST_FAILURE|-10001|Http 请求失败，请检查网络连接情况|
|ERR_HTTP_TOKEN_ERROR|-10002|userSig登录票据不正确或者已过期或者userId不准确|
|ERR_NETWORK_CANNOT_RESET|-10004|正在通话中，禁止重置网络操作&发起外呼|


