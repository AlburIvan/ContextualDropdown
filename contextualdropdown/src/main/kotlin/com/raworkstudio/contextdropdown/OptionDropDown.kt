/*
* Copyright 2016 AlburIvan [Ivan Alberto Alburquerque Mejia]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.raworkstudio.contextdropdown

import android.animation.Animator
import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*

import kotlinx.android.synthetic.main.feature_dropdown_layout.view.*

/**
 *  (っ･_･)っ
 *
 *  @author Iván Alburquerque
 *  @version 1.0.0
 */
class OptionDropDown(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr) {

    val TAG: String = this.javaClass.simpleName
    var options: MutableList<Option> = ArrayList()
    var listPopup: ListPopupWindow
    var adapter: SimpleOptionListAdapter? = null







    /**
     *
     */
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    /**
     * Method that initializes this custom view's default values and sets listeners
     */
    init {

        inflate(R.layout.feature_dropdown_layout) as RelativeLayout

        material_dropdown_container.setOnClickListener { it -> onParentClicked(it) }

        listPopup = ListPopupWindow(context)

        listPopup.anchorView = material_dropdown_container
        listPopup.width = ListPopupWindow.MATCH_PARENT
        listPopup.setOnItemClickListener { adapterView, view, i, l ->
            onItemSelected(i)
            listPopup.dismiss()
        }
    }


    private fun onParentClicked(parent: View) : Unit {
        listPopup.show()
    }

    private fun onItemSelected(position: Int) : Unit {

        val option = adapter?.getItem(position)

        material_dropdown_title.animate()
                .setDuration(350)
                .alphaBy(-30f)
                .translationY(100f)
                .setListener(object: Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        material_dropdown_title.text = option?.title

                        material_dropdown_title.animate()
                                .setDuration(150)
                                .alphaBy(10f)
                                .translationY(0f)
                                .start()
                    }

                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationCancel(animation: Animator?) {}
                })
                .start()


        if(option?.icon != null) {
            material_dropdown_icon.animate()
                    .setDuration(250)
                    .alphaBy(-20f)
                    .setListener(object: Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {}

                        override fun onAnimationEnd(animation: Animator?) {

                            material_dropdown_icon.animate()
                                    .setDuration(250)
                                    .alphaBy(20f)
                                    .start()

                            material_dropdown_icon.setImageBitmap(option?.icon)
                        }

                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}
                    })
                    .start()
        }
    }


    fun setOptionAdapter(adapter: SimpleOptionListAdapter) {
        this.listPopup.setAdapter(adapter)
        this.adapter = adapter

        adapter.notifyDataSetChanged()
    }


    /**
     * Computes the widest view in an adapter, best used when you need to wrap_content on a ListView, please be careful
     * and don't use it on an adapter that is extremely numerous in items or it will take a long time.
     *
     * @param context Some context
     * @param adapter The adapter to process
     * @return The pixel width of the widest View
     */
    private fun getWidestView(context: Context, adapter: Adapter): Int {

        var maxWidth = 0
        var view: View? = null
        val fakeParent = FrameLayout(context)

        for (i: Int in intArrayOf(adapter.count)){
            view = adapter.getView(i, view, fakeParent)
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            val width = view.measuredWidth

            if (width > maxWidth) {
                maxWidth = width
            }
        }

        return maxWidth
    }

}