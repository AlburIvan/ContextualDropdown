package com.raworkstudio.contexdropdown

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
 * Created by Ivan on 10/21/2016
 */
class OptionDropDown(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
        RelativeLayout(context, attrs, defStyleAttr) {

    private val TAG: String = this.javaClass.simpleName


//    var containerView: RelativeLayout
    var options: MutableList<Option> = ArrayList()
    var adapter: SimpleOptionListAdapter
    var listPopup: ListPopupWindow



    init {

//        options.add(Option("Economy Class", "Super Saver", R.drawable.ic_economic_class))
//        options.add(Option("Premium Class", "More Comfortable", R.drawable.ic_premium_class))
//        options.add(Option("Business Class", "High Quality", R.drawable.ic_business_class))
//        options.add(Option("First Class", "Most Expensive", R.drawable.ic_first_class))


        inflate(R.layout.feature_dropdown_layout) as RelativeLayout
//        containerView = inflate(R.layout.feature_dropdown_layout) as RelativeLayout

        material_dropdown_container.setOnClickListener { it -> onParentClicked(it) }


        adapter = SimpleOptionListAdapter(getContext(), android.R.layout.simple_list_item_1, options)

        listPopup = ListPopupWindow(context)
        listPopup.setAdapter(adapter)
        listPopup.anchorView = material_dropdown_container
        listPopup.width = ListPopupWindow.MATCH_PARENT
        listPopup.setOnItemClickListener { adapterView, view, i, l ->
            onItemSelected(i)
            listPopup.dismiss()
        }


    }

    private fun onItemSelected(position: Int) {

        val option = adapter.getItem(position)

        material_dropdown_title.animate()
                .setDuration(350)
                .alphaBy(-30f)
                .translationY(100f)
                .setListener(object: Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        material_dropdown_title.text = option.title

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


        if(option.icon != null) {
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

                            material_dropdown_icon.setImageBitmap(option.icon)
                        }

                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationCancel(animation: Animator?) {}
                    })
                    .start()
        }
    }


    private fun onParentClicked(parent: View) : Unit {
        listPopup.show()
    }


    /**
     *
     */
    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
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