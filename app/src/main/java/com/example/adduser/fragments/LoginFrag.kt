package com.example.adduser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.adduser.R
import com.example.adduser.databinding.FragmentLoginBinding


class LoginFrag : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userList = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addingUser()
        removingUser()
        updateingUser()
    }

    private fun addingUser(){
        binding.btnAddUser.setOnClickListener {
            val firstName = binding.edFirstname.text.toString()
            val lastName = binding.edLastname.text.toString()
            val age = binding.edAge.text.toString()
            val email =binding.edEmail.text.toString()

            val user = User(firstName,lastName,age,email)
            if (!userList.contains(user)){
                addUser(user)
                Toast.makeText(activity,"success",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity,"There already is user like that",Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun removingUser(){
        binding.btnRemoveUser.setOnClickListener {
            val firstName = binding.edFirstname.text.toString()
            val lastName = binding.edLastname.text.toString()
            val age = binding.edAge.text.toString()
            val email =binding.edEmail.text.toString()

            val user  = User(firstName,lastName,age,email)
            if (userList.contains(user)){
                removeUser(user)
                Toast.makeText(activity,"success",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity,"There is no user like that",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun updateingUser(){
        binding.btnUpdateUser.setOnClickListener {
            val firstName = binding.edFirstname.text.toString()
            val lastName = binding.edLastname.text.toString()
            val age = binding.edAge.text.toString()
            val email =binding.edEmail.text.toString()

            val updatedUser = User(firstName, lastName, age, email)
            val existingUserIndex = userList.indexOfFirst { it.firstName == firstName && it.lastName == lastName }
            if (existingUserIndex != -1){
                userList[existingUserIndex] = updatedUser
                Toast.makeText(activity,"success",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity,"error",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun addUser(user: User) {
        userList.add(user)
    }
    private fun removeUser(user: User) {
        userList.remove(user)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

data class User(val firstName: String, val lastName: String, val age: String, val email: String)