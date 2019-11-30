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

public class ListKomen extends ArrayAdapter <ModelKomen> {
    private ArrayList<ModelKomen> list;
    private LayoutInflater inflater;
    private int res;

    public ListKomen(@NonNull Context context, int resource, ArrayList<ModelKomen> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ListKomen.MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new ListKomen.MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id);
            holder.NAMA = (TextView) convertView.findViewById(R.id.detail_nama);
            holder.KOMEN = (TextView) convertView.findViewById(R.id.detail_komen);
//            holder.CREATED = (TextView) convertView.findViewById(R.id.detail_created);
            holder.Komen = (CardView) convertView.findViewById(R.id.cardview_detail_komen);

            convertView.setTag(holder);

        } else {

            holder = (ListKomen.MyHolder) convertView.getTag();
        }

        holder.NAMA.setText(list.get(position).getNama()+ "" + list.get(position).getCreated_at()+" "+list.get(position).getUsername());
        holder.KOMEN.setText(list.get(position).getComment());
//        holder.CREATED.setText(list.get(position).getCreated_at()+" "+list.get(position).getUsername());
        holder.Komen.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
//                final Context context = view.getContext();
//
//                Intent intent = new Intent(context, ThreadView.class);
//                intent.putExtra("nama_materi", String.valueOf(list.get(position).getIdThread()));
//                ((AppCompatActivity) context).startActivity(intent);

            }
        }));


        return convertView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelKomen object) {
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
        TextView KOMEN;
        TextView CREATED;
        TextView USSERNAME;


        CardView Komen;

    }
}
