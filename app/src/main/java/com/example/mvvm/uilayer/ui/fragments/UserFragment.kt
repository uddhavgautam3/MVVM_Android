package com.example.mvvm.uilayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.R
import com.example.mvvm.datalayer.model.UserEntity
import com.example.mvvm.uilayer.viewmodel.UserViewModel
import com.example.utils.StringFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var userId: TextView
    private lateinit var userIdField: EditText
    private lateinit var getUserByIdButton: Button
    private lateinit var getAllUserButton: Button

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
        userViewModel.userEntity.observe(viewLifecycleOwner) { userEntity: UserEntity? ->
            userId.text = buildString {
                if (userEntity != null) {
                    append(userEntity.id.toString())
                }
                append(": ")
                if (userEntity != null) {
                    append(userEntity.name)
                }
            }
        }

        userViewModel.userEntityList.observe(viewLifecycleOwner) { userEntityList: List<UserEntity?>? ->
            userId.text = StringFormatter.formatUserList(userEntityList)
        }

        getUserByIdButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userViewModel.loadUserById(Integer.parseInt(userIdField.text.toString()))
                withContext(Dispatchers.Main) {
                    //UI operations
                }
            }
        }

        getAllUserButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userViewModel.loadAllUsers()
                withContext(Dispatchers.Main) {
                    //UI operations
                }
            }
        }
    }
}