package fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import model.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import self.tutorial.roomkoltinfloating.R
import self.tutorial.roomkoltinfloating.databinding.FragmentAddBinding
import viewModel.UserViewModel


class AddFragment : Fragment() {

   private lateinit var myUserViewModel: UserViewModel
   lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val  view =  inflater.inflate(R.layout.fragment_add, container, false)
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = editTextTextFirstName.text.toString()
        val lastName = editTextTextLastName.text.toString()
        val age = editTextAge.text

        if(inputCheck(firstName,lastName,age)){
            // create User obj
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            // Add data to db
            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successful added new data to DB.", Toast.LENGTH_LONG).show()
            // Navi Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{

            Toast.makeText(requireContext(),"Fill out all fields, Please! ", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName:String, lastName:String, age:Editable) :Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}