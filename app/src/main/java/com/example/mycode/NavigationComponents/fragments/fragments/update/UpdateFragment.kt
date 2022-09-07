package com.example.mycode.NavigationComponents.fragments.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mycode.R
import com.example.mycode.Room_Databse.Database_viewmodel.UserViewModel
import com.example.mycode.Room_Databse.model.UserData
import com.example.mycode.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    //set binding
    lateinit var binding: FragmentUpdateBinding
    //args for update ui
    private val args by navArgs<UpdateFragmentArgs>()
    //viewModel
    lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_update,
            container,
            false
        )

       //menu
        setHasOptionsMenu(true)

        //get all the data from list
        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateUserAge.setText(args.currentUser.age.toString())

        //userModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //update user data
        binding.updateUserBtn.setOnClickListener {

            if (checkValidation()) {
                //place data in model
                val userData = UserData(
                    args.currentUser.id,
                    binding.updateFirstName.text.toString(),
                    binding.updateLastName.text.toString(),
                    binding.updateUserAge.text.toString().toInt()
                )
                //update from database
                mUserViewModel.updateUser(userData)

                //navigate to list fragment
                findNavController().navigate(R.id.action_updateFragment_to_list_fragment)
                Toast.makeText(requireContext(), "Update Successfully.!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Something went wrong.!", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        return binding.root
    }


    //menu icon implementation
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_cicon) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    //delete single users
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "User Data deleted successfully.!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_updateFragment_to_list_fragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

    //validation for all fields
    private fun checkValidation(): Boolean {
        return !(TextUtils.isEmpty(binding.updateFirstName.text.toString()) && TextUtils.isEmpty(
            binding.updateLastName.text.toString()
        ) && TextUtils.isEmpty(
            binding.updateUserAge.text.toString()
        ))
    }
}