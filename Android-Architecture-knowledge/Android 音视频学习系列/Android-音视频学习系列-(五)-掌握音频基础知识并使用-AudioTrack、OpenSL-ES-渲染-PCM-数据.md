![](https://upload-images.jianshu.io/upload_images/22976303-ed12f2123b389d4b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 前言

在讲解音频渲染之前，需要对音频的基础知识有所了解，所以该篇分为基础概念和AudioTrack 以及 OpenSL ES Demo 实例讲解，这样有助于更好的理解 Android 中音频渲染。

音频的基础概念涉及的知识点比较多，该篇文章的上半部分会详细的介绍，后续文章基本上都会涉及音频的开发，有了基础对于后面的内容就更容易上手了。

**更多系列教程GitHub白嫖入口：[https://github.com/Timdk857/Android-Architecture-knowledge-2-](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FTimdk857%2FAndroid-Architecture-knowledge-2-)**


## 音频的基础知识

### 声音的物理性质

*   声音是波

    说到声音我相信只要听力正常的人都听见过声音，那么声音是如何产生的呢？记得初中物理课本上的描述 - 声音是由物体的振动而产生的。其实声音是一种压力波，当敲打某个物体或演奏某个乐器时，它们的振动都会引起空气有节奏的振动，使周围的空气产生疏密变化，形成疏密相间的纵波，由此就产生了声波，这种现象会一直延续到振动消失为止。

*   声波的三要素

    声波的三要素是频率、振幅、和波形，频率代表音阶的高低，振幅代表响度，波形代表音色。

*   声音的传播介质

    声音的传播介质很广，它可以通过空气、液体和固体进行传播；而且介质不同，传播的速度也不同，比如声音在空气中的传播速度为 340m/s , 在蒸馏水中的传播速度为 1497 m/s , 而在铁棒中的传播速度则可以高达 5200 m/s ；不过，声音在真空中时无法传播的。

*   回声

    当我们在高山或者空旷地带高声大喊的时候，经常会听到回声，之所以会有回声是因为声音在传播过程中遇到障碍物会反弹回来，再次被我们听到。

    但是，若两种声音传到我们的耳朵里的时差小于 80 毫秒，我们就无法区分开这两种声音了，其实在日常生活中，人耳也在收集回声，只不过由于嘈杂的外接环境以及回声的分贝比较低，所以我们的耳朵分辨不出这样的声音，或者说是大脑能接收到但分辨不出。

*   共鸣

    自然界中有光能，水能，生活中有机械能，电能，其实声音也可以产生能量，例如两个频率相同的物体，敲打其中一个物体时另一个物体也会振动发生。这种现象称为共鸣，共鸣证明了声音传播可以带动另一个物体振动，也就是说，声音的传播过程也是一种能量的传播过程。

### 数字音频

上一小节我们主要介绍了声音的物理现象以及声音中常见的概念，也会后续的讲解统一了术语，本节主要介绍数字音频概念。

为了将模拟信号数字化，本节将分为 3 个概念对数字音频进行讲解，分别是**采样、量化和编码**。首先要对模拟信号进行采样，所谓采样就是在时间轴上对信号进行数字化。根据奈奎斯特定理（也称采样定理），按比声音最高频率高 2 倍以上的频率对声音进行采样，对于高质量的音频信号，其频率范围在 20Hz ~ 20kHz ，所以采样频率一般为 44.1kHz ,这样就保证采样声音达到 20kHz 也能被数字化，从而使得经过数字化处理之后，人耳听到的声音质量不会被降低。而所谓的 44.1 kHz 就是代表 1 s 会采样 44100 次。

那么，具体的每个采样又该如何表示呢？这就涉及到将要讲解的第二个概念: 量化。量化是指在幅度轴上对信号进行数字化，比如用 16 bit 的二进制信号来表示声音的一个采样，而 16 bit 所表示的范围是 [-32768 , 32767] , 共有 65536 个可能取值，因此最终模拟的音频信号在幅度上也分为了 65536 层。

既然每一个分量都是一个采样，那么这么多的采样该如何进行存储呢？这就涉及将要讲解的第三个概念: 编码。所谓编码，就是按照一定的格式记录采样和量化后的数字数据，比如顺序存储或压缩存储等等。

这里涉及了很多中格式，通常所说的音频的裸数据就是 PCM (Pulse Code Modulation) 数据。描述一段 PCM 数据一般需要以下几个概念：量化格式(sampleFormat)、采样率（sampleRate）、声道数 (channel) 。以 CD 的音质为例：量化格式为 16 bit （2 byte）,采样率 44100 ，声道数为 2 ，这些信息就描述了 CD 的音质。而对于声音的格式，还有一个概念用来描述它的大小，称为数据比特率，即 1s 时间内的比特数目，它用于衡量音频数据单位时间内的容量大小。而对于 CD 音质的数据，比特率为多少呢？ 计算如下:

```
44100 * 16 * 2 = 1378.125 kbps
```

那么在一分钟里，这类 CD 音质的数据需要占据多大的存储空间呢？计算如下:

```
1378.125 * 60 / 8 / 1024 = 10.09 MB
```

当然，如果 sampleFormat 更加精确 (比如用 4 个字节来描述一个采样)，或者 sampleRate 更加密集 (比如 48kHz 的采样率)， 那么所占的存储空间就会更大，同时能够描述的声音细节就会越精确。存储的这段二进制数据即表示将模拟信号转为数字信号了，以后就可以对这段二进制数据进行存储，播放，复制，或者进行其它操作。

### 音频编码

上面提到了 CD 音质的数据采样格式，曾计算出每分钟需要的存储空间约为 10.09 MB ,如果仅仅是将其存储在光盘或者硬盘中，可能是可以接受的，但是若要在网络中实时在线传输的话，那么这个数据量可能就太大了，所以必须对其进行压缩编码。压缩编码的基本指标之一就是压缩比，压缩比通常小于 1 。压缩算法包括有损压缩和无损压缩。无所压缩是指解压后的数据可以完全复原。在常用的压缩格式中，用的较多的是有损压缩，有损压缩是指解压后的数据不能完全恢复，会丢失一部分信息，压缩比越小，丢失的信息就比越多，信号还原后的失真就会越大。根据不同的应用场景 (包括存储设备、传输网络环境、播放设备等)，可以选用不同的压缩编码算法，如 PCM 、WAV、AAC 、MP3 、Ogg 等。

*   WAV 编码

    WAV 编码就是在 PCM 数据格式的前面加了 44 个字节，分别用来存储 PCM 的采样率、声道数、数据格式等信息。

    **特点:** 音质好，大量软件支持。

    **场景:** 多媒体开发的中间文件、保存音乐和音效素材。

*   MP3 编码

    MP3 具有不错的压缩比，使用 LAME 编码 （MP3 编码格式的一种实现）的中高码率的 MP3 文件，听感上非常接近源 WAV 文件，当然在不同的应用场景下，应该调整合适的参数以达到最好的效果。

    **特点:** 音质在 128 Kbit/s 以上表现还不错，压缩比比较高，大量软件和硬件都支持，兼容性好。

    **场景:** 高比特率下对兼容性有要求的音乐欣赏。

*   AAC 编码

    AAC 是新一代的音频有损压缩技术，它通过一些附加的编码技术(比如 PS 、SBR) 等，衍生出了 LC-AAC 、HE-AAC 、HE-AAC v2 三种主要的编码格式。LC-AAC 是比较传统的 AAC ,相对而言，其主要应用于中高码率场景的编码 (>=80Kbit/s) ; HE-AAC 相当于 AAC + SBR 主要应用于中低码率的编码 （<= 80Kbit/s）; 而新推出的 HE-AAC v2 相当于 AAC + SBR + PS 主要用于低码率场景的编码 (<= 48Kbit/s) 。事实上大部分编码器都设置为 <= 48Kbit/s 自动启用 PS 技术，而 > 48Kbit/s 则不加 PS ，相当于普通的 HE-AAC。

    **特点:** 在小于 128Kbit/s 的码率下表现优异，并且多用于视频中的音频编码。

    **场景:** 128 Kbit/s 以下的音频编码，多用于视频中音频轨的编码。

*   Ogg 编码

    Ogg 是一种非常有潜力的编码，在各种码率下都有比较优秀的表现，尤其是在中低码率场景下。Ogg 除了音质好之外，还是完全免费的，这为 Ogg 获得更多的支持打好了基础，Ogg 有着非常出色的算法，可以用更小的码率达到更好的音质，128 Kbit/s 的 Ogg 比 192kbit/s 甚至更高码率的 MP3 还要出色。但是目前因为还没有媒体服务软件的支持，因此基于 Ogg 的数字广播还无法实现。Ogg 目前受支持的情况还不够好，无论是软件上的还是硬件上的支持，都无法和 MP3 相提并论。

    **特点:** 可以用比 MP3 更小的码率实现比 MP3 更好的音质，高中低码率下均有良好的表现，兼容性不够好，流媒体特性不支持。

    **场景:** 语言聊天的音频消息场景。

## Android 平台下的音频渲染

音频基础概念上面讲完了，下面我们实现 Android 下的音频渲染，为实现音视频播放器打下一个基础，音视频采集视频录制的时候在讲解。


### AudioTrack 的使用

由于 AudioTrack 是 Android SDK 层提供的最底层的 音频播放 API,因此只允许输入裸数据 PCM 。和 MediaPlayer 相比，对于一个压缩的音频文件(比如 MP3 、AAC 等文件)，它只需要自行实现解码操作和缓冲区控制。因为这里只涉及 AudioTrack 的音频渲染端，编解码我们后面在讲解，所以本小节只介绍如何使用 AudioTrack 渲染音频 PCM 裸数据。

1.  配置 AudioTrack

    ```
    public AudioTrack(int streamType, int sampleRateInHz, int channelConfig, int audioFormat,
                int bufferSizeInBytes, int mode)
    ```

    **streamType**：Android 手机提供了多重音频管理策略，当系统又多个进程需要播放音频的时候，管理策略会决定最终的呈现效果，该参数的可选值将以常量的形式定义在类 AudioManager 中，主要包括以下内容:

    ```
        /**电话铃声 */
        public static final int STREAM_VOICE_CALL = AudioSystem.STREAM_VOICE_CALL;
        /** 系统铃声 */
        public static final int STREAM_SYSTEM = AudioSystem.STREAM_SYSTEM;
        /** 铃声*/
        public static final int STREAM_RING = AudioSystem.STREAM_RING;
        /** 音乐声 */
        public static final int STREAM_MUSIC = AudioSystem.STREAM_MUSIC;
        /** 警告声 */
        public static final int STREAM_ALARM = AudioSystem.STREAM_ALARM;
        /** 通知声 */
        public static final int STREAM_NOTIFICATION = AudioSystem.STREAM_NOTIFICATION;
    复制代码
    ```

    **sampleRateInHz**：采样率，即播放的音频每秒钟会有没少次采样，可选用的采样频率列表为: 8000 , 16000 , 22050 , 24000 ,32000 , 44100 , 48000 等，大家可以根据自己的应用场景进行合理的选择。

    **channelConfig:** 声道数的配置，可选值以常量的形式配置在类 AudioFormat 中，常用的是 CHANNEL_IN_MONO (单声道)、CHANNEL_IN_STEREO (双声道) ，因为现在大多数手机的麦克风都是伪立体声采集，为了性能考虑，建议使用单声道进行采集。

    **audioFormat:** 该参数是用来配置 "数据位宽" 的，即采样格式，可选值以常量的形式定义在类 AudioFormat 中，分别为 ENCODING_PCM_16BIT (兼容所有手机)、ENCODING_PCM_8BIT ，

    **bufferSizeInBytes:** 配置内部的音频缓冲区的大小， AudioTrack 类提供了一个帮助开发者确定的 bufferSizeInBytes 的函数，其原型具体如下:

    ```
     static public int getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat)
    ```

    在实际开发中，强烈建议由该函数计算出需要传入的缓冲区大小，而不是手动计算。

    **mode:** AudioTrack 提供了两种播放模式，可选的值以常量的形式定义在类 AudioTrack 中，一个是 MODE_STATIC , 需要一次性将所有的数据都写入播放缓冲区中，简单高效，通常用于播放铃声、系统提醒的音频片段；另一个是 MODE_STREAM ，需要按照一定的时间间隔不断地写入音频数据，理论上它可以应用于任何音频播放的场景。

2.  Play

    ```
    //当前播放实例是否初始化成功，如果处于初始化成功的状态并且未播放的状态，那么就调用 play
    if (null != mAudioTrack && mAudioTrack.getState() != AudioTrack.STATE_UNINITIALIZED && mAudioTrack.getPlayState() != PLAYSTATE_PLAYING)
       mAudioTrack.play();
    ```

3.  销毁资源

    ```
        public void release() {
            Log.d(TAG, "==release===");
            mStatus = Status.STATUS_NO_READY;
            if (mAudioTrack != null) {
                mAudioTrack.release();
                mAudioTrack = null;
            }
        }
    ```

4.  具体实例请移步 [AudioPlay 项目的 AudioTracker 部分](https://github.com/yangkun19921001/NDK_AV_SAMPLE/blob/master/audio_video/src/main/java/com/devyk/audiovideo/audio/AudioTracker.java)，需要把项目中 raw 目录下的 pcm 文件放入 sdcard 跟目录中。

### OpenSL ES 的使用

[OpenSL ES 官方文档](https://developer.android.google.cn/ndk/guides/audio/opensl-for-android)

OpenSL ES 全称(Open Sound Library for Embedded System) ,即嵌入式音频加速标准。OpenSL ES 是无授权费、跨平台、针对嵌入式系统精心优化的硬件音频加速 API ，它能为嵌入式移动多媒体设备上的本地应用程序开发者提供了标准化、高性能、低响应时间的音频功能实现方法，同时还实现了软/硬音频性能的直接跨平台部署，不仅降低了执行难度，而且还促进了高级音频市场的发展。

![](https://upload-images.jianshu.io/upload_images/22976303-498e9a841bf19c66.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


上图描述了 OpenSL ES 的架构，在 Android 中，High Level Audio Libs 是音频 Java 层 API 输入输出，属于高级 API , 相对来说，OpenSL ES 则是比价低层级的 API, 属于 C 语言 API 。在开发中，一般会直接使用高级 API , 除非遇到性能瓶颈，如语音实时聊天、3D Audio 、某些 Effects 等，开发者可以直接通过 C/C++ 开发基于 OpenSL ES 音频的应用。

在使用 OpenSL ES 的 API 之前，需要引入 OpenSL ES 的头文件，代码如下:

```
// 这是标准的OpenSL ES库
#include <SLES/OpenSLES.h>
// 这里是针对安卓的扩展，如果要垮平台则需要注意
#include <SLES/OpenSLES_Android.h>
```

1.  创建引擎并获取引擎接口

    ```
    void createEngine() {
            // 音频的播放，就涉及到了，OpenLSES
            // TODO 第一大步：创建引擎并获取引擎接口
            // 1.1创建引擎对象：SLObjectItf engineObject
            SLresult result = slCreateEngine(&engineObj, 0, NULL, 0, NULL, NULL);
            if (SL_RESULT_SUCCESS != result) {
                return;
            }

            // 1.2 初始化引擎
            result = (*engineObj) ->Realize(engineObj, SL_BOOLEAN_FALSE);
            if (SL_BOOLEAN_FALSE != result) {
                return;
            }

            // 1.3 获取引擎接口 SLEngineItf engineInterface
            result = (*engineObj) ->GetInterface(engineObj, SL_IID_ENGINE, &engine);
            if (SL_RESULT_SUCCESS != result) {
                return;
            }

        }
    ```

2.  设置混音器

    ```
    // TODO 第二大步 设置混音器
            // 2.1 创建混音器：SLObjectItf outputMixObject
            result = (*engine)->CreateOutputMix(engine, &outputMixObj, 0, 0, 0);

            if (SL_RESULT_SUCCESS != result) {
                return;
            }

            // 2.2 初始化 混音器
            result = (*outputMixObj)->Realize(outputMixObj, SL_BOOLEAN_FALSE);
            if (SL_BOOLEAN_FALSE != result) {
                return;
            }
    ```

3.  创建播放器

    ```
    // TODO 第三大步 创建播放器
        // 3.1 配置输入声音信息
        // 创建buffer缓冲类型的队列 2个队列
        SLDataLocator_AndroidSimpleBufferQueue locBufq = {SL_DATALOCATOR_ANDROIDSIMPLEBUFFERQUEUE, 2};
        // pcm数据格式
        // SL_DATAFORMAT_PCM：数据格式为pcm格式
        // 2：双声道
        // SL_SAMPLINGRATE_44_1：采样率为44100（44.1赫兹 应用最广的，兼容性最好的）
        // SL_PCMSAMPLEFORMAT_FIXED_16：采样格式为16bit （16位）(2个字节)
        // SL_PCMSAMPLEFORMAT_FIXED_16：数据大小为16bit （16位）（2个字节）
        // SL_SPEAKER_FRONT_LEFT | SL_SPEAKER_FRONT_RIGHT：左右声道（双声道）  （双声道 立体声的效果）
        // SL_BYTEORDER_LITTLEENDIAN：小端模式
        SLDataFormat_PCM formatPcm = {SL_DATAFORMAT_PCM, (SLuint32) mChannels, mSampleRate,
                                      (SLuint32) mSampleFormat, (SLuint32) mSampleFormat,
                                      mChannels == 2 ? 0 : SL_SPEAKER_FRONT_CENTER,
                                      SL_BYTEORDER_LITTLEENDIAN};
        /*
         * Enable Fast Audio when possible:  once we set the same rate to be the native, fast audio path
         * will be triggered
         */
        if (mSampleRate) {
            formatPcm.samplesPerSec = mSampleRate;
        }

        // 数据源 将上述配置信息放到这个数据源中
        SLDataSource audioSrc = {&locBufq, &formatPcm};

        // 3.2 配置音轨（输出）
        // 设置混音器
        SLDataLocator_OutputMix locOutpuMix = {SL_DATALOCATOR_OUTPUTMIX, mAudioEngine->outputMixObj};
        SLDataSink audioSink = {&locOutpuMix, nullptr};

        /*
         * create audio player:
         *     fast audio does not support when SL_IID_EFFECTSEND is required, skip it
         *     for fast audio case
         */
        //  需要的接口 操作队列的接口
        const SLInterfaceID ids[3] = {SL_IID_BUFFERQUEUE, SL_IID_VOLUME, SL_IID_EFFECTSEND};
        const SLboolean req[3] = {SL_BOOLEAN_TRUE, SL_BOOLEAN_TRUE, SL_BOOLEAN_TRUE};

        //  3.3 创建播放器
        result = (*mAudioEngine->engine)->CreateAudioPlayer(mAudioEngine->engine, &mPlayerObj,
                                                            &audioSrc, &audioSink,
                                                            mSampleRate ? 2 : 3, ids, req);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("CreateAudioPlayer failed: %d", result);
            return false;
        }

        //  3.4 初始化播放器：mPlayerObj
        result = (*mPlayerObj)->Realize(mPlayerObj, SL_BOOLEAN_FALSE);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj Realize failed: %d", result);
            return false;
        }
    //  3.5 获取播放器接口：SLPlayItf mPlayerObj
        result = (*mPlayerObj)->GetInterface(mPlayerObj, SL_IID_PLAY, &mPlayer);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj GetInterface failed: %d", result);
            return false;
        }
    ```

4.  设置播放回调函数

    ```
    // TODO 第四大步：设置播放回调函数
        // 4.1 获取播放器队列接口：SLAndroidSimpleBufferQueueItf mBufferQueue
        result = (*mPlayerObj)->GetInterface(mPlayerObj, SL_IID_BUFFERQUEUE, &mBufferQueue);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj GetInterface failed: %d", result);
            return false;
        }
    // 4.2 设置回调 void playerCallback(SLAndroidSimpleBufferQueueItf bq, void *context)
        result = (*mBufferQueue)->RegisterCallback(mBufferQueue, playerCallback, this);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj RegisterCallback failed: %d", result);
            return false;
        }

        mEffectSend = nullptr;
        if (mSampleRate == 0) {
            result = (*mPlayerObj)->GetInterface(mPlayerObj, SL_IID_EFFECTSEND, &mEffectSend);
            if (result != SL_RESULT_SUCCESS) {
                LOGE("mPlayerObj GetInterface failed: %d", result);
                return false;
            }
        }

        result = (*mPlayerObj)->GetInterface(mPlayerObj, SL_IID_VOLUME, &mVolume);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj GetInterface failed: %d", result);
            return false;
        }
    ```

5.  设置播放器状态

    ```
        // TODO 第五大步：设置播放器状态为播放状态
        result = (*mPlayer)->SetPlayState(mPlayer, SL_PLAYSTATE_PLAYING);
        if (result != SL_RESULT_SUCCESS) {
            LOGE("mPlayerObj SetPlayState failed: %d", result);
            return false;
        }
    ```

6.  手动激活回调函数

    ```
    void OpenSLAudioPlay::enqueueSample(void *data, size_t length) {
        // 必须等待一帧音频播放完毕后才可以 Enqueue 第二帧音频
        pthread_mutex_lock(&mMutex);
        if (mBufSize < length) {
            mBufSize = length;
            if (mBuffers[0]) {
                delete[] mBuffers[0];
            }
            if (mBuffers[1]) {
                delete[] mBuffers[1];
            }
            mBuffers[0] = new uint8_t[mBufSize];
            mBuffers[1] = new uint8_t[mBufSize];
        }
        memcpy(mBuffers[mIndex], data, length);
        // TODO 第六步：手动激活回调函数
        (*mBufferQueue)->Enqueue(mBufferQueue, mBuffers[mIndex], length);
        mIndex = 1 - mIndex;
    }
    ```

7.  释放资源

    ```
    extern "C"
    JNIEXPORT void JNICALL
    Java_com_devyk_audioplay_AudioPlayActivity_nativeStopPcm(JNIEnv *env, jclass type) {
        isPlaying = false;
        if (slAudioPlayer) {
            slAudioPlayer->release();
            delete slAudioPlayer;
            slAudioPlayer = nullptr;
        }
        if (pcmFile) {
            fclose(pcmFile);
            pcmFile = nullptr;
        }
    }
    ```

完整的代码请参考仓库中 [OpenSL ES](https://github.com/yangkun19921001/NDK_AV_SAMPLE/blob/master/audio_video/src/main/cpp/opensl/audio_play.cpp) 部分。注意：需要把 raw 中的 pcm 文件放入 sdcard 根目录下。

## 总结

该篇文章主要介绍了音频的一些基础知识和使用 AudioTrack 以及 OpenSL ES 来渲染裸流音频数据。大家可以根据我的源代码中在加深理解。

最后的页面效果:

![](https://upload-images.jianshu.io/upload_images/22976303-e16d1afef46dcf1d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


如有帮助到你，可以点击一波关注、点赞吗？感谢支持！

**更多系列教程GitHub白嫖入口：[https://github.com/Timdk857/Android-Architecture-knowledge-2-](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FTimdk857%2FAndroid-Architecture-knowledge-2-)**

> 作者：DevYK
> 链接：[https://juejin.im/post/5e1c0a4ce51d451c8771c487](https://links.jianshu.com/go?to=https%3A%2F%2Fjuejin.im%2Fpost%2F5e1c0a4ce51d451c8771c487)
