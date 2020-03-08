package com.example.clima

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RandomUserViewModel (applicaton: Application): AndroidViewModel(applicaton){
    private var randomUserDao : RandomUserDao

    init{
        randomUserDao = RandomUserDao.getInstance(this.getApplication())
    }

    fun addUser(){
        randomUserDao.addUser()
    }

    fun getUsers() : MutableLiveData<List<RandomUser>>{
        return randomUserDao.getUsers()
    }
}