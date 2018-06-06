package com.example.richard.owy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class receivePageAdapter extends BaseAdapter {

    LayoutInflater receiveInflater;
    String[] naamOntvang;
    String[] ontvangbedrag;

    public receivePageAdapter(Context c, String[] nO, String[] oB) {
        naamOntvang = nO;
        ontvangbedrag = oB;
        receiveInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View v = receiveInflater.inflate(R.layout.receive_page_listview_detail, null);
        TextView receivePageNameTextView = v.findViewById(R.id.receivePageNameTextView);
        TextView receivePageAmountTextView = v.findViewById(R.id.receivePageAmountTextView);
        String name = naamOntvang[position];
        String bedrag = ontvangbedrag[position];

        receivePageNameTextView.setText(name);
        receivePageAmountTextView.setText(bedrag);
        return v;
    }
}
