# 插件化 

目前市面上有许多Android插件化方案。每种方案都有各自的实现思路。而且随着`Google`对`对非 SDK API`管理逐渐严格。普遍认为一个比较好的插件化方案应该对Android系统做到`0 hook`。
我们这里不去讨论哪种插件化比较好，只是一起来看一下目前业界诸多插件化方案的实现，理解他们的思想，学习他们实现过程中用到的`Android`相关的诸多知识。

>`Android P`开始对`对非SDK API`进行严格管制，先来看一下`私有SDK API`: https://developer.android.google.cn/about/versions/pie/restrictions-non-sdk-interfaces

## [VirtualApk](https://github.com/didi/VirtualAPK)

`VirtualApk`是由滴滴开源的一款插件化框架。主要实现思路是`hook`系统多处对于`Android四大组件`的处理，以达到调用插件的四大组件的实现。我们这里主要看一下它是如何实现的、用到了哪些东西。顺便了解Adnroid四大组件的运行机制。
下面的文章不会去细究实现逻辑，只看`VirtualApk`关于一些关键点的实现思路。

### 如何解析一个插件APK

在`VirtualApk`中，一个插件会被打成一个`.apk`文件。因此加载插件其实就是加载这个`.apk`文件，那么如何加载一个`.apk`文件，并解析出这个文件中的信息，比如四大组件、resourse、类等等 :

<a href="VirtualApk/插件APK的解析.md">插件APK的解析</a>

其实不只是`VirtualApk`，很多其他插件化框架对于插件apk的解析也是这个思路。

上面了解了一个插件的apk的类、资源、四大组件信息时如何被加载到宿主中了。但是我们知道四大组件都需要在`manifest`文件中注册后才能运行，但是`插件`的四大组件是不可能预先在宿主的`manifest`文件中注册的，那么如何让它们运行起来呢？
接下来我们就来看一下宿主如何支持插件的四大组件运行的，首先来看最重要的`Activity` :

### 插件Activity的启动

在阅读源码过程中发现了一个问题: [PathClassLoader与DexClassLoader到底有什么不同](./PathClassLoader与DexClassLoader到底有什么不同.md)

`VirtualApk`主要是通过hook`Instrumentation`来实现对`插件Activity`运行支持的:

<a href="VirtualApk/插件Activity的启动.md">插件Activity的启动</a>

### 插件Service的运行管理

对于`插件Service`的支持和`插件Activity`类似，`VirtualApk`动态代理了`AMS`。不过不同的是它自己管理了一套`插件Service`的运行系统: 

<a href="VirtualApk/插件Service的运行管理.md">插件Service的运行管理</a>

### 插件BroadcastReceiver的管理

`VirtualAPK`对于插件的`BroadcastReceiver`处理十分简单，就是在加载插件的时候把插件的所有`BroadcastReceiver`转为动态广播并注册。所以就不去具体的看源码了，这里我们研究一下Android中的`BroadcastReceiver`的管理:

[从源码理解BroadcastReceiver](../AndroidFramework源码分析/从源码理解BroadcastReceiver的工作过程.md)

### 插件ContentProvider的处理

对于`插件ContentProvider`的运行支持类似于`插件Service`和`插件BroadcastReceiver`的思想结合体。我们继续分析一下:

[插件ContentProvider的处理](../AndroidFramework源码分析/ContentProvider启动过程分析.md)

### 解决资源冲突的问题

宿主apk和插件apk是两个不同的apk，他们在编译时都会产生自己的`resources.arsc`。即他们是两个独立的编译过程。那么它们的`resources.arsc`中的资源id必定是有相同的情况,那么在宿主加载插件的时候就会出现冲突，怎么解决呢 ？

[解决插件资源ID与宿主冲突的问题](VirtualApk/解决插件资源ID与宿主冲突的问题.md)


## [Replugin](https://github.com/Qihoo360/RePlugin)

如何防止进程之间的并发问题: [进程间的同步](Replugin/进程间的同步.md)

Replugin提供了一个单独的进程来管理插件，我们先来分析一下这个进程的工作原理 : [插件守护进程模型](Replugin/插件守护进程模型.md) [草稿]

【暂停分析，先着重看Android FrameWork 相关】...

