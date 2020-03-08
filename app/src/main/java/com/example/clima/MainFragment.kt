package com.example.clima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clima.model.User
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), View.OnClickListener, UserAdapter.onListInteraction {


    lateinit var navController: NavController
    lateinit var user: User
    lateinit var user2: User
    lateinit var user3: User
    lateinit var viewModel: RandomUserViewModel
    private var userList = mutableListOf<RandomUser>()

    val users = mutableListOf<User>()
    private  var adapter: UserAdapter? = null
    var count : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("errorrrrrrrrrrrrrrr", "0")
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        Log.d("errorrrrrrrrrrrrrrr", "1")

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        Log.d("errorrrrrrrrrrrrrrr", "2")
        viewModel.addUser()
        Log.d("errorrrrrrrrrrrrrrr", "3")
        adapter = UserAdapter(users,this)
        Log.d("errorrrrrrrrrrrrrrr", "4")
        viewModel.getUsers().observe(viewLifecycleOwner, Observer{ users ->
            run{
                Log.d("errorrrrrrrrrrrrrrr", "5")
                userList = users as MutableList<RandomUser>
                Log.d("error", "6")
                Log.d("VideoVolleyLifeData", " userList size " + userList.size)
                for( ruser in userList){
                    var user = User(ruser.name?.title, ruser.name?.first, ruser.name?.last,ruser.gender )
                    this.users.add(user)
                }

                adapter!!.updateData()
            }
        })
        Log.d("errorrrrrrrrrrrrrrr", "7")
        view.list.layoutManager = LinearLayoutManager(context)
        view.list.adapter = adapter

        Log.d("errorrrrrrrrrrrrrrr", "return")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        /*user = User("Juanita Perez",20,"Estudiante", 2)
        user2 = User("Fernando Jimeno",20,"Profesor", 5)
        user3 = User("Laura Viloria",20,"Decana", 1)
        view.findViewById<Button>(R.id.button_personal).setOnClickListener(this)
        view.findViewById<Button>(R.id.button_personal2).setOnClickListener(this)
        view.findViewById<Button>(R.id.button_personal3).setOnClickListener(this)*/
    }

    override fun onListItemInteraction(item: User?) {
        Log.d("KRecyclerView", "onListItemInteraction " + item!!.first)
        val bundle = bundleOf("data" to item)
        navController!!.navigate(R.id.action_mainFragment_to_personalFragment,bundle)
    }

    override fun onListButtonInteraction(item: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        // when(v!!.id){

        //   R.id.button_personal -> {
        //     val bundle = bundleOf("data" to user)
        //navController!!.navigate(R.id.action_mainFragment_to_personalFragment,bundle)
        //}

        //}
    }



}