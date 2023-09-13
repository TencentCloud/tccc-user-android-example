# 快速跑通用户端Android Demo

本文主要介绍如何快速跑通腾讯云联络中心用户端 Android TCCC User Demo（音频客服），只要按照如下步骤进行配置，就可以跑通访客端的 Android Demo。

## 开发环境要求
- Android Studio 3.5+。
- Android 4.1（SDK API 16）及以上系统。

## 前提条件
- 您已 [注册腾讯云](https://cloud.tencent.com/document/product/378/17985) 账号，并完成 [实名认证](https://cloud.tencent.com/document/product/378/3629) 。
- 您已 [开通云联络中心](https://cloud.tencent.com/document/product/679/48028#.E6.AD.A5.E9.AA.A41.EF.BC.9A.E5.87.86.E5.A4.87.E5.B7.A5.E4.BD.9C) 服务，并创建了 [云联络中心实例](https://cloud.tencent.com/document/product/679/48028#.E6.AD.A5.E9.AA.A42.EF.BC.9A.E5.88.9B.E5.BB.BA.E4.BA.91.E5.91.BC.E5.8F.AB.E4.B8.AD.E5.BF.83.E5.AE.9E.E4.BE.8B) 。

## 操作步骤
[](id:step1)
### 步骤1：配置音频客服
1. 登录 [联络中心管理端](https://tccc.qcloud.com/login)，选择**登录的联络中心** > **管理端** > **音频客服** > **技能组管理** > **添加技能组** 。

![](https://qcloudimg.tencent-cloud.cn/image/document/fae6c3b42347a16a06f0dda49dfc0f48.png)

2. 添加 **新客服** 到对应的技能组中。

![](https://qcloudimg.tencent-cloud.cn/image/document/09f724109a6e89aa5d645725d8fa7440.png)

3. 新增 IVR， **登录的联络中心** > **管理端** > **音频客服** > **IVR管理** > **呼入IVR** > **新建**。

![](https://qcloudimg.tencent-cloud.cn/image/document/ac2d7643f2be4c01d9cd8de03a981db4.png)

4. 新建 IVR，在 IVR 画布左上角输入 IVR 名称，如：音频客服 IVR。根据您的场景需要拖拽 IVR 模块到画布区域合适的位置释放，连接模块并在各个模块填写相应信息。IVR 流程必须以开始模块为第一个模块，以结束模块为最后一个模块。

![](https://qcloudimg.tencent-cloud.cn/image/document/d05ee5a7c6d0cbaa8f747f48f1d92597.png)

5. 新增配置音频入口。 选择**管理端** > **渠道管理** > **配置音频入口** > **新增 **，输入音频入口名称，

![](https://qcloudimg.tencent-cloud.cn/image/document/fd65a7d5f1e2ce995ab908c28f70a761.png)

6. 复制音频入口 ID。

![](https://qcloudimg.tencent-cloud.cn/image/document/e69f60d860d3e98da2d827e19766d0b5.png)

[](id:step2)
### 步骤2：下载 SDK 和 tccc-user-android-example 源码
1. 根据实际业务需求 [tccc-user-android-example](https://github.com/TencentCloud/tccc-user-android-example) 源码。

[](id:step3)
### 步骤3：配置 tccc-user-android-example 工程文件
1. 找到并打开 `Debug/src/main/java/com/tencent/debug/GenerateTestUserSig.java` 文件。
3. 设置 `GenerateTestUserSig.java` 文件中的相关参数：
   <ul>
  <li/>AUDIO_CHANNELID：请设置为实际的 音频入口 ID。
  <li/>SDKAPPID：请设置为实际的腾讯云联络中心 SDKAppID。
	<li/>SECRETID：请设置为实际的密钥ID信息。
  <li/>SECRETKEY：请设置为实际的密钥Key信息。
  </ul>


![](https://qcloudimg.tencent-cloud.cn/image/document/83110c9d1dd003c0dc47e27dde352098.png)


>!
>- 本文提到的生成 UserSig 的方案是在客户端代码中配置 SECRETID、SECRETKEY，该方法中 SECRETID、SECRETKEY 很容易被反编译逆向破解，一旦您的密钥泄露，攻击者就可以盗用您的腾讯云流量，因此**该方法仅适合本地跑通 tccc-user-android-example 和功能调试**。
>- 正确的 UserSig 签发方式是将 UserSig 的计算代码集成到您的服务端，并提供面向 App 的接口，在需要 UserSig 时由您的 App 向业务服务器发起请求获取动态 UserSig。更多详情请参见 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260)。


### 步骤4：编译运行
使用 Android Studio（3.5及以上的版本）打开源码工程 `tccc-user-android-example`，单击**运行**即可。

### 运行效果
基本功能如下图所示

![](https://qcloudimg.tencent-cloud.cn/raw/c90d746b96c9fd935353d4a50853bb08.png)
