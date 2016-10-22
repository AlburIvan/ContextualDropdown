package com.raworkstudio.contexdropdown

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.feature_dropdown_list_item.view.*

/**
 * Created by Ivan Alburquerque on 10/21/2016
 */
class SimpleOptionListAdapter(context: Context, textViewResourceId: Int = 0, optionsListItem: List<Option>?) :
        ArrayAdapter<Option>(context, textViewResourceId) {

    private val TAG: String = this.javaClass.simpleName

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if(view == null) {
            view = parent!!.inflate( R.layout.feature_dropdown_layout)
        }

        val option = getItem(position)

        if (option != null) {
            parent?.dropdown_list_item_text?.text = option.title
        }

        return view
    }

}