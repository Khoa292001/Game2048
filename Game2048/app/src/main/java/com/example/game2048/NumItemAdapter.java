package com.example.game2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NumItemAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Integer> arr;
    public NumItemAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public void notifyDataSetChanged() {
        arr = DataGame.getDataGame().getArrNum();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if ( convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.square_item,null);
        }
        if (arr.size() > 0){
            SquareItem squareItem =(SquareItem) convertView.findViewById(R.id.txvSquareItem);
            squareItem.setTextt(arr.get(position));
        }
        return convertView;
    }
}
