package com.example.richard.owy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class debtPageAdapter extends BaseAdapter {

    LayoutInflater debtInflater;
    String[] naamSchuld;
    String[] schuldBedrag;

    public debtPageAdapter(Context c, String[] nS, String[] sB) {
        naamSchuld = nS;
        schuldBedrag = sB;
        debtInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View z = debtInflater.inflate (R.layout.debt_textview_detail, null);
        TextView debtPageNameView = z.findViewById(R.id.debtPageNameView);
        TextView debtPageTextView = z.findViewById(R.id.debtPageTextView);
        String name = naamSchuld[position];
        String bedrag = schuldBedrag[position];


        debtPageNameView.setText(name);
        debtPageTextView.setText(bedrag);
        return z;
    }
}
