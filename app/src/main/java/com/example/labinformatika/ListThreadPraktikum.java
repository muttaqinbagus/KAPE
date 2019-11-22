package com.example.labinformatika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListThreadPraktikum extends ArrayAdapter <ModelPraktikum> {

    private ArrayList<ModelPraktikum> list;
    private LayoutInflater inflater;
    private int res;

    public ListThreadPraktikum(@NonNull Context context, int resource, ArrayList<ModelPraktikum> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id_thread_praktikum);
            holder.NAMA = (TextView) convertView.findViewById(R.id.thread_praktikum);
            holder.ThreadPraktikum = (CardView) convertView.findViewById(R.id.cardview_thread_praktikum);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

//        holder.ID.setText(list.get(position).getidPraktikum());
        holder.NAMA.setText(list.get(position).getNamaPraktikum());
        holder.ThreadPraktikum.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                final Context context = view.getContext();

                Intent intent = new Intent(context, ThreadModul.class);
                intent.putExtra("id_praktikum", String.valueOf(list.get(position).getidPraktikum()));
                ((AppCompatActivity) context).startActivity(intent);

            }
        }));

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelPraktikum object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {

//        TextView ID;
        TextView NAMA;
        CardView ThreadPraktikum;

    }
}
