# 快速跑通用户端Android Demo

本文主要介绍如何快速跑通腾讯云呼叫中心用户端 Android TCCC User Demo（音频客服），只要按照如下步骤进行配置，就可以跑通访客端的 Android Demo。

## 开发环境要求
- Android Studio 3.5+。
- Android 4.1（SDK API 16）及以上系统。

## 前提条件
- 您已 [注册腾讯云](https://cloud.tencent.com/document/product/378/17985) 账号，并完成 [实名认证](https://cloud.tencent.com/document/product/378/3629) 。
- 您已 [开通云呼叫中心](https://cloud.tencent.com/document/product/679/48028#.E6.AD.A5.E9.AA.A41.EF.BC.9A.E5.87.86.E5.A4.87.E5.B7.A5.E4.BD.9C) 服务，并创建了 [云呼叫中心实例](https://cloud.tencent.com/document/product/679/48028#.E6.AD.A5.E9.AA.A42.EF.BC.9A.E5.88.9B.E5.BB.BA.E4.BA.91.E5.91.BC.E5.8F.AB.E4.B8.AD.E5.BF.83.E5.AE.9E.E4.BE.8B) 。

## 操作步骤
[](id:step1)
### 步骤1：配置音频客服
1. 登录 [呼叫中心管理端](https://tccc.qcloud.com/login)，选择**登录的呼叫中心** > **管理端** > **音频客服** > **技能组管理** > **添加技能组** 。
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a7065dd3655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKID_uzWdh8mQFyU5YvHDTuXSUzzRfY2L6mjIZF-X3T_KPbaM0n_sflNcDOIsYEF99FW&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=a78f8935862affe14e36cbfee1313a7a3e1b1751&x-cos-security-token=9iRKuUZemUEPZPFiwNeZEcm3wsbzstrabfb149a9ac6af8070703b9e0eb599621mdjhlxdxSTrgyoqCEP1AZSmI4Ca4r7K3Pe_9uF3zF2e9fz3WlVBQgQP2zHaWGUUnV-3hNimJqkKhxNOgHxNYmPBeKyvq2eJbxMFA8S8v2JNelcf22FFviDKlcR2PXszMrzGVhlgzhNpyAJTYGOZKllIfahptXJ3kKdSyc1jRI9vZGZ4ApFqMBEzPl4JtPCPIiZzQgG7cHM4VXh53y_Gdj9Ct06Hl7jbN30XBlNHkzXXef3o9ax5XnujSTQiDXdFniK3OiddKjXCob8barWOB7axHj-qhvxlkBnHSChuNKb1R0yaXEA0g6sDFMa1S2c5m4qlaXOakR83WxshdnFqBkgBuPERvFpUXaEgajwRvvhL4w-NsD6lsBUW7SjBzaC6m)

2. 添加 **新客服** 到对应的技能组中。
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a725d862655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKIDg_T1oGOtPve164NOyVlLrRa3Z1ioGA2cdxhJnzb2o0FZzUW3Yovlm5YQBJaBWTU1&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=ceb685578e29ee13927c6e17ab42fef8f4bf4ccb&x-cos-security-token=9iRKuUZemUEPZPFiwNeZEcm3wsbzstra0d3e304b9292efbd3d1e1da241bcb180mdjhlxdxSTrgyoqCEP1AZSmI4Ca4r7K3Pe_9uF3zF2e9fz3WlVBQgQP2zHaWGUUnV-3hNimJqkKhxNOgHxNYmPBeKyvq2eJbxMFA8S8v2JNelcf22FFviDKlcR2PXszMrzGVhlgzhNpyAJTYGOZKllIfahptXJ3kKdSyc1jRI9vZGZ4ApFqMBEzPl4JtPCPI6FKIlhoDk4_KE0FbghjBKNrc1H-TZ5644vmP7XDXqsyBJ_lO8SBdkAQJykFda6PxB1VE-UCcFhPyqqUN-UUk_CUfS7tzBLelPeihlYXe6LOs5rJWK5BSug_NedfTW1VR4Mcu7ajqIqNJ2mV-_NTNC-fLHhhiBq7urFX60z88MSbb91vh1YAjcjrhlXZDMYyR)

3. 新增 IVR， **登录的呼叫中心** > **管理端** > **音频客服** > **IVR管理** > **呼入IVR** > **新建**。
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a744c2b3655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKIDP4j7IUpRG3i9lansStK0MGJv0SZuu9JCcVHCEjcFxm8REFC0X_yXWMQzRIIQR5Un&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=5e104f136605ad9745a888a9490720016e9efd13&x-cos-security-token=h07o4UonLQo5SPyoIyB6ZD5lOkD4YfGaba421496e3a36ebb3856fbfed5038daaWrGuZPjk-muvv_8uc07QYLx9Wyv0YX33WE1Sv9d1PoxJCpTP-2ax4_Moa1p6XoOFfloDi9JmyoWmiZSyQJcLqZHaa-pakm-4P94Z9yJa9wYzjqMqC7Q-WYF-dcgD6u6OrsEWs_LgFczhk8-9ef-n_S_YPDCoBz1Awzs74oNvptqV7MalsWzKlriXslpkBsKijm8QuzfQSs8BAara-_7fJvDATOyfbexu50rT_FwMSeKrbEm2VAGyJIf-Joy9OewP_AaQav0F7_hEZFIFOuZIWCvuAFVGbDeH4G8OgJUSZjSw1qHPZd_CpOCd6KkVO2kgEeAOfAnDGbCU3JMMYOyQZBtoQiQtE9akjZU5VrFiw_2BMcvIGy-RUGMMQ4nJwHgS)

