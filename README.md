# Deeplink Bypass Test
https://datatracker.ietf.org/doc/html/rfc3986

## How to install apk?
1. install apk from https://github.com/wizleysw/deeplink-test/blob/main/app/release/app-release.apk
2. clone this repository and run on AndroidStudio

## How to solve?
go through deeplink and bypass validation and open "attack-wizley.com". If you succeed, you may see SUCCEESS else FAIL.

## Stage
### deeplink entry
```xml
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.BROWSABLE" />
                <category android:name="android.intent.category.DEFAULT" />

                <data
                    android:host="stage1"
                    android:scheme="deeplink" />
            </intent-filter>

            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.BROWSABLE" />
                <category android:name="android.intent.category.DEFAULT" />

                <data
                    android:host="stage2"
                    android:scheme="deeplink" />
            </intent-filter>
```
### Goal
```kotlin
override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        val isSuccess = url?.toUri()?.host == "attack-wizley.com"
```

### Stage1
```kotlin
    private fun isValidHost(url: String): Boolean {
        if (url.isEmpty()) return false
        val host = url.toUri().host?.toLowerCase(Locale.ROOT) ?: return false

        return host.endsWith("wizley.com") || host.endsWith(".wizley.com")
    }
```
### Stage2
```kotlin
    private fun isValidHost2(url: String): Boolean {
        if (url.isEmpty()) return false
        val host = url.toUri().host?.toLowerCase(Locale.ROOT) ?: return false

        return host == "wizley.com" || host.endsWith(".wizley.com")
    }
```
