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

public class ListModulPraktikum extends ArrayAdapter<ModelModul> {

    private ArrayList<ModelModul> list;
    private LayoutInflater inflater;
    private int res;

    public ListModulPraktikum(@NonNull Context context, int resource, ArrayList<ModelModul> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ListModulPraktikum.MyHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new ListModulPraktikum.MyHolder();

//            holder.ID = (TextView) convertView.findViewById(R.id.id);
            holder.NAMA = (TextView) convertView.findViewById(R.id.thread_modul);
            holder.ThreadModul = (CardView) convertView.findViewById(R.id.cardview_thread_modul);

            convertView.setTag(holder);

        } else {

            holder = (ListModulPraktikum.MyHolder) convertView.getTag();
        }

//        holder.NAMA.setText(list.get(position).getNamaModul());

        holder.NAMA.setText(list.get(position).getNamaModul());
        holder.ThreadModul.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                final Context context = view.getContext();

                Intent intent = new Intent(context, ThreadMateri.class);
                intent.putExtra("id_modul", String.valueOf(list.get(position).getIdModul()));
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
    public void remove(ModelModul object) {
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
        CardView ThreadModul;

    }
}
