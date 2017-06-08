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
