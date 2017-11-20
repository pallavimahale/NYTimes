package com.mindstix.nytimes.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindstix.nytimes.Communicator;
import com.mindstix.nytimes.MainActivity;
import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.RetrofitController;
import com.mindstix.nytimes.network.models.Result;
import com.mindstix.nytimes.network.models.TopStoriesResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @BindView(R.id.top_stories_list)
    RecyclerView topStoriesList;
    Unbinder unbinder;
    private Context context;
    private Communicator communicator;
    private List<Result> topStoryList;
    private HomeAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof Communicator) {
            communicator = (Communicator) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, homeView);

        topStoriesList.setLayoutManager(new LinearLayoutManager(context));
        topStoriesList.setAdapter(adapter);
        if (topStoryList == null) {
            getAllTopStories();
        } else {
            adapter.setData(topStoryList);
        }

        return homeView;
    }

    private void getAllTopStories() {

        final Call<TopStoriesResponse> topStories = RetrofitController.getInstance()
                .getNyTimesApi().getTopStories(MainActivity.API_KEY);

        if (communicator != null) {
            communicator.showProgressDialog(context.getString(R.string.fetching_data));
        }
        topStories.enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Call<TopStoriesResponse> call, Response<TopStoriesResponse>
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

                TopStoriesResponse topStoriesResponse = response.body();
                if (topStoriesResponse.getResults() == null) {
                    return;
                }

                topStoryList = topStoriesResponse.getResults();
                adapter.setData(topStoryList);

            }

            @Override
            public void onFailure(Call<TopStoriesResponse> call, Throwable t) {
                Log.e("onFailure", "onFailure" + t);
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
