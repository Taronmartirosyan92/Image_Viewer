package com.example.taron.myimageview.dialogFragmentForImg;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taron.myimageview.R;
import com.example.taron.myimageview.adapter.ListAdapterForImg;
import com.example.taron.myimageview.model.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ImgFragmentDialog extends DialogFragment {
    private List<Result> resultList = Collections.emptyList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.img_dialog_fragment, container, false);
        final ImageView imageView = view.findViewById(R.id.fr_img);
        if (getArguments() != null) {
            int position = getArguments().getInt(ListAdapterForImg.BUNDLE_KEY);
            Picasso.get().load(resultList.get(position).getUrl()).into(imageView);
        }
        return view;
    }

    public void getList(List<Result> list) {
        this.resultList = list;
    }
}
