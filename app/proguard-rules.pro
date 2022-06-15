# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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


#=========================#高德代码混淆star================
#3D 地图 V5.0.0之前：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.amap.mapcore.*{*;}
-keep   class com.amap.api.trace.**{*;}

#3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}

#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}

#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

#导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
#=========================高德代码混淆end==================

#======请避免混淆Bugly，在Proguard混淆文件中增加以下配置=========
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#混淆B
#Gson混淆
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.**.**.model.** {*;}
-keep class com.cheergotech.UI.model.**{*;}
-keepattributes *Annotation*
# 避免混淆泛型
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
-dontwarn com.google.**
-keep class com.google.gson.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-keep class com.google.firebase.**{*;}
-dontwarn com.google.firebase.**
-keep class sun.misc.Unsafe.**{ *; }
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** {*; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer