package com.raworkstudio.contexdropdown;


import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class OptionListAdapter2 extends ArrayAdapter<Option> {

    public OptionListAdapter2(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public OptionListAdapter2(Context context, int resource, List<Option> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.feature_dropdown_list_item, null);
        }


        Option p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.dropdown_list_item_text);
            tt1.setText(p.getTitle());
        }

        return v;
    }

}