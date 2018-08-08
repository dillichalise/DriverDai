package com.example.dillichalise.driverdai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{
    Context c;
    ArrayList<ListSeats> listSeats;

    public CustomAdapter(Context c,ArrayList<ListSeats> listSeats)
    {
        this.c=c;
        this.listSeats=listSeats;
    }

    @Override
    public int getCount()
    {
        return listSeats.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listSeats.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView busNoTxt=(TextView)convertView.findViewById(R.id.busNo);
        TextView seatNoTxt=(TextView)convertView.findViewById(R.id.seatNo);
        TextView fromTxt=(TextView)convertView.findViewById(R.id.from);
        TextView destinationTxt=(TextView)convertView.findViewById(R.id.destination);

        final ListSeats l =(ListSeats) this.getItem(position);

        busNoTxt.setText(l.getBusno());
        seatNoTxt.setText(l.getSeatno());
        fromTxt.setText(l.getFrom());
        destinationTxt.setText(l.getDestination());

        return convertView;
    }
}
