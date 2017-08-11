package com.example.senturk.gyk_proje;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.senturk.gyk_proje.R.id.textView_ad;

/**
 * Created by Senturk on 19.7.2017.
 */

public class CustomAdapter extends ArrayAdapter<Element> {
    private Activity context;
    private int resource;
    private List<Element> listImage;

    public CustomAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Element> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tvName = (TextView) v.findViewById(textView_ad);
        ImageView img = (ImageView) v.findViewById(R.id.imageView_resim);

        tvName.setText(listImage.get(position).getElementAdi());
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);

        return v;

    }
}
