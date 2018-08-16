package com.example.taron.myimageview.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taron.myimageview.dialogFragmentForImg.ImgFragmentDialog;
import com.example.taron.myimageview.R;
import com.example.taron.myimageview.activity.MainActivity;
import com.example.taron.myimageview.model.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ListAdapterForImg extends RecyclerView.Adapter<ListAdapterForImg.ImageViewHolder> {
    private static final String FR_DAG = "tag";
    public static final String BUNDLE_KEY = "KEY";
    private List<Result> list = Collections.emptyList();
    private Context context;

    public ListAdapterForImg(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterForImg.ImageViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterForImg.ImageViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getThumbnailUrl()).into(holder.img);
    }

    public void setData(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;
        private FragmentManager fragmentTransaction;

        ImageViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
            img = itemView.findViewById(R.id.image_id);
            fragmentTransaction = ((MainActivity) context).getFragmentManager();
            imgOnClick();
        }

        private void imgOnClick() {
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(BUNDLE_KEY, getAdapterPosition());
                    ImgFragmentDialog imgFragmentDialog = new ImgFragmentDialog();
                    imgFragmentDialog.setArguments(bundle);
                    imgFragmentDialog.show(fragmentTransaction, FR_DAG);
                    imgFragmentDialog.getList(list);
                }
            });
        }
    }
}
