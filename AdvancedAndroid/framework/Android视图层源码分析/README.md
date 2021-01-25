# Android视图层源码分析

>这个系列的文章只是大致了解一下Android视图层的工作原理, 并不会深入去探讨源码中的某个点, 毕竟整个视图层过于复杂，如果没有原因的去抓住分析某个细节并没有太大的意义。

## UI视图的渲染原理

`Window`是Android提供的用来构建UI的类，它是承载UI的基本单元。我们在正常开发中一般不会对他直接操作，不过我们所使用的`Activity/Dialog`等UI的展示都是依托于`Window`。
所以想要深入分析Android UI的展示原理，其实沿着`Window`这条线就可以了(目前Android Framework所提供的唯一`Window`实现类是`PhoneWindow`), 首先我们通过剖析`Window`的组成来大致了解一下`Window`: 

[深入剖析Window组成](深入剖析Window组成.md)

上面这篇文章只是简单的讲了`Window`的添加过程和`Activity`中`PhoneWindow`的UI组成结构。那`Window`的UI到底是怎么绘制的呢？

其实是通过`Surface`来显示的。每一个窗口都有一个自己的`Surface`,可以把它理解为一块画布，应用可以通过`Canvas`和`OpenGL`在上面作画。`Surface`的具体显示由`SurfaceFlinger`负责完成。`SurfaceFlinger`可以
将多块`Surface`的内容按照特定的顺序(Z-order)进行混合并输出到`FrameBuffer`，从而显示出用户所见到的`UI`。所以下面我们要好好研究一下`Surface`,首先看一下它是怎么创建的:

[Android的UI显示原理之Surface的创建](Android的UI显示原理之Surface的创建.md)

视图的基本承载单元`Surface`已经准备完毕，那么怎么渲染一个`Surface`呢?

[Android的UI显示原理之Surface的渲染](Android的UI显示原理之Surface的渲染.md)

上面3篇文章中的源码分析几乎包含了整个视图层源码的所有关键对象，下面总结一下这3篇文章:

[Android的UI显示原理总结](Android的UI显示原理总结.md)

## UI视图的用户交互事件处理原理

### 触摸事件原理分析

下面这篇文章分析一下: 触摸事件怎么产生和收集的? `Activity.dispatchTouchEvent()`是怎么调用到的?

[Android触摸事件全过程分析](Android触摸事件全过程分析.md)

最基本的事件分发流程还是要过一遍的:

[View事件传递机制源码走查](View事件传递机制源码走查.md)


## 绘制优化相关

[GPU 与 CPU](https://tech.meituan.com/2017/01/19/hardware-accelerate.html)

[Android Bitmap变迁与原理解析（4.x-8.x）](https://www.jianshu.com/p/d5714e8987f3)

[Android GPU呈现模式原理及卡顿掉帧浅析](https://www.jianshu.com/p/2ad8723865cc)

### 硬件加速 & OpenGL

[什么是OpenGL](https://www.zhihu.com/question/51867884)

[理解Android硬件加速原理(上)](https://www.jianshu.com/p/40f660e17a73)

[理解Android硬件加速原理(下)](https://www.jianshu.com/p/dd800800145b)