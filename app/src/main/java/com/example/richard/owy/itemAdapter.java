package com.example.richard.owy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class itemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] naamSchuld;
    String[] schuldBedrag;

    public itemAdapter(Context c, String[] n, String[] s) {
        naamSchuld = n;
        schuldBedrag = s;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return naamSchuld.length;
    }

    @Override
    public Object getItem(int position) {
        return naamSchuld[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView naamTextView = (TextView) v.findViewById(R.id.naamTextView);
        TextView bedragTextView = (TextView) v.findViewById(R.id.bedragTextView);
        String name = naamSchuld[position];
        String bedrag = schuldBedrag[position];

        naamTextView.setText(name);
        bedragTextView.setText(bedrag);

        return v;
    }
}
