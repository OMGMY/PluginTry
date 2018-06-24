# Plugin
## 基于代理方式的插件化开发，部分代码编译时生成，用AbstractProcessor来扫描和处理注解，使用JavaPoet来生成Java文件
## 使用注解处理器（Annotation Processor Tool）
### app为测试
### annotation为注解，Java Library Module
### compile为扫描和处理注解，同时生成Java文件，Java Library Module
### api为一些调用方法，Android Library Module 包含核心的插件内容
### plugin目录下为二次开发的主要地方
### 还支持方法的反射调用，目前主要是通过String[]来传输参数类型
###宿主端实现一个 PluginProxyActivity，使用这个Activity代理插件中的Activity的重要事务，例如生命周期调用、contentview设置、Activity跳转等事务。PluginProxyActivity注册在宿主中，启动插件中的Activity实际就是启动PluginProxyActivity
###插件activity继承至BasePluginAtivity，由接口统一来伪造生命周期。
###宿主APK和插件APK资源依赖包需要一致，不然会造成XML解析错误

