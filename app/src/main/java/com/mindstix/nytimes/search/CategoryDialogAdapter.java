package com.mindstix.nytimes.search;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mindstix.nytimes.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDialogAdapter extends RecyclerView.Adapter<CategoryDialogAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final String[] categoryList = new String[]{"Adventure Sports", "Business",
            "Circuits", "Crosswords & Games", "Fashion"};
    private List<String> selectedList = new ArrayList<>();

    public CategoryDialogAdapter(@NonNull Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.categoryName.setText(categoryList[position]);
        holder.categoryCheckbox.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    selectedList.add(categoryList[position]);
                } else {
                    selectedList.remove(categoryList[position]);
                }
            }
        });
    }

    public List<String> getSelectedList() {
        return selectedList;
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_name)
        TextView categoryName;
        @BindView(R.id.category_checkbox)
        CheckBox categoryCheckbox;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
