包名不能修改：
dev-manager
dev-loader

todo：
在线依赖，改成本地依赖
Androidx 支持

在又一个zip下如何增加一个 plugin.apk      ok
如何打包一个zip，（内置Loader、runtime）  ok
如何同时，加载两个 zip  ？
如何单独加载一个 plugin.apk
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

