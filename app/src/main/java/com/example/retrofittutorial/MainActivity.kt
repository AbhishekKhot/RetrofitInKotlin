package com.example.retrofittutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittutorial.databinding.ActivityMainBinding
import com.example.retrofittutorial.databinding.RvItemsBinding
import retrofit2.HttpException
import java.io.IOException
import java.util.logging.Logger

const val TAG="MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rVadapter: RVadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible=true
            val response=try {
                RetrofitInstance.api.getUserData()
            }catch (e:IOException){
                Log.e(TAG,"IOException you might not have internet connection")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException unexpected response")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() !=null){
                rVadapter.ApiResponseList=response.body()!!
            }
            else{
                Log.e(TAG,"response is not successful")
            }
            binding.progressBar.isVisible=false
        }
    }


    private fun setRecyclerView() = binding.MainRecyclerView.apply {
        rVadapter= RVadapter()
        adapter=rVadapter
        layoutManager=LinearLayoutManager(this@MainActivity)
    }
}