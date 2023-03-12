package adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fragment.ListFragmentDirections
import model.User
import kotlinx.android.synthetic.main.custom_roll.view.*
import self.tutorial.roomkoltinfloating.R

class ListAdapter(val userList: List<User>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_roll, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.id_user.text = currentItem.id.toString()
        holder.itemView.tVfirtName.text = currentItem.firstName
        holder.itemView.tVlastName.text = currentItem.lastName
        holder.itemView.tVage.text = currentItem.age.toString()

        holder.itemView.id_user.setOnClickListener {
               val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
               holder.itemView.findNavController().navigate(action)
        }

//        holder.itemView.tVfirtName.setOnClickListener {
//
//            // holder.itemView.context.startActivity(Intent(holder.itemView.context,DetailPhotoActivity::class.java).putExtra("myphoto",photoItem.urls.full))
//
//            val intent=Intent(holder.itemView.context,UpdateActivity::class.java)
////
////            intent.putExtra("first",currentItem.firstName)
////            intent.putExtra("last",currentItem.lastName)
////            intent.putExtra("dats",currentItem.age)
////
////
//           holder.itemView.context.startActivity(intent)
//
//        }
//
//           holder.setIsRecyclable(false)


        }

    override fun getItemCount():Int {

        return userList.size
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(user: List<User>) {
//        this.userList = user
//        notifyDataSetChanged()
//    }


}




/*
override fun getItemCount() = differ.currentList.size

val diffUtils = object : DiffUtil.ItemCallback<PhotoModelItem>(){
    override fun areItemsTheSame(oldItem: PhotoModelItem, newItem: PhotoModelItem): Boolean {
        return oldItem.id === newItem.id
    }
    override fun areContentsTheSame(oldItem: PhotoModelItem, newItem: PhotoModelItem): Boolean {
        return oldItem == newItem
    }
}

val differ = AsyncListDiffer(this,diffUtils)*/
