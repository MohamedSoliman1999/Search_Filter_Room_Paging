package com.example.search_filteramproom_paging.UI.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.search_filteramproom_paging.UI.AllUserActivity.AllUsersActivity
import com.example.search_filteramproom_paging.databinding.ActivityMainBinding
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserUtil
import com.example.search_filteramproom_paging.utility.DataStorePreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding?=null
    val binding get() = _binding!!
    lateinit var dataStorePreferences: DataStorePreferences
    private val viewModel:MainActivityViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataStorePreferences=DataStorePreferences(context = this)
        saveBtn.setOnClickListener {
            val error=UserUtil.checkEmptyOrNull(
                firstNameET.text.toString(),
                lastNameET.text.toString(),
                ageET.text.toString())
            if(error.isEmpty()){
                viewModel.insertUser(User(
                    firstNameET.text.toString(),
                    lastNameET.text.toString(),
                    ageET.text.toString().toInt()))
            }else{
                if(firstNameET.text.isNullOrEmpty()){
                    firstNameET.error=error

                }else if (lastNameET.text.isNullOrEmpty()){
                    lastNameET.error=error
                }else if (ageET.text.isNullOrEmpty()){
                    ageET.error=error
                }
                Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            }

           /* CoroutineScope(Dispatchers.IO).launch{
                dataStorePreferences.saveString("key",wordET.text.toString())
            }*/
        }
        showPeopleBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,AllUsersActivity::class.java))
        }
        clearBtn.setOnClickListener {
            viewModel.removeAll()
            Toast.makeText(this@MainActivity,"Cleared Successfully",Toast.LENGTH_SHORT).show()
        }
        /*
        CoroutineScope(Dispatchers.Main).launch{
            dataStorePreferences.getString("key").collect {
                dataStoreValueTV.text = it
            }
        }*/
        /*val fw=flow{
            emit(1)
        }
        CoroutineScope(Dispatchers.Main).launch{
            fw.collect{
                Log.e("TEST",it.toString())
            }
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
        this.clearFindViewByIdCache()
    }
}