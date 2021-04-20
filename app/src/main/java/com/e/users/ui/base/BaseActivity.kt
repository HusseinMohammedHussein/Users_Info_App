package com.e.users.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.users.databinding.ToolbarHomeBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ToolbarHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ToolbarHomeBinding.inflate(layoutInflater)
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}