![](https://upload-images.jianshu.io/upload_images/22976303-f82afb02163df8ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 简介

上一篇文章我们学习了音频的基础知识和音频的渲染之后，该篇我们学习视频的知识，与上一篇学习方式一样，基础 + demo ，主打渲染，采集跟编码我们后面学习播放器和录屏在来研究。

**更多系列教程GitHub白嫖入口：[https://github.com/Timdk857/Android-Architecture-knowledge-2-](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FTimdk857%2FAndroid-Architecture-knowledge-2-)**


## 视频的基础知识

### 图像的物理现象

做过 Camera 采集或者做过帧动画其实应该知道，视频是由一幅幅图像或者说一帧帧 YUV 数据组成，所以要学习视频还得从图像开始学习。

我们回顾一下，应该是初中的时候做过一个三棱镜实验，内容是如何利用三棱镜将太阳光分解成彩色的光带？第一个做这个实验者是 **牛顿** ，各色光因其所形成的折射角不同而彼此分离，就像彩虹一样，所以白光能够分解成多种色彩的光。后来人们通过实验证明，红绿蓝三种色光无法被分解，故称为三原色光，等量的三原色光相加会变为白光，即白光中含有等量的红光(R),绿光(G),蓝光(B)。

在日常生活中，由于光的反射，我们才能看到各类物体的轮廓和颜色。但是如果将这个理论应用到手机中，那么该结论还成立吗？答案是否定的，因为在黑暗中我们也可以看到手机屏幕中的内容，实际上人眼能看到手机屏幕上的内容的原理如下。

假设一部手机屏幕的分辨率是 1920 * 1080 说明水平方向有 1080 个像素点，垂直方向有 1920 个像素点，所以整个屏幕就有 1920 * 1080 个像素点(这也是分辨率的含义)。每个像素点都由三个子像素点组成，如下图所示，这些密密麻麻的子像素点在图像放大或者在显微镜下可以看得一清二楚。当要显示某篇文字或者某幅图像时，就会把这幅图像的每一个像素点的 RGB 通道分别对应的屏幕位置上的子像素点绘制到屏幕上，从而显示整个图像。

所以在黑暗的环境下也能看到手机屏幕上的内容，是因为手机屏幕是自发光的，而不是通过光的反射才被人们看到的。

![](https://upload-images.jianshu.io/upload_images/22976303-687d3a4d2633153f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


[图片来源](https://glumes.com/post/ffmpeg/understand-yuv-format/)

### 图像的数值表示

#### RGB 表示方式

通过上一小节我们清楚的知道任何一个图像都是由 RGB 组成，那么一个像素点的 RGB 该如何表示呢？音频里面的每一个采样 (sample) 均使用 16 bit 来表示，那么像素里面的子像素又该如何表示呢？通常的表示方式有以下几种。

*   浮点表示: 取值范围在 0.0 ~ 1.0 之间，比如在 OpenGL ES 中对每一个子像素点的表示使用的就是这种方式。
*   整数表示: 取值范围为 0 ~ 255 或者 00 ~ FF , 8 个 bit 表示一个子像素点，32 个 bit 表示一个像素，这就是类似某些平台上表示图像格式的 RGBA_8888 数据格式。比如 Android 平台上的 RGB_565 的表示方法为 16 个 bit 模式表示一个像素， R 用 5 个 bit , G 用 6 个 bit, B 用 5 个 bit 来表示。

对于一幅图像，一般使用整数表示方法进行描述，比如计算一张 1920 * 1080 的 RGB_8888 的图像大小，可采用如下计算方式:

```
1920 * 1080 * 4 / 1024 / 1024 ≈ 7.910 MB
复制代码
```

这也是 Bitmap 在内存中所占用的大小，所以每一张图像的裸数据都是很大的。对于图像的裸数据来说，直接来网络中进行传输也是不大可能的，所以就有了图像的压缩格式，比如我之前开源过一个基于 [JPEG 压缩](https://github.com/yangkun19921001/LIBJPEG_SAMPLE) :JPEG 是静态图像压缩标准，由 ISO 制定。 JPEG 图像压缩算法在提供良好的压缩性能的同时，具有较好的重建质量。这种算法被广泛应用于图像处理领域，当然它也是一种有损压缩。在很多网站如淘宝上使用的都是这种压缩之后的图像，但是，这种压缩不能直接应用于视频压缩，因为对于视频来讲，还有一个时域上的因素需要考虑，也就是说不仅仅要考虑帧内编码，还要考虑帧间编码。视频采用的是更加成熟的算法，关于视频压缩算法的相关内容我们会在后面进行介绍。

#### YUV 表示方式

对于视频帧的裸数据表示，其实更多的是 YUV 数据格式的表示， YUV 主要应用于优化彩色视频信号的传输，使其向后兼容老式黑白电视。在 RGB 视频信号传输相比，它最大的优点在于只需要占用极少的频宽(RGB 要求三个独立的视频信号同时传输)。其中 Y 表示明亮度，而 “U”,"V" 表示的则是色度值，它们的作用是描述影像的色彩及饱和度，用于指定像素的颜色。“亮度” 是透过 RGB 输入信号来建立的，方法时将 RGB 信号的特定部分叠加到一起。“色度” 则定义了颜色的两个方面 - 色调与饱和度，分别用 Cr 和 Cb 来表示。其中，Cr 反应了 RGB 输入信号红色部分与 RGB 信号亮度值之间的差异，而 Cb 反映的则是 RGB 输入信号蓝色部分与 RGB 信号亮度值之间的差异。

之所以采用 YUV 色彩空间，是因为它的亮度信号 Y 和色度信号 U、V 是分离的。如果只有 Y 信号分量而没有 U 、V 分量，那么这样表示的图像就是黑白灰图像。彩色电视采用 YUV 空间正是为了用亮度信号 Y 解决彩色电视机与黑白电视机的兼容问题，使黑白电视机也能接收彩色电视信号，最常用的表示形式是 Y、U、V 都使用 8 字节来表示，所以取值范围是 0 ~ 255 。 在广播电视系统中不传输很低和很高的数值，实际上是为了防止信号变动造成过载， Y 的取值范围都是 16 ~ 235 ，UV 的取值范围都是 16 ~ 240。

YUV 最常用的采样格式是 4:2:0 ， 4:2:0 并不意味着只有 Y 、Cb 而没有 Cr 分量。它指的是对每行扫描线来说，只有一种色度分量是以 2：1 的抽样率来存储的。相邻的扫描行存储着不同的色度分量，也就是说，如果某一行是 4：2：0，那么下一行就是 4：0：2，在下一行是 4：2：0，以此类推。对于每个色度分量来说，水平方向和竖直方向的抽象率都是 2：1，所以可以说色度的抽样率是 4：1。对非压缩的 8 bit 量化的视频来说，8*4 的一张图片需要占用 48 byte 内存。

相较于 RGB ，我们可以计算一帧为 1920 * 1080 的视频帧，用 YUV420P 的格式来表示，其数据量的大小如下:

```
(1920 * 1080 * 1 + 1920 * 1080 * 0.5 ) / 1024 /1024 ≈ 2.966MB
复制代码
```

如果 fps（1 s 的视频帧数量）是 25 ，按照 5 分钟的一个短视频来计算，那么这个短视频用 YUV420P 的数据格式来表示的话，其数据量的大小就是 :

```
2.966MB * 25fps * 5min * 60s / 1024 ≈ 21GB
复制代码
```

可以看到仅仅 5 分钟的视频数据量就能达到 21 G, 像抖音，快手这样短视频领域的代表这样的话还不卡死，那么如何对短视频进行存储以及流媒体播放呢？答案肯定是需要进行视频编码，后面会介绍视频编码的内容。

如果对 YUV 采样或者存储不明白的可以看这篇文章:[音视频基础知识---像素格式YUV](https://zhuanlan.zhihu.com/p/68532444)

#### YUV 和 RGB 的转化

前面已经讲过，凡是渲染到屏幕上的文字、图片、或者其它，都需要转为 RGB 的表示形式，那么 YUV 的表示形式和 RGB 的表示形式之间是如何进行转换的呢？可以参考该篇文章[YUV <——> RGB 转换算法](http://blog.shenyuanluo.com/ColorConverter.html), 相互转换 C++ 代码可以参考[地址](https://github.com/shenyuanluo/SYKit/tree/master/SYKit/SYConverter)

### 视频的编码方式

#### 视频编码

还记得上一篇文章我们学习的音频编码方式吗？音频的编码主要是去除冗余信息，从而实现数据量的压缩。那么对于视频压缩，又该从哪几个方面来对数据进行压缩呢？其实与之前提到的音频编码类似，视频压缩也是通过去除冗余信息来进行压缩的。相较于音频数据，视频数据有极强的相关性，也就是说有大量的冗余信息，包括空间上的冗余信息和时间上的冗余信息，具体包括以下几个部分。

*   运动补偿: 运动补偿是通过先前的局部图像来预测，，补偿当前的局部图像，它是减少帧序列冗余信息的有效方法。
*   运动表示: 不同区域的图像需要使用不同的运动矢量来描述运动信息。
*   运动估计: 运动估计是从视频序列中抽取运动信息的一整套技术。

使用帧内编码技术可以去除空间上的冗余信息。

大家还记得之前提到的图像编码 [JPEG](https://github.com/libjpeg-turbo/libjpeg-turbo) 吗？对于视频， ISO 同样也制定了标准: Motion JPEG 即 MPEG ，MPEG 算法是适用于动态视频的压缩算法，它除了对单幅图像进行编码外，还利用图像序列中的相关原则去除冗余，这样可以大大提高视频的压缩比，截至目前，MPEG 的版本一直在不断更新中，主要包括这样几个版本: Mpeg1(用于 VCD)、Mpeg2(用于 DVD)、Mpeg4 AVC(现在流媒体使用最多的就是它了)。

想比较 ISO 指定的 MPEG 的视频压缩标准，ITU-T 指定的 H.261、H.262、H.263、H.264 一系列视频编码标准是一套单独的体系。其中，H.264 集中了以往标准的所有优点，并吸取了以往标准的经验，采样的是简洁设计，这使得它比 Mpeg4 更容易推广。现在使用最多的就是 H.264 标准, H.264 创造了多参考帧、多块类型、整数变换、帧内预测等新的压缩技术，使用了更精准的分像素运动矢量(1/4、1/8) 和新一代的环路滤波器，这使得压缩性能得到大大提高，系统也变得更加完善。

#### 编码概念

视频编码中，每帧都代表着一幅静止的图像。而在进行实际压缩时，会采取各种算法以减少数据的容量，其中 IPB 帧就是最常见的一种。

##### IPB 帧

*   I 帧: 表示关键帧，你可以理解为这一帧画面的完整保留，解码时只需要本帧数据就可以完成(包含完整画面)。
*   P 帧: 表示的是当前 P 帧与上一帧( I 帧或者 P帧)的差别，解码时需要用之前缓存的画面叠加上本帧定义的差别生成最终画面。(也就是差别帧， P 帧没有完整画面数据，只有与前一帧的画面差别的数据。)
*   B 帧: 表示双向差别帧，也就是 B 帧记录的是当前帧与前后帧(前一个 I 帧或 P 帧和后面的 P 帧)的差别(具体比较复杂，有 4 种情况)， 换言之，要解码 B 帧，不仅要取得之前的缓存画面，还要解码之后的画面，通过前后画面数据与本帧数据的叠加取得最终的画面。B 帧压缩率高，但是解码时 CPU 会比较吃力。

##### IDR 帧与 I 帧的理解

在 H264 的概念中有一个帧称为 IDR 帧，那么 IDR 帧与 I 帧的区别是什么呢 ？ 首先要看下 IDR 的英文全称 instantaneous decoding refresh picture , 因为 H264 采用了多帧预测，所以 I 帧之后的 P 帧有可能会参考 I 帧之前的帧，这就使得在随机访问的时候不能以找到 I 帧作为参考条件，因为即使找到 I 帧，I 帧之后的帧还是有可能解析不出来，而 IDR 帧就是一种特殊的 I 帧，即这一帧之后的所有参考帧只会参考到这个 IDR 帧，而不会再参考前面的帧。在解码器中，一旦收到第一个 IDR 帧，就会立即清理参考帧缓冲区，并将 IDR 帧作为被参考的帧。

##### PTS 与 DTS

DTS 主要用视频的解码，全称为(Decoding Time Stamp), PTS 主要用于解码阶段进行视频的同步和输出， 全称为 (Presentation Time Stamp) 。在没有 B 帧的情况下， DTS 和 PTS 的输出顺序是一样的。因为 B 帧打乱了解码和显示的顺序，所以一旦存在 B 帧， PTS 与 DTS 势必就会不同。在大多数编解码标准(H.264 或者 HEVC) 中，编码顺序和输入顺序并不一致，于是才会需要 PTS 和 DTS 这两种不同的时间戳。

##### GOP 的概念

两个 I 帧之间形成的一组图片，就是 GOP (Group Of Picture) 的概念。通常在为编码器设置参数的时候，必须要设置 gop_size 的值，其代表的是两个 I 帧之间的帧数目。一个 GOP 中容量最大的帧就是 I 帧，所以相对来讲，gop_size 设置得越大，整个画面的质量就会越好，但是在解码端必须从接收到的第一个 I 帧开始才可以正确的解码出原始图像，否则会无法正确解码，在提高视频质量的技巧中，还有个技巧是多使用 B 帧，一般来说，I 的压缩率是 7 （与 JPG 差不多），P 是 20 ，B 可以达到 50 ，可见使用 B 帧能节省大量空间，节省出来的空间可以用来更多地保存 I 帧，这样就能在相同的码率下提供更好的画质，所以我们要根据不同的业务场景，适当地设置 gop_size 的大小，以得到更高质量的视频。

结合下图，希望可以帮组大家更好的理解 DTS 和 PTS 的概念。

![](https://upload-images.jianshu.io/upload_images/22976303-9213e8b51fc88d4a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 视频渲染

### OpenGL ES

#### 实现效果

![](https://upload-images.jianshu.io/upload_images/22976303-dfb65d14cbc818bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### 介绍

[OpenGL](https://www.khronos.org/opengl/) (Open Graphics Lib) 定义了一个跨编程语言、跨平台编程的专业图形程序接口。可用于二维或三维图像的处理与渲染，它是一个功能强大、调用方便的底层图形库。对于嵌入式的设备，其提供了 [OpenGL ES](https://developer.android.com/guide/topics/graphics/opengl?hl=zh-cN)(OpenGL for Embedded System) 版本，该版本是针对手机、Pad 等嵌入式设备而设计的，是 OpenGL 的一个子集。到目前为止，OpenGL ES 已经经历过很多版本的迭代与更新，到目前为止运用最广泛的还是 [OpenGL ES](https://www.khronos.org/opengles/) 2.0 版本。我们接下来所实现的 Demo 就是基于 OpenGL ES 2.0 接口进行编程并实现图像的渲染。

由于 OpenGL ES 是基于跨平台的设计，所以在每个平台上都要有它的具体实现，既要提供 OpenGL ES 的上下文环境以及窗口的管理。在 OpenGL 的设计中，OpenGL 是不负责管理窗口的。那么在 Android 平台上其实是使用 EGL 提供本地平台对 OpenGL ES 的实现。

#### 使用

要在 Android 平台下使用 OpenGL ES , 第一种方式是直接使用 GLSurfaceView ，通过这种方式使用 OpenGL ES 比较简单，因为不需要开发者搭建 OpenGL ES 的上下文环境，以及创建 OpenGL ES 的显示设备。但是凡事都有两面，有好处也有坏处，使用 GLSurfaceView 不够灵活，很多真正的 OpenGL ES 的核心用法(比如共享上下文来达到多线程使用 EGL 的 API 来搭建的，并且是基于 C++ 的环境搭建的。因为如果仅仅在 Java 层编写 ，那么对于普通的应用也许可行，但是对于要进行解码或者使用第三方库的场景(比如人脸识别)，则需要到 C++ 层来实施。处于效率和性能的考虑，这里的架构将直接使用 Native 层的 EGL 搭建一个 OpenGL ES 的开发环境。要想在 Native 层使用 EGL ,那么就必须在 CmakeLists.txt 中添加 EGL 库(可以参考如下提供的 CMakeLists 文件配置)，并在使用该库的 C++ 文件中引入对应的头文件，需要引如的头文件地址如下:

```
//1\. 在开发中如果要使用 EGL 需要在 CMakeLists.txt 中添加 EGL 库，并指定头文件

//使用 EGL 需要添加的头文件
#include <EGL/egl.h>
#include <EGL/eglext.h>

//2\. 使用 OpenGL ES 2.0  也需要在 CMakeLists.txt 中添加 GLESv2 库，并指定头文件

//使用 OpenGL ES 2.0 需要添加的头文件
#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>

```

CMakeLists 文件配置:

```
cmake_minimum_required(VERSION 3.4.1)

#音频渲染
set(OpenSL ${CMAKE_SOURCE_DIR}/opensl)
#视频渲染
set(OpenGL ${CMAKE_SOURCE_DIR}/gles)

#批量添加自己编写的 cpp 文件,不要把 *.h 加入进来了
file(GLOB ALL_CPP ${OpenSL}/*.cpp ${OpenGL}/*.cpp)

#添加自己编写 cpp 源文件生成动态库
add_library(audiovideo SHARED ${ALL_CPP})

#找系统中 NDK log库
find_library(log_lib
        log)

#最后才开始链接库
target_link_libraries(
				#最后生成的 so 库名称
        audiovideo
        #音频渲染
        OpenSLES

        # OpenGL 与 NativeWindow 连接本地窗口的中间者
        EGL
        #视频渲染
        GLESv2
        #添加本地库
        android

        ${log_lib}
)

```

至此，对于 OpenGL 的开发需要用到的头文件以及库文件就引入完毕了，下面再来看看如何使用 EGL 搭建出 OpenGL 的上下文环境以及渲染视频数据。

*   1.  使用 EGL 首先必须创建，建立本地窗口系统和 OpenGL ES 的连接

        ```
        //1.获取原始窗口
        nativeWindow = ANativeWindow_fromSurface(env, surface);
        //获取Display
        display = eglGetDisplay(EGL_DEFAULT_DISPLAY);
        if (display == EGL_NO_DISPLAY) {
                LOGD("egl display failed");
                showMessage(env, "egl display failed", false);
                return;
        }
  
        ```

*   2.  初始化 EGL

        ```
        //初始化egl，后两个参数为主次版本号
            if (EGL_TRUE != eglInitialize(display, 0, 0)) {
                LOGD("eglInitialize failed");
                showMessage(env, "eglInitialize failed", false);
                return;
            }
      
        ```

*   3.  确定可用的渲染表面（ Surface ）的配置。

        ```
            //surface 配置，可以理解为窗口
            EGLConfig eglConfig;
            EGLint configNum;
            EGLint configSpec[] = {
                    EGL_RED_SIZE, 8,
                    EGL_GREEN_SIZE, 8,
                    EGL_BLUE_SIZE, 8,
                    EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
                    EGL_NONE
            };

            if (EGL_TRUE != eglChooseConfig(display, configSpec, &eglConfig, 1, &configNum)) {
                LOGD("eglChooseConfig failed");
                showMessage(env, "eglChooseConfig failed", false);
                return;
            }
  
        ```

*   4.  创建渲染表面 surface（4/5步骤可互换）

        ```
        //创建surface(egl和NativeWindow进行关联。最后一个参数为属性信息，0表示默认版本)
            winSurface = eglCreateWindowSurface(display, eglConfig, nativeWindow, 0);
            if (winSurface == EGL_NO_SURFACE) {
                LOGD("eglCreateWindowSurface failed");
                showMessage(env, "eglCreateWindowSurface failed", false);
                return;
            }
 
        ```

*   5.  创建渲染上下文 Context

        ```
            //4 创建关联上下文
            const EGLint ctxAttr[] = {
                    EGL_CONTEXT_CLIENT_VERSION, 2, EGL_NONE
            };
            //EGL_NO_CONTEXT表示不需要多个设备共享上下文
            context = eglCreateContext(display, eglConfig, EGL_NO_CONTEXT, ctxAttr);
            if (context == EGL_NO_CONTEXT) {
                LOGD("eglCreateContext failed");
                showMessage(env, "eglCreateContext failed", false);
                return;
            }
        ```

*   6.  指定某个 EGLContext 为当前上下文, 关联起来

        ```
            //将egl和opengl关联
            //两个surface一个读一个写。第二个一般用来离线渲染
            if (EGL_TRUE != eglMakeCurrent(display, winSurface, winSurface, context)) {
                LOGD("eglMakeCurrent failed");
                showMessage(env, "eglMakeCurrent failed", false);
                return;
            }
        ```

*   7.  使用 OpenGL 相关的 API 进行绘制操作

        ```
            GLint vsh = initShader(vertexShader, GL_VERTEX_SHADER);
            GLint fsh = initShader(fragYUV420P, GL_FRAGMENT_SHADER);

            //创建渲染程序
            GLint program = glCreateProgram();
            if (program == 0) {
                LOGD("glCreateProgram failed");
                showMessage(env, "glCreateProgram failed", false);
                return;
            }

            //向渲染程序中加入着色器
            glAttachShader(program, vsh);
            glAttachShader(program, fsh);

            //链接程序
            glLinkProgram(program);
            GLint status = 0;
            glGetProgramiv(program, GL_LINK_STATUS, &status);
            if (status == 0) {
                LOGD("glLinkProgram failed");
                showMessage(env, "glLinkProgram failed", false);
                return;
            }
            LOGD("glLinkProgram success");
            //激活渲染程序
            glUseProgram(program);

            //加入三维顶点数据
            static float ver[] = {
                    1.0f, -1.0f, 0.0f,
                    -1.0f, -1.0f, 0.0f,
                    1.0f, 1.0f, 0.0f,
                    -1.0f, 1.0f, 0.0f
            };

            GLuint apos = static_cast<GLuint>(glGetAttribLocation(program, "aPosition"));
            glEnableVertexAttribArray(apos);
            glVertexAttribPointer(apos, 3, GL_FLOAT, GL_FALSE, 0, ver);

            //加入纹理坐标数据
            static float fragment[] = {
                    1.0f, 0.0f,
                    0.0f, 0.0f,
                    1.0f, 1.0f,
                    0.0f, 1.0f
            };
            GLuint aTex = static_cast<GLuint>(glGetAttribLocation(program, "aTextCoord"));
            glEnableVertexAttribArray(aTex);
            glVertexAttribPointer(aTex, 2, GL_FLOAT, GL_FALSE, 0, fragment);

            //纹理初始化
            //设置纹理层对应的对应采样器？

            /**
             *  //获取一致变量的存储位置
            GLint textureUniformY = glGetUniformLocation(program, "SamplerY");
            GLint textureUniformU = glGetUniformLocation(program, "SamplerU");
            GLint textureUniformV = glGetUniformLocation(program, "SamplerV");
            //对几个纹理采样器变量进行设置
            glUniform1i(textureUniformY, 0);
            glUniform1i(textureUniformU, 1);
            glUniform1i(textureUniformV, 2);
             */
            //对sampler变量，使用函数glUniform1i和glUniform1iv进行设置
            glUniform1i(glGetUniformLocation(program, "yTexture"), 0);
            glUniform1i(glGetUniformLocation(program, "uTexture"), 1);
            glUniform1i(glGetUniformLocation(program, "vTexture"), 2);
            //纹理ID
            GLuint texts[3] = {0};
            //创建若干个纹理对象，并且得到纹理ID
            glGenTextures(3, texts);

            //绑定纹理。后面的的设置和加载全部作用于当前绑定的纹理对象
            //GL_TEXTURE0、GL_TEXTURE1、GL_TEXTURE2 的就是纹理单元，GL_TEXTURE_1D、GL_TEXTURE_2D、CUBE_MAP为纹理目标
            //通过 glBindTexture 函数将纹理目标和纹理绑定后，对纹理目标所进行的操作都反映到对纹理上
            glBindTexture(GL_TEXTURE_2D, texts[0]);
            //缩小的过滤器
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            //放大的过滤器
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            //设置纹理的格式和大小
            // 加载纹理到 OpenGL，读入 buffer 定义的位图数据，并把它复制到当前绑定的纹理对象
            // 当前绑定的纹理对象就会被附加上纹理图像。
            //width,height表示每几个像素公用一个yuv元素？比如width / 2表示横向每两个像素使用一个元素？
            glTexImage2D(GL_TEXTURE_2D,
                         0,//细节基本 默认0
                         GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个亮度的颜色通道的意思）
                         width,//加载的纹理宽度。最好为2的次幂(这里对y分量数据当做指定尺寸算，但显示尺寸会拉伸到全屏？)
                         height,//加载的纹理高度。最好为2的次幂
                         0,//纹理边框
                         GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                         GL_UNSIGNED_BYTE,//像素点存储的数据类型
                         NULL //纹理的数据（先不传）
            );

            //绑定纹理
            glBindTexture(GL_TEXTURE_2D, texts[1]);
            //缩小的过滤器
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            //设置纹理的格式和大小
            glTexImage2D(GL_TEXTURE_2D,
                         0,//细节基本 默认0
                         GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个颜色通道的意思）
                         width / 2,//u数据数量为屏幕的4分之1
                         height / 2,
                         0,//边框
                         GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                         GL_UNSIGNED_BYTE,//像素点存储的数据类型
                         NULL //纹理的数据（先不传）
            );

            //绑定纹理
            glBindTexture(GL_TEXTURE_2D, texts[2]);
            //缩小的过滤器
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            //设置纹理的格式和大小
            glTexImage2D(GL_TEXTURE_2D,
                         0,//细节基本 默认0
                         GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个颜色通道的意思）
                         width / 2,
                         height / 2,//v数据数量为屏幕的4分之1
                         0,//边框
                         GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                         GL_UNSIGNED_BYTE,//像素点存储的数据类型
                         NULL //纹理的数据（先不传）
            );

            unsigned char *buf[3] = {0};
            buf[0] = new unsigned char[width * height];//y
            buf[1] = new unsigned char[width * height / 4];//u
            buf[2] = new unsigned char[width * height / 4];//v

            showMessage(env, "onSucceed", true);

            FILE *fp = fopen(data_source, "rb");
            if (!fp) {
                LOGD("oepn file %s fail", data_source);
                return;
            }

            while (!feof(fp)) {
                //解决异常退出,终止读取数据
                if (!isPlay)
                    return;
                fread(buf[0], 1, width * height, fp);
                fread(buf[1], 1, width * height / 4, fp);
                fread(buf[2], 1, width * height / 4, fp);

                //激活第一层纹理，绑定到创建的纹理
                //下面的width,height主要是显示尺寸？
                glActiveTexture(GL_TEXTURE0);
                //绑定y对应的纹理
                glBindTexture(GL_TEXTURE_2D, texts[0]);
                //替换纹理，比重新使用glTexImage2D性能高多
                glTexSubImage2D(GL_TEXTURE_2D, 0,
                                0, 0,//相对原来的纹理的offset
                                width, height,//加载的纹理宽度、高度。最好为2的次幂
                                GL_LUMINANCE, GL_UNSIGNED_BYTE,
                                buf[0]);

                //激活第二层纹理，绑定到创建的纹理
                glActiveTexture(GL_TEXTURE1);
                //绑定u对应的纹理
                glBindTexture(GL_TEXTURE_2D, texts[1]);
                //替换纹理，比重新使用glTexImage2D性能高
                glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, width / 2, height / 2, GL_LUMINANCE,
                                GL_UNSIGNED_BYTE,
                                buf[1]);

                //激活第三层纹理，绑定到创建的纹理
                glActiveTexture(GL_TEXTURE2);
                //绑定v对应的纹理
                glBindTexture(GL_TEXTURE_2D, texts[2]);
                //替换纹理，比重新使用glTexImage2D性能高
                glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, width / 2, height / 2, GL_LUMINANCE,
                                GL_UNSIGNED_BYTE,
                                buf[2]);

                glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
                //8\. 窗口显示，交换双缓冲区
                eglSwapBuffers(display, winSurface);
            }
        ```

*   8.  交换 EGL 的 Surface 的内部缓冲和 EGL 创建的和平台无关的窗口 diaplay

        ```
        //窗口显示，交换双缓冲区
        eglSwapBuffers(display, winSurface);
        复制代码
        ```

*   9.  释放资源

        ```
        /**
         * 销毁数据
         */
        void Gles_play::release() {
            if (display || winSurface || context) {
                //销毁显示设备
                eglDestroySurface(display, winSurface);
                //销毁上下文
                eglDestroyContext(display, context);
                //释放窗口
                ANativeWindow_release(nativeWindow);
                //释放线程
                eglReleaseThread();
                //停止
                eglTerminate(display);
                eglMakeCurrent(display, winSurface, EGL_NO_SURFACE, context);
                context = EGL_NO_CONTEXT;
                display = EGL_NO_SURFACE;
                winSurface = nullptr;
                winSurface = 0;
                nativeWindow = 0;
                isPlay = false;

            }

        }
        复制代码
        ```

到这里整个 OpenGL ES 渲染工作都完成了，代码已上传到 [GitHub 仓库，需要的可以自行查看](https://github.com/yangkun19921001/NDK_AV_SAMPLE/tree/master/audio_video) 在提供一个 [Java 端实现 OpenGL ES 实时渲染 YUV 的 DEMO](https://github.com/yangkun19921001/YUVPlay),注意: 测试的时候需要把 raw/*.yuv 放入 sdcard/ 根目录中。

## 总结

本章的概念比较多，难免会枯燥一些，但是了解这些概念是必须的。下一篇将带来 FFmpeg + LibRtmp 播放器开发练习，支持 rtmp 拉流、本地视频播放(该篇文章和上一篇文章都分别讲解了音频视频基础和渲染就是为了播放器开发做准备)，可以先看一下效果(如下图)。

![](https://upload-images.jianshu.io/upload_images/22976303-e91ac9b3cfd46f89.gif?imageMogr2/auto-orient/strip)


如有帮助到你，可以点击一波关注、点赞吗？感谢支持！

**更多系列教程GitHub白嫖入口：[https://github.com/Timdk857/Android-Architecture-knowledge-2-](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FTimdk857%2FAndroid-Architecture-knowledge-2-)**

> 作者：DevYK
> 链接：[https://juejin.im/post/5e1c0a4ce51d451c8771c487](https://links.jianshu.com/go?to=https%3A%2F%2Fjuejin.im%2Fpost%2F5e1c0a4ce51d451c8771c487)


