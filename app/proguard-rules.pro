# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Volumes/MacintoshHD/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

##XBanner 图片轮播混淆配置
#-keep class com.stx.xhb.xbanner.**{*;}

##浏览器混淆配置
#-keep class com.just.library.** {
#    *;
#}
#-dontwarn com.just.library.**
#-keepclassmembers class com.just.library.agentweb.AndroidInterface{ *; }

## nohttp
#-dontwarn com.yanzhenjie.nohttp.**
#-keep class com.yanzhenjie.nohttp.**{*;}
#
## nohttp-okhttp
#-dontwarn com.yanzhenjie.nohttp.**
#-keep class com.yanzhenjie.nohttp.**{*;}
#
## okhttp
#-dontwarn okhttp3.**
#-keep class okhttp3.** { *;}
#-dontwarn okio.**
#-keep class okio.** { *;}

##友盟统计
#-keepclassmembers class * {
#   public <init> (org.json.JSONObject);
#}
#-keep public class com.adrian.automat.R$*{
#public static final int *;
#}
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

##   高德地图
#    -keep   class com.amap.api.maps.**{*;}
#    -keep   class com.autonavi.**{*;}
#    -keep   class com.amap.api.trace.**{*;}
#
##    定位
#    -keep class com.amap.api.location.**{*;}
#    -keep class com.amap.api.fence.**{*;}
#    -keep class com.autonavi.aps.amapapi.model.**{*;}
#
##    搜索
#    -keep   class com.amap.api.services.**{*;}
#
##    2D地图
#    -keep class com.amap.api.maps2d.**{*;}
#    -keep class com.amap.api.mapcore2d.**{*;}
#
##    导航
#    -keep class com.amap.api.navi.**{*;}
#    -keep class com.autonavi.**{*;}

##极光推送
#-dontoptimize
#-dontpreverify
#
#-dontwarn cn.jpush.**
#-keep class cn.jpush.** { *; }
#-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }
#
#-dontwarn cn.jiguang.**
#-keep class cn.jiguang.** { *; }