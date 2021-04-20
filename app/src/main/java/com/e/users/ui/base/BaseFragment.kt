package com.e.users.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.e.users.databinding.ToolbarBinding
import com.e.users.ui.activity.main.MainActivity

/**
 * Created by Hussein on 4/15/2021
 */

abstract class BaseFragment : Fragment() {
    lateinit var binding: ToolbarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ToolbarBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {

    }
}