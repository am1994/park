package com.devmina.droid_ant.carpark.occupancyLevel

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.devmina.droid_ant.carpark.databinding.FragmentActivityBinding

class OccupancyLevel :Fragment(){
    lateinit var  application: Application


    private val viewModel: OccupancyLevelViewModel by lazy {
        ViewModelProviders.of(this).get(OccupancyLevelViewModel::class.java)
    }
    val badgeValue = 6
   // val totalValue = 180

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentActivityBinding.inflate(inflater)
        application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        //bind
        binding.viewmodel=viewModel

        viewModel.setBadgeValue(badgeValue.toString())
        viewModel.setTotalValue(data.totalSpaces.toString())




        return binding.root

    }
}
object data{
    var totalSpaces = 570f
    var amountSpaces = 50f

}