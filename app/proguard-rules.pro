# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\MyAndroidSofe\android2.0\sdk/tools/proguard/proguard-android.txt
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
#-keep public class com.koolearn.klibrary.ui.android.R$*{
#public static final int *;
#}
-keepclasseswithmembernames class * {
    native <methods>;
}
#eventbus
-keep class android.media.PlaybackParams { *;}
-keep class android.view.Display.** { *;}
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
static final long serialVersionUID;
private static final java.io.ObjectStreamField[] serialPersistentFields;
private void writeObject(java.io.ObjectOutputStream);

private void readObject(java.io.ObjectInputStream);

java.lang.Object writeReplace();

java.lang.Object readResolve();

}
    -keepattributes *Annotation*

#    -keepclassmembers class * {@com.xxx.Subscribe ;}
#
#    -keepclassmembers class * {@com.xxx.Action ;}



    -keep class * implements android.os.Parcelable {

    public static final android.os.Parcelable$Creator *;

    }


-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}
-keep class com.novel.ui.MainActivity{ *;}
-keep class com.koolearn.android.kooreader.KooReader{ *;}
-keep class com.koolearn.android.kooreader.TOCActivity{ *;}
-keep class com.google.android.gms.** { *;}
-dontwarn com.google.android.gms.*
-keep class com.google.android.** { *;}
-dontwarn com.google.android.*
-keep class com.facebook.share.** { *;}
-keep class com.google.ads.** { *;}
-keep class com.google.gson.** { *;}
-dontwarn org.apache.http.**
-keep class org.apache.http.** { *;}
-dontwarn android.support.**
-keep class android.support.** { *;}
-dontwarn android.net.http.**
-keep class android.net.http.** { *;}
-keep class com.firebase.** { *; }

-keep class android.net.** { *;}
-keepclassmembers public class * extends org.apache.http.entity.AbstractHttpEntity
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
#org.geometerplus. = com.koolean
#org.geometerplus.fbreader = com.koolearn.kooreader
#com.koolearn.klibrary = com.koolearn.klibrary
#org.geometerplus.android.fbreader = com.koolean.android.kooreader
-keep class com.android.vending.** { *; }
-keep class com.google.vending.** { *; }
-keep class com.koolean.android.kooreader.util.** { *; }
-keep class org.geometerplus.android.util.** { *; }
-keep class com.koolean.android.kooreader.** { *; }
-keep class com.koolearn.klibrary.core.application.** { *; }
-keep class com.koolearn.klibrary.core.constants.** { *; }
-keep class com.koolearn.klibrary.core.drm.** { *; }
-keep class com.koolearn.klibrary.core.drm.embedding.** { *; }
-keep class com.koolearn.klibrary.core.encodings.** { *; }
-keep class com.koolearn.klibrary.core.filesystem.** { *; }
-keep class com.koolearn.klibrary.core.filesystem.tar.** { *; }
-keep class com.koolearn.klibrary.core.filetypes.** { *; }
-keep class com.koolearn.klibrary.core.fonts.** { *; }
-keep class com.koolearn.klibrary.core.html.** { *; }
-keep class com.koolearn.klibrary.core.image.** { *; }
-keep class com.koolearn.klibrary.core.language.** { *; }
-keep class com.koolearn.klibrary.core.library.** { *; }
-keep class com.koolearn.klibrary.core.options.** { *; }
-keep class com.koolearn.klibrary.core.resources.** { *; }
-keep class com.koolearn.klibrary.core.tree.** { *; }
-keep class com.koolearn.klibrary.core.util.** { *; }
-keep class com.koolearn.klibrary.core.view.** { *; }
-keep class com.koolearn.klibrary.core.xml.** { *; }
-keep class com.koolearn.klibrary.text.model.** { *; }
#-keep class com.koolearn.klibrary.** { *; }
#-keep class com.koolearn.android.** { *; }
-keep class com.koolearn.kooreader.** { *; }

#com.koolearn.klibrary.text.view;

#com.koolearn.android.kooreader.libraryService
#-libraryjars /libs/commons-codec-1.6.jar
#-libraryjars /libs/commons-logging-1.1.3.jar
#-libraryjars /libs/fluent-hc-4.3.jar
#-libraryjars /libs/gson-2.2.4.jar
#-libraryjars /libs/httpclient-4.3.jar
#-libraryjars /libs/httpclient-cache-4.3.jar
#-libraryjars /libs/httpmime-4.3.jar
#-libraryjars libs\android-support-v4.jar
#-libraryjars <java.home>\lib\rt.jar
-keep class org.apache.commons.** { *; }
-dontwarn org.apache.commons.**
-keep class * implements android.os.Parcelable {

public static final android.os.Parcelable$Creator *;

}
-keep class com.google.ads.mediation.admob.AdMobAdapter {
    *;
}

-keep class com.google.ads.mediation.AdUrlAdapter {
    *;
}
#支付宝
-libraryjars libs/alipaySDK-20150602.jar
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}