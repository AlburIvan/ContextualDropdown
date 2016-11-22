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
package com.raworkstudio.contextualdropdown

import android.animation.Animator
import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*

import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.feature_dropdown_layout.view.*
import kotlin.concurrent.schedule

/**
 *  (っ･_･)っ
 *
 *  @author Iván Alburquerque
 *  @version 1.0.0
 */
@SuppressWarnings("ALL")
class OptionDropDown(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr) {

    /** Debug TAG */
    val TAG: String = this.javaClass.simpleName

    /** */
    var options: MutableList<Option> = ArrayList()

    /** */
    var listPopup: ListPopupWindow? = null

    /** */
    var mOptionAdapter: SimpleOptionListAdapter? = null

    /** */
    var callback: onItemSelectedCallback? = null


//    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int ): this {
//
//    }


//    fun OptionDropDown(context: Context?, attrs: AttributeSet?) {
//        initialize()
//    }

   /* init {

//        val layout = inflate(R.layout.feature_dropdown_layout)  as RelativeLayout

        inflate(R.layout.feature_dropdown_layout)

        material_dropdown_container
                .setOnClickListener { it -> onDropdownClicked(it) }

        listPopup = ListPopupWindow(context)

        listPopup?.anchorView = material_dropdown_container
        listPopup?.width = ListPopupWindow.MATCH_PARENT
        listPopup?.setOnItemClickListener { adapterView, view, i, l ->
            onItemSelected(i)
            listPopup?.dismiss()
        }

        Log.d(TAG,"${this.javaClass.simpleName} initialized")
    }*/



    /**
     * Method that initializes this custom view's default values and sets listeners
     */
   /* private fun initialize() {

        inflate(R.layout.feature_dropdown_layout) // as RelativeLayout

        material_dropdown_container.setOnClickListener { it -> onDropdownClicked(it) }

        listPopup = ListPopupWindow(context)

        listPopup?.anchorView = material_dropdown_container
        listPopup?.width = ListPopupWindow.MATCH_PARENT
        listPopup?.setOnItemClickListener { adapterView, view, i, l ->
            onItemSelected(i)
            listPopup?.dismiss()
        }
    }*/


    private fun onDropdownClicked(parent: View) : Unit {
        listPopup?.show()
    }

    // TODO add long press (hold, drag and select) behavior
    private fun onDropdownDragSelect() : Unit {}

    private fun onItemSelected(position: Int) : Unit {

        val option = mOptionAdapter?.getItem(position)

        // animate the title down, while down, change the title's text
        // and then animate a slide up
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

                        if(option?.icon != null) {
                            material_dropdown_icon.setImageBitmap(option?.icon)
                        } else {
                            material_dropdown_icon.setImageDrawable(
                                    resources.getDrawable(option!!.drawableRes, null)
                            )
                        }
                    }

                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationCancel(animation: Animator?) {}
                })
                .start()

        // create a daemon thread
        val timer = Timer("schedule", true)

        // schedule a single event
        timer.schedule( delay = 400L, action = {
            if(callback != null) {
                callback?.onItemSelected(option!!)
            }
        }).run()
    }


    fun setOnItemSelectedCallback(callback: onItemSelectedCallback): Unit {
        this.callback = callback
    }


    fun setOptionsAdapter(adapter: SimpleOptionListAdapter) {
        this.listPopup!!.setAdapter(adapter)
        this.mOptionAdapter = adapter

        adapter.notifyDataSetChanged()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }


    /**
     *
     */
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    /**
     *
     */
    private fun View.applyDimension(value: Float, type : Int = TypedValue.COMPLEX_UNIT_DIP): Float{
        val metrics  = resources.displayMetrics
        val resultPix = TypedValue.applyDimension(type, value, metrics)
        return resultPix
    }




    /**
     * Computes the widest view in an mOptionAdapter, best used when you need to wrap_content on a ListView, please be careful
     * and don't use it on an mOptionAdapter that is extremely numerous in items or it will take a long time.
     *
     * @param context Some context
     * @param mOptionAdapter The mOptionAdapter to process
     * @return The pixel width of the widest View
     */
    //    private fun getWidestView(context: Context, mOptionAdapter: Adapter): Int {
//
//        var maxWidth = 0
//        var view: View? = null
//        val fakeParent = FrameLayout(context)
//
//        for (i: Int in intArrayOf(mOptionAdapter.count)){
//            view = mOptionAdapter.getView(i, view, fakeParent)
//            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
//            val width = view.measuredWidth
//
//            if (width > maxWidth) {
//                maxWidth = width
//            }
//        }
//
//        return maxWidth
//    }

}