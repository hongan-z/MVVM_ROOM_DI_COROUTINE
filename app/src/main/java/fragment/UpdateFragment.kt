package fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import model.User
import self.tutorial.roomkoltinfloating.R
import self.tutorial.roomkoltinfloating.databinding.FragmentUpdateBinding
import viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel
    lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_update, container, false)
       binding = FragmentUpdateBinding.inflate(layoutInflater,container,false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        binding.updataETFirstName.setText(args.currentUsers.firstName)
        binding.updataETLastName.setText(args.currentUsers.lastName)
        binding.updataETAge.setText(args.currentUsers.age.toString())

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }


        // add menu for delete
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val firstName = updata_eT_FirstName.text.toString()
        val lastName = updata_eT_LastName.text.toString()
        val age = Integer.parseInt(updata_eTAge.text.toString())

        if (inputCheck(firstName, lastName, updata_eTAge.text)) {
            // create user object
            val new_updatedUser = User(args.currentUsers.id, firstName, lastName, age)
            // update current user
            userViewModel.updataUser(new_updatedUser)
            Toast.makeText(requireContext(), "Been Updated Successfully!", Toast.LENGTH_SHORT)
                .show()

            // Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "please Fill out field in table!", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    // delete

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dele_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteItemUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItemUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(args.currentUsers)
            Toast.makeText(requireContext(),
                "Successfully remove ${args.currentUsers.firstName}?",
                Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete ${args.currentUsers.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUsers.firstName}? ")
        builder.create().show()
    }

}