4. 新建 IVR，在 IVR 画布左上角输入 IVR 名称，如：音频客服 IVR。根据您的场景需要拖拽 IVR 模块到画布区域合适的位置释放，连接模块并在各个模块填写相应信息。IVR 流程必须以开始模块为第一个模块，以结束模块为最后一个模块。
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a758d2f2655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKIDxKSEBVP-psbkhDAO4oL51y9CCwU_-xv3nI43OzKOlaBttD4Sg45FHwgqIt36Oetp&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=0cac3c80bf7c327741e38c17688e3df13b0721db&x-cos-security-token=9iRKuUZemUEPZPFiwNeZEcm3wsbzstraaf2c3c151827fbe4aa7b2c55010e67fdmdjhlxdxSTrgyoqCEP1AZSmI4Ca4r7K3Pe_9uF3zF2e9fz3WlVBQgQP2zHaWGUUnV-3hNimJqkKhxNOgHxNYmPBeKyvq2eJbxMFA8S8v2JNelcf22FFviDKlcR2PXszMrzGVhlgzhNpyAJTYGOZKllIfahptXJ3kKdSyc1jRI9vZGZ4ApFqMBEzPl4JtPCPIGPpFtSjU9EuiyM8F-0xmLf14rw_mXs2S-d3-XjT_dEvIM18FltGSoV9WCXeqdUpQppK8DQAhx8uU1-sHbAThS7fNN_nzbBp3vUT3AU7aAg2ag6GxHy8uCAhy91zaqSwgZi6-pz6idMhnPyYw2XccAF1_iZjW6LNTNVvL5-tSLVzFmE7IYkWiPpkUlhwwWeK0)

5. 新增配置音频入口。 选择**管理端** > **渠道管理** > **配置音频入口** > **新增 **，输入音频入口名称，
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a771600f655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKIDnPMuTNBQXOMrL-y08wpQGNmGMlqSUfzPolPJtdVTHfp_SINZnEvrs6XkMOL8eeCw&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=0c46a1ae4b83093474dde81fcce958045d23bf63&x-cos-security-token=h07o4UonLQo5SPyoIyB6ZD5lOkD4YfGadcf5489bc5dad92ead80790e6c277549WrGuZPjk-muvv_8uc07QYLx9Wyv0YX33WE1Sv9d1PoxJCpTP-2ax4_Moa1p6XoOFfloDi9JmyoWmiZSyQJcLqZHaa-pakm-4P94Z9yJa9wYzjqMqC7Q-WYF-dcgD6u6OrsEWs_LgFczhk8-9ef-n_S_YPDCoBz1Awzs74oNvptqV7MalsWzKlriXslpkBsKid2iUWPFIHdC1VnuRPrzwBf6ZizJQ3JYbmsSZSfd54Kw12pkPs5vPEjHhY11VGi5-FEzjyO3sPv5iogNmqw1o5y3qpRYAhi-HMMimn2vJshk39cnuQWTqQ8PLpRRlyYlxp4JYaOz6BbWHIA7vv55wNlbkAwLvq451Gg2a2RSEGPyTa7vGrHSVxOMCHo9GrxSJ)

