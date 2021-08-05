
package com.pd.databaseui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CoustomListAdapter extends BaseAdapter {

     /*   holder.ID_TextView.setText(ID.get());
        holder.Name_TextView.setText(Name.get());
        holder.PhoneNumberTextView.setText(PhoneNumber.get());

    // ID = [1,2,3]
    //Name = [x,y,z]
    //PhoneNumber = [a,b,c]  */


    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> PhoneNumber;

    public CoustomListAdapter(Context context2, ArrayList<String> id, ArrayList<String> name,
                              ArrayList<String> phone) {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.PhoneNumber = phone;
    }

    @Override
    public int getCount() {
        return ID.size();
    } // impt

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;

        LayoutInflater layoutInflater;
        if (view == null) {

            layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            //   view = layoutInflater.inflate(R.layout.items, null);       //

            view = layoutInflater.inflate( R.layout.items, null );
            holder = new Holder();

            //items to inflate on a view are defined below;

            holder.ID_TextView = (TextView) view.findViewById( R.id.textViewID );
            holder.Name_TextView = (TextView) view.findViewById( R.id.textViewNAME );
            holder.PhoneNumberTextView = (TextView) view.findViewById( R.id.textViewPHONE_NUMBER );

            view.setTag( holder );


        } else {
            holder = (Holder) view.getTag();
        }
        holder.ID_TextView.setText( ID.get( i ) );
        holder.Name_TextView.setText( Name.get( i ) );
        holder.PhoneNumberTextView.setText( PhoneNumber.get( i ) );
        return view;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView PhoneNumberTextView;
    }
}

