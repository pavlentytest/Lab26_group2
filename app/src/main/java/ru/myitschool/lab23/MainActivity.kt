package ru.myitschool.lab23

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myitschool.lab23.databinding.ActivityMainBinding
import java.util.Random

class MyViewModel : ViewModel() {
    val random_value = MutableLiveData<Double>()
    fun setRandomValue(d: Double) {
        random_value.value = d
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        binding.content.vm = myViewModel
        binding.content.lifecycleOwner = this

        binding.content.getRandomNum.setOnClickListener {
            val m = binding.content.meanVal.text.toString().toDouble()
            val v = binding.content.varianceValue.text.toString().toDouble()
            val calc = Math.exp(Math.sqrt(v) * Random().nextGaussian() + m)
            myViewModel.setRandomValue(calc)
        }

       // myViewModel.random_value.observe(this) {
       //     binding.content.randomNumberResult.text = it.toString()
       // }
    }
}
