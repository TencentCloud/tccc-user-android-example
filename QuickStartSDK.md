# 快速集成Android SDK

本文主要介绍如何快速地将腾讯云呼叫中心 TCCC SDK(Android) 集成到您的项目中，只要按照如下步骤进行配置，就可以完成 SDK 的集成工作。

## 开发环境要求
- Android Studio 3.5+。
- Android 4.1（SDK API 16）及以上系统。

## 集成 SDK（aar、jar）

### 手动下载（aar、jar）
目前我们暂时还未发布到 mavenCentral ，您只能手动下载 SDK 集成到工程里：

1. 下载最新版本 [TCCC SDK](https://tccc.qcloud.com/assets/doc/user/android/TCCC_SDK_Android_latest.zip)。
2. 将下载到的 aar 文件拷贝到工程的 **app/libs** 目录下。如果你项目中也用到了 okhttp3.0 这个库，保留其中一个即可。
3. 在工程根目录下的 build.gradle 中，指定本地仓库路径。
![](https://qcloudimg.tencent-cloud.cn/raw/272b561db87fedec0442d41b757b0b53.png)
```
implementation fileTree(dir: "libs",includes: ['*.aar','*.jar'])
```
4. 在 app/build.gradle的defaultConfig 中，指定 App 使用的 CPU 架构。
```
defaultConfig {
       ndk {
           abiFilters "armeabi", "armeabi-v7a", "arm64-v8a"
       }
}
```
>?目前 TCCC SDK 支持 armeabi ， armeabi-v7a 和 arm64-v8a。
5. 在 app/src/AndroidManifest.xml 中，指定 App 不允许应用参与备份和恢复基础架构。
![](https://qcloudimg.tencent-cloud.cn/raw/5ddbf9424b6f5157b17a61f368b54f20.png)
6. 单击![](https://main.qcloudimg.com/raw/d6b018054b535424bb23e42d33744d03.png)**Sync Now**，完成 TCCC SDK 的集成工作。


## 配置 App 权限
在 AndroidManifest.xml 中配置 App 的权限，TCCC SDK 需要以下权限：
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-feature android:name="android.hardware.camera.autofocus" />
```

>! 请勿设置 `android:hardwareAccelerated="false"`，关闭硬件加速之后，会导致对方的视频流无法渲染。

## 设置混淆规则
在 proguard-rules.pro 文件，将 TCCC SDK 相关类加入不混淆名单：

```
-keep class com.tencent.** { *; }
```
## 代码实现
具体编码实现可参考 [API 概览以及示例](api.md)

## 常见问题
###  如何查看 TCCC 日志？
TCCC 的日志默认压缩加密，后缀为 .xlog。日志是否加密是可以通过 setLogCompressEnabled 来控制，生成的文件名里面含 C(compressed) 的就是加密压缩的，含 R(raw) 的就是明文的。
- Android：
	- 日志路径：`/sdcard/Android/data/包名/files/log/liteav/`
>?
>- 查看 .xlog 文件需要下载解密工具，在python 2.7环境中放到 xlog 文件同目录下直接使用 `python decode_mars_log_file.py` 运行即可。
>- 日志解密工具下载地址：`dldir1.qq.com/hudongzhibo/log_tool/decode_mars_log_file.py`，日志相关详情参考 [日志输出配置](https://cloud.tencent.com/developer/article/1502366)。

### TCCC Android 端能不能支持模拟器？
TCCC 目前版本暂时不支持，未来会支持模拟器。

###  两台设备同时运行 Demo，为什么画面、声音会断断续续？
请确保两台设备在运行 Demo 时使用的是不同的 clientUserID，TCCC 不支持同一个 clientUserID （除非 SDKAppID 不同）在两个设备同时使用。

### TCCC 怎么校验生成的 UserSig 是否正确？ 
可通过云 API 调用生成UserSig，具体可查看 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260) 接口文档

### TCCC 视频画面出现黑边怎么去掉？
设置 TCCC_VIDEO_RENDER_MODE_FILL（填充）即可解决，TCCC 视频渲染模式分为填充和适应，本地渲染画面可以通过 setLocalRenderParams() 设置，远端渲染画面可以通过 setRemoteRenderParams 设置：

- TCCC_VIDEO_RENDER_MODE_FILL：图像铺满屏幕，超出显示视窗的视频部分将被截掉，所以画面显示可能不完整。
- TCCC_VIDEO_RENDER_MODE_FIT：图像长边填满屏幕，短边区域会被填充黑色，但画面的内容肯定是完整的。

### TCCC 自己的本地画面和远端画面左右相反？
本地默认采集的画面是镜像的。App 端可以通过 setLocalRenderParams 接口设置 mirrorType ，该接口只改变本地摄像头预览画面的镜像模式；

