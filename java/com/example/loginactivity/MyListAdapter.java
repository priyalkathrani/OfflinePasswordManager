package com.example.loginactivity;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] application;
    private final String[] user;
    private final String[] password;

    public MyListAdapter(Activity context, String[] a,String[] u,String[] p) {
        super(context, R.layout.my_layout, a);

        this.context=context;
        this.application=a;
        this.user=u;
        this.password=p;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_layout, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.Application);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.password);
        TextView username = (TextView) rowView.findViewById(R.id.username);

        titleText.setText(application[position]);
        subtitleText.setText(password[position]);
        username.setText(user[position]);
        return rowView;
    }
}