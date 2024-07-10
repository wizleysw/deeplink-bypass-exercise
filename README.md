# Deeplink Bypass Test

## How to install apk?
1. install apk from https://github.com/wizleysw/deeplink-test/blob/main/app/release/app-release.apk
2. clone this repository and run on AndroidStudio

## How to solve?
go through deeplink and bypass validation and open "attack-wizley.com". If you succeed, you may see SUCCEESS else FAIL.

## Stage
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
