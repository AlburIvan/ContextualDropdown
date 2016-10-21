package com.raworkstudio.contexdropdown;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ivan Alburquerque on 9/8/2016
 */
public class OptionDropDown extends RelativeLayout {

    private final String TAG = OptionDropDown.class.getSimpleName();

    RelativeLayout containerView;

    ImageView mIconView;
    ImageView mSortView;
    TextView mTitleView;
    TextView mSubtitleView;
    List<Option> options;

    OptionListAdapter2 adapter;
    ListPopupWindow listPopup;


    public OptionDropDown(Context context) {
        super(context);
        init();
    }

    public OptionDropDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OptionDropDown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public OptionDropDown(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init()
    {
        options = new ArrayList<>();
        options.add(new Option("Economy Class",     "Super Saver", R.drawable.ic_economic_class));
        options.add(new Option("Premium Class",     "More Comfortable", R.drawable.ic_premium_class));
        options.add(new Option("Business Class",    "High Quality", R.drawable.ic_business_class));
        options.add(new Option("First Class",       "Most Expensive", R.drawable.ic_first_class));

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        containerView
                = (RelativeLayout) inflater.inflate(R.layout.feature_dropdown_layout, this, true);

        RelativeLayout relativeLayout = (RelativeLayout)
                containerView.findViewById(R.id.material_dropdown_container);


        mIconView = (ImageView) containerView.findViewById(R.id.material_dropdown_icon);
        mSortView = (ImageView) containerView.findViewById(R.id.material_dropdown_sort);
        mTitleView = (TextView) containerView.findViewById(R.id.material_dropdown_title);
        mSubtitleView = (TextView) containerView.findViewById(R.id.material_dropdown_subtitle);

        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onParentClicked(view);
            }
        });

        adapter = new OptionListAdapter2(getContext(), android.R.layout.simple_list_item_1, options);
        listPopup = new ListPopupWindow(getContext());
        listPopup.setAdapter(adapter);
        listPopup.setAnchorView(findViewById(R.id.material_dropdown_container));
        listPopup.setWidth(ListPopupWindow.MATCH_PARENT /*getWidestView(getContext(), adapter) */);
        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateUI(adapter.getItem(i));
                listPopup.dismiss();
            }
        });
    }



 /*   @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setOutlineProvider(new CustomOutline(w, h));
//        }
    }*/

 /*   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private class CustomOutline extends ViewOutlineProvider {

        int width;
        int height;

        CustomOutline(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, width , height);
        }
    }*/

    private void onParentClicked(View parent) {
        Log.d(TAG, "onParentClicked: Dropdown clicked");
        listPopup.show();
    }


    public void updateUI(final Option option)
    {
        mTitleView.animate()
                .setDuration(350)
                .alphaBy(-30)
                .translationY(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {}

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mTitleView.setText(option.getTitle());

                        mTitleView.animate()
                                .setDuration(150)
                                .alphaBy(10)
                                .translationY(0)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {}

                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                })
                .start();

        if(option.getIcon() >= 0)
        {
            mIconView.animate()
                    .setDuration(250)
                    .alphaBy(-20)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {}

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            mIconView.animate()
                                    .setDuration(250)
                                    .alphaBy(20)
                                    .start();

                            mIconView.setImageResource(option.getIcon());
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {}

                        @Override
                        public void onAnimationRepeat(Animator animator) {}
                    })
                    .start();
        }


        mSubtitleView.setText(option.getSubtitle());
    }

    /**
     * Computes the widest view in an adapter, best used when you need to wrap_content on a ListView, please be careful
     * and don't use it on an adapter that is extremely numerous in items or it will take a long time.
     *
     * @param context Some context
     * @param adapter The adapter to process
     * @return The pixel width of the widest View
     */
    public static int getWidestView(Context context, Adapter adapter) {
        int maxWidth = 0;
        View view = null;
        FrameLayout fakeParent = new FrameLayout(context);
        for (int i=0, count = adapter.getCount(); i<count; i++) {
            view = adapter.getView(i, view, fakeParent);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = view.getMeasuredWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

}
