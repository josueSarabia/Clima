package com.example.clima

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.clima.model.User
import com.example.clima.databinding.RowBinding
import kotlinx.android.synthetic.main.row.view.*

class UserAdapter(
    private val mValues: List<User>,
    private val mListener: onListInteraction
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.ViewHolder {
        //val view  = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        //return ViewHolder(view)
        var binder: RowBinding
        binder = DataBindingUtil.inflate(LayoutInflater.from((parent.context)), R.layout.row, parent, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mView.user = item
        holder.mView.executePendingBindings()
        //holder.textView.textViewUserName.text = item.nombre

        holder.mView.theLayout.setOnClickListener{
            mListener?.onListItemInteraction(item)
        }

        //holder.mView.setOnClickListener{
        //mListener?.onListItemInteraction(item)
        //}

        //holder.mView.buttonDeleteUser.setOnClickListener{
        //  mListener?.onListButtonInteraction(item)
        //}
    }

    public fun updateData(){
        notifyDataSetChanged()
    }

    inner  class ViewHolder(val mView: RowBinding) : RecyclerView.ViewHolder(mView.root){
        //val button: Button = mView.buttonDeleteUser
        //val textView: View = mView.textViewUserName
    }

    public interface onListInteraction{
        fun onListItemInteraction(item: User?)
        fun onListButtonInteraction(item: User?)
    }
}