package com.example.mvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvm.R
import com.example.mvvm.model.User
import com.example.mvvm.utils.StringFormatter
import com.example.mvvm.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var userId: TextView
    private lateinit var userIdField: EditText
    private lateinit var getUserByIdButton: Button
    private lateinit var getAllUserButton: Button


    //inside Fragment
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    companion object {
        fun newInstance() = UserFragment()
    }

    //private lateinit var userViewModel: UserViewModel
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout: View = inflater.inflate(R.layout.fragment_user, container, false)
        userId = layout.findViewById<View>(R.id.userIdTv) as TextView
        getUserByIdButton = layout.findViewById<View>(R.id.getUserByIdButton) as Button
        getAllUserButton = layout.findViewById<View>(R.id.getAllUserButton) as Button
        userIdField = layout.findViewById<View>(R.id.userIdField) as EditText
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observer(lifeCycleOwner, observer)
        userViewModel.user.observe(viewLifecycleOwner) { user: User? ->
            userId.text = buildString {
                if (user != null) {
                    append(user.id.toString())
                }
                append(": ")
                if (user != null) {
                    append(user.name)
                }
            }
        }

        userViewModel.userList.observe(viewLifecycleOwner) { userList: List<User?>? ->
            userId.text = StringFormatter.formatUserList(userList)
        }

        getUserByIdButton.setOnClickListener {
            uiScope.launch(Dispatchers.IO) {
                //asyncOperation
                withContext(Dispatchers.Main) {
                    //ui operation
                    userViewModel.loadUserById(Integer.parseInt(userIdField.text.toString()))
                }

            }
        }

        getAllUserButton.setOnClickListener {
            uiScope.launch(Dispatchers.IO) {
                //asyncOperation
                withContext(Dispatchers.Main) {
                    //ui operation
                    userViewModel.getAllUsers()
                }

            }
        }

    }

}