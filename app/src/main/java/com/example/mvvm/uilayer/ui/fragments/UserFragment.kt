package com.example.mvvm.uilayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.R
import com.example.mvvm.uilayer.viewmodel.UserViewModel
import com.example.utils.StringFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/* Use Fragment(R.layout.fragment_user) constructor: Instead of overriding onCreateView to inflate the layout,
use the Fragment(R.layout.fragment_user) constructor to directly set the layout resource ID.
 */
@AndroidEntryPoint
class UserFragment :  Fragment(R.layout.fragment_user) {

    private lateinit var userId: TextView
    private lateinit var userIdField: EditText
    private lateinit var getUserByIdButton: Button
    private lateinit var getAllUserButton: Button

    companion object {
        fun newInstance() = UserFragment()
    }

    //private lateinit var userViewModel: UserViewModel
    private val userViewModel by viewModels<UserViewModel>()

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
        setupViews(view)
        observeViewModel()
        setupButtons()
    }

    private fun setupViews(view: View) {
        userId = view.findViewById(R.id.userIdTv)
        userIdField = view.findViewById(R.id.userIdField)
        getUserByIdButton = view.findViewById(R.id.getUserByIdButton)
        getAllUserButton = view.findViewById(R.id.getAllUserButton)
    }

    private fun observeViewModel() {
        userViewModel.userEntity.observe(viewLifecycleOwner) { userEntity ->
            userEntity?.let {
                userId.text = "${it.id}: ${it.name}"
            }
        }

        userViewModel.userEntityList.observe(viewLifecycleOwner) { userEntityList ->
            userId.text = StringFormatter.formatUserList(userEntityList)
        }
    }

    private fun setupButtons() {
        getUserByIdButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val userIdValue = userIdField.text.toString().toIntOrNull()
                if (userIdValue != null) {
                    userViewModel.loadUserById(userIdValue)
                } else {
                    Toast.makeText(requireContext(), "Invalid User ID", Toast.LENGTH_SHORT).show()
                }
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