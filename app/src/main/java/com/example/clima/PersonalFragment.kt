package com.example.clima

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clima.databinding.FragmentPersonalBinding
import com.example.clima.model.User
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment(), View.OnClickListener, UserAdapter.onListInteraction  {

    lateinit var user: User
    lateinit var nBinding: FragmentPersonalBinding

    lateinit var viewModel: RandomUserViewModel
    private var daysList = mutableListOf<RandomUser>()
    val days = mutableListOf<User>()
    private  var adapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_personal, container, false)
        nBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal, container, false);


        return nBinding.root
    }


    fun getDaysMethod(daysParam: String?){
        viewModel.getDays().observe(viewLifecycleOwner, Observer{ days ->
            run{
                daysList = days as MutableList<RandomUser>
                Log.d("VideoVolleyLifeDataDays", " userList size " + daysList.size)
                nBinding.user=daysList[0];
                nBinding.user2=daysList[1];
                nBinding.user3=daysList[2];
                nBinding.user4=daysList[3];
                nBinding.user5=daysList[4];
                adapter!!.updateData()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //view.findViewById<TextView>(R.id.textViewHobby).text = user.hobby
        //view.findViewById<TextView>(R.id.textViewNombre).text = arguments!!.getString("nombre")!!
        this.user = arguments!!.getParcelable("data")!!
        nBinding.nameCity = "Weather " + user.name
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.addDays(user.name)
        if(days.isEmpty()){
            getDaysMethod(user.name)
        }
        adapter = UserAdapter(days,this)


        //view.list.layoutManager = LinearLayoutManager(context)
        //view.list.adapter = adapter
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListItemInteraction(item: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListButtonInteraction(item: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}