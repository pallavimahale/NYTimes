package com.mindstix.nytimes.home;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.models.Multimedium;
import com.mindstix.nytimes.network.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private List<Result> topStoryList;

    public HomeAdapter(@NonNull Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void setData(List<Result> topStoryList) {
        this.topStoryList = topStoryList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = topStoryList.get(position);
        if (result == null) {
            return;
        }

        holder.storyTitle.setText(result.getTitle());
        holder.storySubTitle.setText(result.getAbstract());

        if (result.getMultimedia() != null) {

            List<Multimedium> multimediaList = result.getMultimedia();

            for (Multimedium multimedia : multimediaList) {
                if (multimedia != null && !TextUtils.isEmpty(multimedia.getType()) && multimedia
                        .getFormat().equalsIgnoreCase("superJumbo")) {

                    String url = multimedia.getUrl();
                    if (url != null) {
                        Picasso.with(context).load(url).into(holder.storyImage);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return (topStoryList == null) ? 0 : topStoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.story_title)
        TextView storyTitle;
        @BindView(R.id.story_sub_title)
        TextView storySubTitle;
        @BindView(R.id.top_story_image)
        ImageView storyImage;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
