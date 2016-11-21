package com.raworkstudio.contextdropdown

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.raworkstudio.contextualdropdown.Option
import com.raworkstudio.contextualdropdown.R
import com.raworkstudio.contextualdropdown.SimpleOptionListAdapter
import com.raworkstudio.contextualdropdown.onItemSelectedCallback
import kotlinx.android.synthetic.main.activity_main.*



import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val options: MutableList<Option> = ArrayList()
        options.add(
                Option(title = "Economy Class", subTitle = "Super Saver", drawableRes = R.drawable.ic_economic_class)
        )
        options.add(
                Option(title = "Business Class", subTitle = "High Quality", drawableRes = R.drawable.ic_business_class)
        )
        options.add(
                Option(title = "First Class", subTitle = "Most Expensive", drawableRes = R.drawable.ic_premium_class)
        )


//        val opDropdown = OptionDropDown(context = baseContext, attrs = null, defStyleAttr = 0)
//        val opDropdown = findViewById(R.id.optiondropdown) as OptionDropDown

        optiondropdown.setOnItemSelectedCallback(object : onItemSelectedCallback {
            override fun onItemSelected(selected: Option) {
                Toast.makeText(
                        applicationContext, // context
                        "Option ${selected.title} selected", // text
                        Toast.LENGTH_LONG //duration
                ).show()
            }
        })

        optiondropdown.setOptionsAdapter(
                SimpleOptionListAdapter(this, 0, options)
        )

//        val layoutParams = RelativeLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
//        addContentView(opDropdown, layoutParams)

//        addContentView()
//        var optionDropdown = OptionDropDown(applicationContext, null)
//        optionDropdown.setOptionAdapter()
    }






}
