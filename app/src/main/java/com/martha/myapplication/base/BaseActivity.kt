package com.martha.myapplication.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.martha.myapplication.network.RemoteDataSource
import com.martha.myapplication.util.DataPreferences

abstract class BaseActivity<vm: ViewModel, vb: ViewBinding>: AppCompatActivity() {

    protected lateinit var bind: vb
    protected lateinit var viewModel: vm
    protected val remoteDataSource = RemoteDataSource(this)
    protected lateinit var prefs: DataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = DataPreferences(this)
        bind = getActivityBinding(layoutInflater)
        val factory =
            ViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        setContentView(bind.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        backToPreviousPage()
    }

    abstract fun getViewModel(): Class<vm>
    abstract fun getActivityBinding(layoutInflater: LayoutInflater): vb

    fun goToActivity(c: Class<*>, bundle: Bundle?, isFinish: Boolean) {
        val i = Intent(this, c)
        if (bundle != null) {
            i.putExtras(bundle)
        }
        startActivity(i)
        if (isFinish) {
            finish()
        }
    }

    fun backToPreviousPage() {
        finish()
    }
}