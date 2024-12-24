包名不能修改：
dev-manager
dev-loader

todo：
在线依赖，改成本地依赖
Androidx 支持

在又一个zip下如何增加一个 plugin.apk      ok
如何打包一个zip，（内置Loader、runtime）  ok
如何同时，加载两个 zip  ok  把loader+runtime 抽取出来，作为一个单独的zip，业务zip 依赖这个 zip
如何单独加载一个 plugin.apk ok loader+runtime,先加载，再加载 plugin.apk.zip
如何启动plugin的service

插件与宿主通信
宿主与插件通信
插件与插件通信

businessName = 'sample-plugin'//businessName相同的插件，context获取的Dir是相同的。businessName留空，表示和宿主相同业务，直接使用宿主的Dir。
如何生成 配置 uuid

version = 4
compactVersion = [1, 2, 3]

uuidNickName = "1.1.5"
标识一次插件发布的id，可以使用自定义格式描述版本信息

hostWhiteList 怎么用？
加载一个带so 的插件 功能验证
不同uuid插件加载


设计理念:
1.runtime和loader在common里
2.pluginA.zip和pluginB.zip只有config.json和业务apk

https://github.com/listen2code/Shadow
参考的是maven的Sample来做
提供参考，runtime和loader在common里，pluginA.zip和pluginB.zip只有config.json和业务apk，满足你的需求


//            Logger mLogger = LoggerFactory.getLogger(DynamicPluginManager.class);
//            AndroidLogLoggerFactory.getInstance().getLogger("xx").debug("xx");


不同uuid的插件包如何切换？
host 调用 插件 contentProvider
有so的插件加载测试  ok

升级插件包
插件中的service 应用外无法访问的。！！！！！！！！

插件中contentprovider 应用外无法访问的。！！！！！！！！
        cp
三方访问 插件   fail
宿主访问 插件   fail
插件访问 宿主    OK
插件访问 插件    OK
插件访问 三方    OK

SamplePluginManager 对应关系 很重要

插件进程可以在自己的AndroidManifest文件声明一个Activity为另一个单独进程吗？
不能，插件中Manifest的进程配置无效

插件包如何进行版本更新？
一个zip包里边有多个业务包

release包，OK

混淆:'com.tencent.shadow.plugin' 崩溃
解解方案：applicant（不混淆） + module（混淆）方案
参考：
plugin-other-lib          module
zip-plugin-other-apk      applicantion

plugin-common-lib         module
zip-plugin-common-apk     applicantion




