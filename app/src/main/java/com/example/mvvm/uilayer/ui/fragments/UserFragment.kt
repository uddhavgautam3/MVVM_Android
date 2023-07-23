package com.example.mvvm.uilayer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentUserBinding
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

    // Using ViewBinding to access views
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = UserFragment()
    }

    //private lateinit var userViewModel: UserViewModel
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupButtons()
    }

    private fun observeViewModel() {
        userViewModel.userEntity.observe(viewLifecycleOwner) { userEntity ->
            userEntity?.let {
                binding.userIdTv.text = "${it.id}: ${it.name}"
            }
        }

        userViewModel.userEntityList.observe(viewLifecycleOwner) { userEntityList ->
            binding.userIdTv.text = StringFormatter.formatUserList(userEntityList)
        }
    }

    private fun setupButtons() {
        binding.getUserByIdButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val userIdValue = binding.userIdField.text.toString().toIntOrNull()
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

        binding.getAllUserButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userViewModel.loadAllUsers()
                withContext(Dispatchers.Main) {
                    //UI operations
                }
            }
        }
    }

}