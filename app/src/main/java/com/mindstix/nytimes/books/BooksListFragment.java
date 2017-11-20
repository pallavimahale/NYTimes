package com.mindstix.nytimes.books;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindstix.nytimes.Communicator;
import com.mindstix.nytimes.MainActivity;
import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.RetrofitController;
import com.mindstix.nytimes.network.models.books.BestSellerData;
import com.mindstix.nytimes.network.models.books.BestSellerResult;
import com.mindstix.nytimes.network.models.books.BookDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BooksListFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.best_seller_books)
    RecyclerView bestSellerBooks;
    Unbinder unbinder;
    private Context context;
    private Communicator communicator;
    public static final String BOOK_DATA_KEY = "bookDetails";
    private List<BestSellerResult> resultList;
    private BooksListAdapter adapter;

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
        adapter = new BooksListAdapter(context, BooksListFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View booksList = inflater.inflate(R.layout.fragment_books, container, false);
        unbinder = ButterKnife.bind(this, booksList);
        bestSellerBooks.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager
                .VERTICAL, false));
        bestSellerBooks.setAdapter(adapter);

        if (resultList == null) {
            getAllBooks();
        } else {
            adapter.setData(resultList);
        }

        return booksList;
    }

    private void getAllBooks() {
        final Call<BestSellerData> booksOverview = RetrofitController.getInstance().getNyTimesApi
                ().getBestSellers(MainActivity.API_KEY, context.getString(R.string.book_category));

        if (communicator != null) {
            communicator.showProgressDialog(context.getString(R.string.fetching_data));
        }

        booksOverview.enqueue(new Callback<BestSellerData>() {
            @Override
            public void onResponse(Call<BestSellerData> call, Response<BestSellerData> response) {

                if (communicator != null) {
                    communicator.hideProgressDialog();
                }

                if (response == null) {
                    return;
                }

                if (response.body() == null) {
                    return;
                }

                BestSellerData bestSellerData = response.body();

                resultList = bestSellerData.getResults();
                adapter.setData(resultList);
            }

            @Override
            public void onFailure(Call<BestSellerData> call, Throwable t) {
                Log.e("onFailure", "Failed" + t);
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }
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
            case R.id.book_card:
                if (view.getTag(R.id.book_card) != null) {
                    BookDetail bookDetail = (BookDetail) view.getTag(R.id.book_card);
                    BookDetailsFragment fragment = new BookDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BOOK_DATA_KEY, bookDetail);
                    fragment.setArguments(bundle);

                    if (communicator != null) {
                        communicator.startFragment(fragment);
                        communicator.toggleNavigationBar(View.GONE);
                        communicator.setToolbarTitle("Details");
                        communicator.displayDetailsActionBar();
                    }
                }
                break;
        }
    }
}
