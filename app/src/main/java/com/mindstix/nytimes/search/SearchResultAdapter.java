package com.mindstix.nytimes.search;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.models.search.Doc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private List<Doc> docList;

    public SearchResultAdapter(@NonNull Context context, @NonNull List<Doc> docs) {
        inflater = LayoutInflater.from(context);
        this.docList = docs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Doc document = docList.get(position);
        if (document == null) {
            return;
        }

        if (TextUtils.isEmpty(document.getSnippet())) {
            docList.remove(document);
        } else {
            holder.resultTitle.setText(document.getSnippet().trim());
        }
    }

    @Override
    public int getItemCount() {
        if(docList != null) {
            return docList.size();
        }
        return 0;
    }

    public void clearData() {
        docList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.result_title)
        TextView resultTitle;
        @BindView(R.id.result_sub_title)
        TextView resultSubTitle;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
