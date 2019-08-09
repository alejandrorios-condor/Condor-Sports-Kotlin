package com.alejandrorios.condorsports.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Alejandro Rios on 2019-07-22
 */
open class BaseActivity : DaggerAppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
