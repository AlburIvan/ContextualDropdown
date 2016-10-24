package com.raworkstudio.contextdropdown

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        val options: MutableList<Option> = ArrayList()
        options.add(
                Option(title = "Economy Class", subTitle =  "Super Saver", drawableRes =  R.drawable.ic_economic_class)
        )
        options.add(
                Option(title = "Business Class", subTitle = "High Quality", drawableRes =  R.drawable.ic_business_class)
        )
        options.add(
                Option(title = "First Class", subTitle = "Most Expensive", drawableRes =  R.drawable.ic_economic_class)
        )


        var opDropdown = findViewById(R.id.optiondropdown) as OptionDropDown

        opDropdown.setOptionAdapter(
                SimpleOptionListAdapter(this, 0, options)
        )
//
//        var optionDropdown = OptionDropDown(applicationContext, null)
//        optionDropdown.setOptionAdapter()
    }






}
