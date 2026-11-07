package com.sabakjedddah.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ربط زر زيارة الموقع الإلكتروني بـ sabakjedddah.com
        val btnWebsite = findViewById<Button>(R.id.btnWebsite)
        btnWebsite.setOnClickListener {
            val url = "https://www.sabakjedddah.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
