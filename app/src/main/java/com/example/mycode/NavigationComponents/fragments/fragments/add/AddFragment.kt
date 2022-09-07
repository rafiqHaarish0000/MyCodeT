package com.example.mycode.NavigationComponents.fragments.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mycode.R
import com.example.mycode.Room_Databse.Database_viewmodel.UserViewModel
import com.example.mycode.Room_Databse.model.UserData
import com.example.mycode.databinding.FragmentAddFragmentBinding

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddFragmentBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_add_fragment,
            container,
            false
        )
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //add user data
        binding.addUser.setOnClickListener {
            adduserdata()
        }

        return binding.root
    }

    //add and check fields
    private fun adduserdata() {

        if (checkValidation()) {

            val userData = UserData(
                0,
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                binding.userAge.text.toString().toInt()
            )

            userViewModel.addUser(userData)
            Toast.makeText(requireContext(), "Added Successfully.!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_add_fragment_to_list_fragment)
        }else{
            Toast.makeText(requireContext(), "Something went wrong.!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkValidation(): Boolean {
        return !(TextUtils.isEmpty(binding.firstName.text.toString()) && TextUtils.isEmpty(binding.lastName.text.toString()) && TextUtils.isEmpty(
            binding.userAge.text.toString()
        ))
    }

}