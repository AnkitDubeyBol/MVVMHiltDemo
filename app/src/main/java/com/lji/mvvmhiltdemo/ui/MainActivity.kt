package com.lji.mvvmhiltdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lji.mvvmhiltdemo.R
import com.lji.mvvmhiltdemo.model.Blog
import com.lji.mvvmhiltdemo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObserver()
    }

    private fun subscribeObserver(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> -> {
                    dataState.data.fold(StringBuilder()){initial,blog->
                        initial.append(blog.title)
                    }?.let {
                      text.text = it
                    }
                }
                is DataState.Error -> {
                    Log.e("Uff","error")
                }
                is DataState.Loading -> {
                    text.text = "loading"
                }
            }
        })
    }
}