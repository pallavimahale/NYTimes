package com.mindstix.nytimes.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mindstix.nytimes.Communicator;
import com.mindstix.nytimes.MainActivity;
import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.RetrofitController;
import com.mindstix.nytimes.network.models.search.SearchKeywordResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements View.OnClickListener, CategoryDialog
        .CategoryDataListener {

    Unbinder unbinder;
    @BindView(R.id.search_box)
    EditText searchBox;
    @BindView(R.id.search_result)
    RecyclerView searchResultList;
    @BindView(R.id.category_search)
    Button categorySearch;
    private Context context;
    private Communicator communicator;
    private static SearchFragment searchFragment;
    private CategoryDialog categoryDialog;

    private SearchResultAdapter adapter;

    public static SearchFragment getInstance() {
        if (searchFragment == null) {
            synchronized (SearchFragment.class) {
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                }
            }
        }
        return searchFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof Communicator) {
            communicator = (Communicator) context;
        }

        if (communicator != null) {
            communicator.setToolbarTitle("Search");
            communicator.toggleNavigationBar(View.GONE);
            communicator.displayDetailsActionBar();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, searchView);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s != null && s.length() >= 3) {
                    if (adapter != null) {
                        adapter.clearData();
                    }
                    searchByKeyword(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        categorySearch.setOnClickListener(this);
        return searchView;

    }

    private void searchByKeyword(String keyword) {

        final Call<SearchKeywordResult> searchResult = RetrofitController.getInstance()
                .getNyTimesApi().searchResultByKeyword(MainActivity.API_KEY, keyword);

        searchResult.enqueue(new Callback<SearchKeywordResult>() {
            @Override
            public void onResponse(Call<SearchKeywordResult> call, Response<SearchKeywordResult>
                    response) {

                if (response == null) {
                    return;
                }

                if (response.body() == null) {
                    return;
                }
                SearchKeywordResult searchResult = response.body();

                if (searchResult.getResponse() == null) {
                    return;
                }

                com.mindstix.nytimes.network.models.search.Response searchResponse = searchResult
                        .getResponse();
                if (searchResponse.getDocs() == null || searchResponse.getDocs().size() == 0) {
                    return;
                }
                searchResultList.setLayoutManager(new LinearLayoutManager(context));
                adapter = new SearchResultAdapter(context, searchResponse.getDocs());
                searchResultList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SearchKeywordResult> call, Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category_search:
                categoryDialog = new CategoryDialog(context, this);
                categoryDialog.show();
                break;
        }
    }

    @Override
    public void onDataSend(List<String> selectedList) {
        if (categoryDialog != null) {
            categoryDialog.dismiss();
        }
        if (selectedList == null || selectedList.size() == 0) {
            Toast.makeText(context, "No category selected", Toast.LENGTH_LONG).show();
            return;
        }

        String filteredQueryParam = "news_desk:(";
        for (int i = 0; i < selectedList.size(); i++) {
            filteredQueryParam += selectedList.get(i) + " ";
        }
        filteredQueryParam += ")";
        searchByCategory(filteredQueryParam);
    }

    private void searchByCategory(String keyword) {
        if (communicator != null) {
            communicator.showProgressDialog("Searching...");
        }

        final Call<SearchKeywordResult> searchResult = RetrofitController.getInstance()
                .getNyTimesApi().searchResultByCategory(MainActivity.API_KEY, keyword);

        searchResult.enqueue(new Callback<SearchKeywordResult>() {
            @Override
            public void onResponse(Call<SearchKeywordResult> call, Response<SearchKeywordResult>
                    response) {
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }
                if (response == null) {
                    return;
                }

                if (response.body() == null) {
                    return;
                }
                SearchKeywordResult searchResult = response.body();

                if (searchResult.getResponse() == null) {
                    return;
                }

                com.mindstix.nytimes.network.models.search.Response searchResponse = searchResult
                        .getResponse();
                if (searchResponse.getDocs() == null || searchResponse.getDocs().size() == 0) {
                    return;
                }
                searchResultList.setLayoutManager(new LinearLayoutManager(context));
                searchResultList.setAdapter(new SearchResultAdapter(context, searchResponse
                        .getDocs()));
            }

            @Override
            public void onFailure(Call<SearchKeywordResult> call, Throwable t) {
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }
            }
        });
    }
}
