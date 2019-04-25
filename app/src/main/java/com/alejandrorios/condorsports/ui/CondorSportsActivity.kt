package com.alejandrorios.condorsports.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.ui.mainActivity.MainActivity

class CondorSportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@CondorSportsActivity, MainActivity::class.java))
        finish()
    }
}