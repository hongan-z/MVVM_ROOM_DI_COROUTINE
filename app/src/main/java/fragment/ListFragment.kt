package fragment

import adapter.ListAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.view.*
import model.User
import self.tutorial.roomkoltinfloating.R
import self.tutorial.roomkoltinfloating.databinding.FragmentListBinding
import viewModel.UserViewModel

//@AndroidEntryPoint
class ListFragment : Fragment() {


    lateinit var binding: FragmentListBinding

    private lateinit var myUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // val view = inflater.inflate(R.layout.fragment_list, container, false)
        binding = FragmentListBinding.inflate(layoutInflater,container,false)

        // ViewModel
        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


       myUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
           setAdapter(user)
       })


        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // add menu
        setHasOptionsMenu(true)


        return binding.root
    }

    private fun setAdapter(user: List<User>?) = binding.recyclerview.apply {

        val myadapter = ListAdapter(user!!)
        layoutManager  = LinearLayoutManager(requireContext())
        adapter = myadapter
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dele_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->
            myUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(),"Successfully remove all ", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){ _,_-> }
        builder.setTitle("Delete All Of User")
        builder.setMessage("Are you sure you want to delete all of Users")
        builder.create().show()
    }
}



