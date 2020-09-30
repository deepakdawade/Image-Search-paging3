package com.devdd.framework.imagesearch_pagination.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.ActivityMainBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingWithLifecycleOwner(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}