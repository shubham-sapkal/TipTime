package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{
            calculateTip()
        }

    }

    private fun calculateTip(){

        val stringInText = binding.costOfService.text.toString()

        val cost = stringInText.toDoubleOrNull()

        if(cost == null){
            Toast.makeText(this, "Please Enter Cost .... ", Toast.LENGTH_LONG).show()
            return
        }

//        val selectedId = binding.serviceRadioGroup.checkedRadioButtonId

        val tipPercentage = when(binding.serviceRadioGroup.checkedRadioButtonId){
            R.id.option_20_percent -> 0.20
            R.id.option_18_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        val isRoundUp = binding.roundUpSwitch.isChecked

        if(isRoundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipTextView.text = getString(R.string.tip_amount, formattedTip)


    }

}