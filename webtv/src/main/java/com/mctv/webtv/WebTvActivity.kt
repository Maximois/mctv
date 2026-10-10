package com.mctv.webtv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class WebTvActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, WebTvFragment())
                .commit()
        }
    }
}
