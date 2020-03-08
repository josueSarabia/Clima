package com.example.clima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.clima.databinding.FragmentPersonalBinding
import com.example.clima.model.User

/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment() {

    lateinit var user: User
    lateinit var nBinding: FragmentPersonalBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_personal, container, false)
        nBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal, container, false);
        return nBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //view.findViewById<TextView>(R.id.textViewHobby).text = user.hobby
        //view.findViewById<TextView>(R.id.textViewNombre).text = arguments!!.getString("nombre")!!
        this.user = arguments!!.getParcelable("data")!!
        nBinding.user = this.user
    }
}