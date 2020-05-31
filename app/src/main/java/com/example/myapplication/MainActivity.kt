package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.api.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, MainViewModel.ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)

        setup()
    }

    fun setup() {
        viewModel.totalTime.observe(this, Observer {
            timeDef.setText("$it  milliseconds")
            progresbar.visibility = View.GONE
            onClick.isEnabled = true
        })




        onClick.setOnClickListener {
            viewModel.startCaling()
            progresbar.visibility = View.VISIBLE
            onClick.isEnabled = false
        }


    }
}