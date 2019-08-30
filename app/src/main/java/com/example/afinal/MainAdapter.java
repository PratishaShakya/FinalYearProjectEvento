package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] grid;
    private int[] gridimages;

    public MainAdapter(Context c,String[] grid, int[] gridimages){
        context = c;
        this.grid = grid;
        this.gridimages = gridimages;
    }

    @Override
    public int getCount() {
        return grid.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_item,null);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.grid_text);
imageView.setImageResource(gridimages[position]);
       // imageView.setImageResource(gridimages[position]);
        textView.setText(grid[position]);
        return convertView;
    }

}
