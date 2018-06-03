package com.example.richard.owy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class receiveAdapter extends BaseAdapter {

    LayoutInflater nInflater;
    String[] naamOntvang;
    String[] ontvangBedrag;

    public receiveAdapter(Context c, String[] r, String[] o) {
        naamOntvang = r;
        ontvangBedrag = o;
        nInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return naamOntvang.length;
    }

    @Override
    public Object getItem(int position) {
        return naamOntvang[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View q = nInflater.inflate(R.layout.receive_listview_detail, null);
        TextView nameTextView = (TextView) q.findViewById(R.id.nameTextView);
        TextView receiveTextView = (TextView) q.findViewById(R.id.receiveTextView);
        String naam = naamOntvang[position];
        String amount = ontvangBedrag[position];

        nameTextView.setText(naam);
        receiveTextView.setText(amount);
        return q;
    }
}
