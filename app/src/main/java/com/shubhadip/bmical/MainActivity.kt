package com.shubhadip.bmical

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.shubhadip.bmical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private var gender : String = "male"
    private var height : Int = 183
    private var weight : Int = 74
    private var age : Int = 24


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserGender()
        getUserHeight()
        getUserWeight()
        getUserAge()
        onBtnClicked()


    }

    private fun onBtnClicked() {
       binding.mainActivitytBtnCalculateBmi.setOnClickListener {
           Log.d("myLog", "onBtnClicked: $gender $height $weight $age")

           showBmiResult()
       }
    }

    private fun showBmiResult() {
    // show a dialog here

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_result_dialog)

        var imgClose : ImageView= dialog.findViewById(R.id.dialogImgClose)
        var bmiValue: TextView = dialog.findViewById(R.id.dialogTvBmiValue)

        imgClose.setOnClickListener {
            dialog.dismiss()
        }

        bmiValue.text = String.format("%.1f", calculateBmi())

        dialog.show()

    }

    private  fun calculateBmi(): Double{
        val bmi = (weight/(height*height).toDouble())*10000
        return bmi
    }



    //    height function

    private fun getUserHeight() {
        binding.mainActivitySeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
              binding.mainActivityTvHeight.text = progress.toString()
                height = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    //    age function

    private fun getUserAge() {
        binding.mainActivityTvAddAge.setOnClickListener {
            age++
            binding.mainActivityTvAge.text = age.toString()
        }

        binding.mainActivityTvDecAge.setOnClickListener {
            age--
            binding.mainActivityTvAge.text = age.toString()
        }
    }

     //    weight function

    private fun getUserWeight() {
        binding.mainActivityTvAddWeight.setOnClickListener {
            weight++
            binding.mainActivityTvWeight.text = weight.toString()
        }

        binding.mainActivityTvDecWeight.setOnClickListener {
            weight--
            binding.mainActivityTvWeight.text = weight.toString()
        }

    }

    //    gender function

    private fun getUserGender() {
       binding.mainActivityLayoutMale.setOnClickListener {
           binding.mainActivityLayoutFeMale.setBackgroundResource(R.drawable.rectangle_black_7dp)
           binding.mainActivityLayoutMale.setBackgroundResource(R.drawable.rectangle_purple_outline)
           gender = "male"
       }

        binding.mainActivityLayoutFeMale.setOnClickListener {

            binding.mainActivityLayoutFeMale.setBackgroundResource(R.drawable.rectangle_purple_outline)
            binding.mainActivityLayoutMale.setBackgroundResource(R.drawable. rectangle_black_7dp)
            gender = "female"

        }
    }
}