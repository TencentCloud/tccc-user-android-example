# 快速集成用户端音频客服Android SDK

本文主要介绍如何快速地将腾讯云联络中心用户端音频客服 TCCC User SDK(Android) 集成到您的项目中，只要按照如下步骤进行配置，就可以完成 SDK 的集成工作。

## 开发环境要求
- Android Studio 3.5+。
- Android 4.1（SDK API 16）及以上系统。

## 集成 SDK（aar、jar）

### 手动下载（aar、jar）
目前我们暂时还未发布到 mavenCentral ，您只能手动下载 SDK 集成到工程里：

1. 下载最新版本 [TCCC User SDK](https://tccc.qcloud.com/assets/doc/user/release/TCCCCloud_android_aar_last.zip)。
2. 在工程根目录下的 build.gradle 中，指定本地仓库路径。
![](https://qcloudimg.tencent-cloud.cn/raw/0d3ee08fd77fef568776f5b693f8403a.png)
```
implementation fileTree(dir: "libs",includes: ['*.aar','*.jar'])
```
3. 在 app/build.gradle的defaultConfig 中，指定 App 使用的 CPU 架构。
```
defaultConfig {
       ndk {
           abiFilters "armeabi", "armeabi-v7a", "arm64-v8a"
       }
}
```
>?目前 TCCC User SDK 支持 armeabi ， armeabi-v7a 和 arm64-v8a。
4. 在 app/src/AndroidManifest.xml 中，指定 App 不允许应用参与备份和恢复基础架构。
![](https://qcloudimg.tencent-cloud.cn/raw/5ddbf9424b6f5157b17a61f368b54f20.png)
5. 单击![](https://main.qcloudimg.com/raw/d6b018054b535424bb23e42d33744d03.png)**Sync Now**，完成 TCCC User SDK 的集成工作。


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
```


## 设置混淆规则
在 proguard-rules.pro 文件，将 TCCC SDK 相关类加入不混淆名单：

```
-keep class com.tencent.** { *; }
```
## 代码实现
具体编码实现可参考 [API 概览以及示例](api.md)

## 常见问题
###  如何查看 TCCC 日志？
TCCC 的日志默认为后缀为 .log文件。
- Android：
    - 日志路径：`/sdcard/Android/data/包名/files/log/tccc/`

### TCCC Android 端能不能支持模拟器？
TCCC 目前版本暂时不支持，未来会支持模拟器。


### TCCC 怎么校验生成的 UserSig 是否正确？ 
可通过云 API 调用生成UserSig，具体可查看 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260) 接口文档


