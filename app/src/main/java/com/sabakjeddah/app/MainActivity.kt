package com.sabakjeddah.app
import android.content.Intent
import android.os.Bundle
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    private val home = "https://www.sabakjedddah.com/"
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        setContentView(R.layout.activity_main)
        val web = findViewById<WebView>(R.id.web)
        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipe)
        web.settings.apply { javaScriptEnabled = true; domStorageEnabled = true; cacheMode = WebSettings.LOAD_DEFAULT }
        swipe.setOnRefreshListener { web.reload() }
        swipe.setOnChildScrollUpCallback { _, _ -> web.scrollY > 0 }
        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(v: WebView, r: WebResourceRequest): Boolean {
                val u = r.url
                if ((u.scheme == "http" || u.scheme == "https") && u.host?.endsWith("sabakjedddah.com") == true) return false
                try { startActivity(Intent(Intent.ACTION_VIEW, u)) } catch (e: Exception) {}
                return true
            }
            override fun onPageFinished(v: WebView?, url: String?) { swipe.isRefreshing = false }
            override fun onReceivedError(v: WebView, r: WebResourceRequest, e: WebResourceError) {
                if (r.isForMainFrame) { swipe.isRefreshing = false
                    v.loadData("<body dir='rtl' style='font-family:sans-serif;text-align:center;padding:60px 20px'><h2>لا يوجد اتصال بالإنترنت</h2><p>اسحب للأسفل لإعادة المحاولة</p><p><a href='tel:+966579054861'>اتصل بنا: 0579054861</a></p></body>", "text/html; charset=utf-8", "UTF-8") }
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { if (web.canGoBack()) web.goBack() else finish() }
        })
        if (b == null) web.loadUrl(home) else web.restoreState(b)
    }
    override fun onSaveInstanceState(o: Bundle) { super.onSaveInstanceState(o); findViewById<WebView>(R.id.web).saveState(o) }
}
