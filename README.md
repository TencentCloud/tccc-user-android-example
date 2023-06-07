# 腾讯云呼叫中心 Android 用户端简单Demo
腾讯云呼叫中心支持安卓设备音视频通话，用户通过安卓设备发起通话，客服可在电脑端工作台接待。

- [快速跑通 Android Demo](QuickStartDemo.md)
- [快速集成 Android SDK](QuickStartSDK.md)
- [API 概览以及示例](api.md)

## 常见问题
###  如何查看 TCCC 日志？
TCCC 的日志默认为后缀为 .log文件。
- Android：
    - 日志路径：`/sdcard/Android/data/包名/files/log/tccc/`

### TCCC Android 端能不能支持模拟器？
TCCC 目前版本暂时不支持，未来会支持模拟器。


### TCCC 怎么校验生成的 UserSig 是否正确？
可通过云 API 调用生成UserSig，具体可查看 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260) 接口文档
