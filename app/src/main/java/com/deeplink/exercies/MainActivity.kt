package com.deeplink.exercies

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val resultTextView
        get() = findViewById<TextView>(R.id.result)

    private val webView
        get() = findViewById<WebView>(R.id.webview)

    private var stage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                val isSuccess = url?.toUri()?.host == "attack-wizley.com"

                resultTextView.text = buildString {
                    append("STAGE$stage: ")
                    append(isSuccess.toResult())
                }
            }
        }

        parseIntent()
    }

    private fun Boolean.toResult(): String {
        return if (this) "SUCCESS" else "FAIL"
    }

    private fun parseIntent() {
        val deeplink = intent.data ?: return

        if (deeplink.scheme == "deeplink") {
            when (deeplink.host) {
                "stage1" -> {
                    stage = 1
                    val url = deeplink.getQueryParameter("url")
                    if (isValidHost(url.toString())) {
                        webView.loadUrl(url!!)
                    } else {
                        resultTextView.text = buildString {
                            append("STAGE$stage: ")
                            append(false.toResult())
                        }
                    }
                }

                "stage2" -> {
                    stage = 2
                    val url = deeplink.getQueryParameter("url")
                    if (isValidHost2(url.toString())) {
                        webView.loadUrl(url!!)
                    } else {
                        resultTextView.text = buildString {
                            append("STAGE$stage: ")
                            append(false.toResult())
                        }
                    }
                }
            }
        }
    }
    
    private fun isValidHost(url: String): Boolean {
        if (url.isEmpty()) return false
        val host = url.toUri().host?.toLowerCase(Locale.ROOT) ?: return false

        return host.endsWith("wizley.com") || host.endsWith(".wizley.com")
    }

    private fun isValidHost2(url: String): Boolean {
        if (url.isEmpty()) return false
        val host = url.toUri().host?.toLowerCase(Locale.ROOT) ?: return false

        return host == "wizley.com" || host.endsWith(".wizley.com")
    }
}
