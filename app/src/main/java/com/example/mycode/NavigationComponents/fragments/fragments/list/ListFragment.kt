package com.example.mycode.NavigationComponents.fragments.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycode.NavigationComponents.fragments.adapter.ListAdapter
import com.example.mycode.R
import com.example.mycode.Room_Databse.Database_viewmodel.UserViewModel
import com.example.mycode.databinding.FragmentListFragmentBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListFragmentBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_list_fragment,
            container,
            false
        )

        //menu
        setHasOptionsMenu(true)

        //recyclerview
        val adapter = ListAdapter()
        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //usermodel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        //floating action button
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_list_fragment_to_add_fragment)
        }
        return binding.root
    }

    //menu implementation
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_cicon) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    //delete alert
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "User Data deleted successfully.!", Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure want to delete all users")
        builder.create().show()
    }


}