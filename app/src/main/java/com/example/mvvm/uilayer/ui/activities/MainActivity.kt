package com.example.mvvm.uilayer.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.mvvm.R
import com.example.mvvm.uilayer.ui.fragments.UserFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        if (savedInstanceState == null) {
            showUserFragment()
        }
    }

    private fun showUserFragment() {
        supportFragmentManager.commit {
            replace(R.id.container, UserFragment.newInstance())
        }
    }
}