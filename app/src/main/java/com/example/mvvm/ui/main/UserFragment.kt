package com.example.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import kotlinx.coroutines.*


class UserFragment : Fragment() {

    private lateinit var userId: TextView
    private lateinit var userIdField: EditText
    private lateinit var loadButton: Button

    //inside Fragment
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout: View = inflater.inflate(R.layout.fragment_user, container, false)
        userId = layout.findViewById<View>(R.id.userIdTv) as TextView
        loadButton = layout.findViewById<View>(R.id.loadButton) as Button
        userIdField = layout.findViewById<View>(R.id.userIdField) as EditText
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observer(lifeCycleOwner, observer)
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            userId.text = buildString {
                append(user.id.toString())
                append(": ")
                append(user.name)
            }
        }

        loadButton.setOnClickListener {
            uiScope.launch(Dispatchers.IO) {
                //asyncOperation
                withContext(Dispatchers.Main) {
                    //ui operation
                    userViewModel.loadUser(Integer.parseInt(userIdField.text.toString()))
                }

            }
        }

    }

}