package com.rizlee.justforfun.features.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizlee.justforfun.R
import com.rizlee.justforfun.di.Injector
import com.rizlee.justforfun.features.base.BaseActivity
import com.rizlee.justforfun.features.utils.lifecycle.EventObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        internal fun newActivityIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectComponent() = Injector.plusMainComponent().inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        initViews()

        viewModel.run {
            scrollChatEvent.observe(this@MainActivity, EventObserver {
                recyclerChat.smoothScrollToPosition(it)
            }
            )
        }
    }

    private fun initViews() {
        editTextChat.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    viewModel.sendMessage(editTextChat.text.toString())
                    editTextChat.setText("")
                    return true
                }
                return false
            }
        })
    }

    private fun initRecycler() {
        recyclerChat.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewModel.adapter
        }
    }
}