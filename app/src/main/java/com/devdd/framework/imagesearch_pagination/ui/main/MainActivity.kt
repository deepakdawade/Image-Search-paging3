package com.devdd.framework.imagesearch_pagination.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.ActivityMainBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingWithLifecycleOwner(R.layout.activity_main)
    }
}