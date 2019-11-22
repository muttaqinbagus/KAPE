package com.example.labinformatika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListDetailThread extends ArrayAdapter <ModelDetailThread> {

    private ArrayList<ModelDetailThread> list;
    private LayoutInflater inflater;
    private int res;

    public ListDetailThread(@NonNull Context context, int resource, ArrayList<ModelDetailThread> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ListDetailThread.MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new ListDetailThread.MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id);
            holder.JUDULTHREAD = (TextView) convertView.findViewById(R.id.detail_judul);
            holder.NAMA = (TextView) convertView.findViewById(R.id.detail_nama);
//            holder.USERNAME = (TextView) convertView.findViewById(R.id.usser);
            holder.KET = (TextView) convertView.findViewById(R.id.detail_keterangan);
            holder.ThreadView = (CardView) convertView.findViewById(R.id.cardview_detail_thread);

            convertView.setTag(holder);

        } else {

            holder = (ListDetailThread.MyHolder) convertView.getTag();
        }

        holder.JUDULTHREAD.setText(list.get(position).getJudulThread());
        holder.NAMA.setText(list.get(position).getNamaMahasiswa());
//        holder.USERNAME.setText(list.get(position).getUsername());
        holder.KET.setText(list.get(position).getKet());
        holder.ThreadView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                final Context context = view.getContext();

                Intent intent = new Intent(context, ThreadView.class);
                intent.putExtra("nama_materi", String.valueOf(list.get(position).getIdThread()));
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
    public void remove(ModelDetailThread object) {
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

        //TextView ID;
        TextView JUDULTHREAD;
        TextView NAMA;
        TextView USERNAME;
        TextView KET;

        CardView ThreadView;

    }
}

