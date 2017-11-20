package com.mindstix.nytimes.search;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mindstix.nytimes.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryDialog extends Dialog {

    @BindView(R.id.category_list)
    RecyclerView categoryList;
    @BindView(R.id.done)
    Button done;
    private Context context;
    private CategoryDataListener listener;

    public CategoryDialog(Context context, CategoryDataListener listener) {
        super(context, R.style.Theme_Dialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_dialog);
        ButterKnife.bind(this, this);

        categoryList.setLayoutManager(new LinearLayoutManager(context));
        final CategoryDialogAdapter adapter = new CategoryDialogAdapter(context);
        categoryList.setAdapter(adapter);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectedList = adapter.getSelectedList();
                listener.onDataSend(selectedList);
            }
        });
    }

    public interface CategoryDataListener {
        public void onDataSend(List<String> selectedList);
    }

}