6. 复制音频入口 ID。
![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a7879519655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKID2Ry32MM8f4OL3vp4f6OzgUS2IblbRnBPbiwKTa-cbTVmAN65tvZ6q8DjAqVwHmtH&q-sign-time=1685603639;1685607239&q-key-time=1685603639;1685607239&q-header-list=&q-url-param-list=&q-signature=670e703b3e1ee16c1c098d44bff58744b8b87e6c&x-cos-security-token=h07o4UonLQo5SPyoIyB6ZD5lOkD4YfGad13f823162d6fc474fd10e6a0457d9acWrGuZPjk-muvv_8uc07QYLx9Wyv0YX33WE1Sv9d1PoxJCpTP-2ax4_Moa1p6XoOFfloDi9JmyoWmiZSyQJcLqZHaa-pakm-4P94Z9yJa9wYzjqMqC7Q-WYF-dcgD6u6OrsEWs_LgFczhk8-9ef-n_S_YPDCoBz1Awzs74oNvptqV7MalsWzKlriXslpkBsKibiYbLlFVHchiaIIII0El8B44qJeXlmKCSiH3H1l0BBWxr0tw2wSEL8ZF1i5tbc9wP41t4s_-XLHuEVXAeeQHnDvllnYp9YMhlipS07PkQ8vpM6icyNDOIHAN_7AvAgljW1AhOIo6RBV4stZlOw-dgSflvk4I3NO9iCeYiwbYPGEyxjZHsJEXyiLA_nDQy9v2)

[](id:step2)
### 步骤2：下载 SDK 和 tccc-user-android-example 源码
1. 根据实际业务需求 [tccc-user-android-example](https://github.com/TencentCloud/tccc-user-android-example) 源码。

[](id:step3)
### 步骤3：配置 tccc-user-android-example 工程文件
1. 找到并打开 `Debug/src/main/java/com/tencent/debug/GenerateTestUserSig.java` 文件。
3. 设置 `GenerateTestUserSig.java` 文件中的相关参数：
	<ul>
  <li/>AUDIO_CHANNELID：请设置为实际的 音频入口 ID。
  <li/>SDKAPPID：请设置为实际的腾讯云呼叫中心 SDKAppID。
	<li/>SECRETID：请设置为实际的密钥ID信息。
  <li/>SECRETKEY：请设置为实际的密钥Key信息。
  </ul>

![](https://write-document-release-1258344699.cos.ap-guangzhou.tencentcos.cn/100022348635/a6905d19655c11ed87ca525400463ef7.png?q-sign-algorithm=sha1&q-ak=AKIDTOjl_dkfL00mOVL0irXkqRFnGodK4NVwgFcXseR2UprWGY3ucD-DzORiTxc4872N&q-sign-time=1685604038;1685607638&q-key-time=1685604038;1685607638&q-header-list=&q-url-param-list=&q-signature=cf653c414b1d6f0ba6577c5614c00e4fb1c14a4b&x-cos-security-token=9iRKuUZemUEPZPFiwNeZEcm3wsbzstrabcdb2413b67171ddb2af02e4a6c33f2cmdjhlxdxSTrgyoqCEP1AZSMkVB1ofxFysE_aHRgnxMT0dZhJgr-eyapALUODkLhdYgYy2kUMIbIxOhIbMFgYjdO-JEndAkddtwnWbsAWLOe7VlNez8SUjwc-iFpVU4IgLqHkr80C4RgkzCcEXUKzFIGHTYX_ePecYQECCQHCN7WJJTYTMu2fz7e0kwiJzDwUy3Duyie_oqSjYVjifz-nhTl9vBwzoALSyOiWMy4o-VKq79cXVW4E3Kj83KV88rYr9U1mXGWQjL4d5orUf02jqJ65aK6MMooPE0stF-8SIhxGlK7i639Fph4BEfU4GSX3mTWsW8ej70tIZCQo77K9-mECExjgCPQGMTY0614WjX3D7Y42otke5T9gKwz6eaGd)


>!
>- 本文提到的生成 UserSig 的方案是在客户端代码中配置 SECRETID、SECRETKEY，该方法中 SECRETID、SECRETKEY 很容易被反编译逆向破解，一旦您的密钥泄露，攻击者就可以盗用您的腾讯云流量，因此**该方法仅适合本地跑通 tccc-user-android-example 和功能调试**。
>- 正确的 UserSig 签发方式是将 UserSig 的计算代码集成到您的服务端，并提供面向 App 的接口，在需要 UserSig 时由您的 App 向业务服务器发起请求获取动态 UserSig。更多详情请参见 [创建用户数据签名](https://cloud.tencent.com/document/product/679/58260)。


### 步骤4：编译运行
使用 Android Studio（3.5及以上的版本）打开源码工程 `tccc-user-android-example`，单击**运行**即可。

### 运行效果
基本功能如下图所示

![](https://qcloudimg.tencent-cloud.cn/raw/c90d746b96c9fd935353d4a50853bb08.png)
