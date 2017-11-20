package com.mindstix.nytimes.books;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindstix.nytimes.Communicator;
import com.mindstix.nytimes.MainActivity;
import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.RetrofitController;
import com.mindstix.nytimes.network.models.books.BestSellerOverview;
import com.mindstix.nytimes.network.models.books.BookDetail;
import com.mindstix.nytimes.network.models.books.BookOverview;
import com.mindstix.nytimes.network.models.books.BooksOverviewList;
import com.mindstix.nytimes.network.models.books.BooksOverviewResult;
import com.mindstix.nytimes.network.models.books.history.BookHistoryResult;
import com.mindstix.nytimes.network.models.books.history.BooksHistoryResponse;
import com.mindstix.nytimes.network.models.books.history.RanksHistory;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mindstix.nytimes.books.BooksListFragment.BOOK_DATA_KEY;

public class BookDetailsFragment extends Fragment {

    private static final String TAG = BooksListFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.book_image)
    ImageView bookImage;
    @BindView(R.id.details_book_title)
    TextView detailsBookTitle;
    @BindView(R.id.details_book_author)
    TextView detailsBookAuthor;
    @BindView(R.id.details_book_description)
    TextView detailsBookDescription;
    @BindView(R.id.history_data)
    TextView historyData;
    private Context context;
    private Communicator communicator;
    private BookDetail bookDetail;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
        if (context instanceof Communicator) {
            communicator = (Communicator) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View booksList = inflater.inflate(R.layout.fragment_book_details, container, false);
        unbinder = ButterKnife.bind(this, booksList);

        Bundle bookDetailsBundle = getArguments();
        if (bookDetailsBundle != null) {
            if (bookDetailsBundle.getSerializable(BOOK_DATA_KEY) != null) {
                bookDetail = (BookDetail) bookDetailsBundle.getSerializable(BOOK_DATA_KEY);
            }
        }

        if (bookDetail != null) {
            detailsBookTitle.setText(bookDetail.getTitle());
            detailsBookAuthor.setText(bookDetail.getAuthor());
            detailsBookDescription.setText(bookDetail.getDescription());
        }

        getBookHistory();
        getOverviewDetails();
        return booksList;
    }

    private void getOverviewDetails() {
        final Call<BestSellerOverview> booksOverview = RetrofitController.getInstance()
                .getNyTimesApi().getBooksOverview(MainActivity.API_KEY);

        if (communicator != null) {
            communicator.showProgressDialog(context.getString(R.string.fetching_data));
        }

        booksOverview.enqueue(new Callback<BestSellerOverview>() {
            @Override
            public void onResponse(Call<BestSellerOverview> call, Response<BestSellerOverview>
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

                BestSellerOverview overview = response.body();
                if (overview == null) {
                    return;
                }

                BooksOverviewResult overviewResult = overview.getResults();
                if (overviewResult == null) {
                    return;
                }

                List<BooksOverviewList> overviewList = overviewResult.getLists();
                if (overviewList == null) {
                    return;
                }
                BookOverview overviewData = searchOverview(overviewList);
                displayOverViewData(overviewData);
            }

            @Override
            public void onFailure(Call<BestSellerOverview> call, Throwable t) {
                Log.e("getOverviewDetails", "onFailure " + t);
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }

            }
        });
    }

    private void displayOverViewData(BookOverview overviewData) {
        if (overviewData == null) {
            Log.d(TAG, "In displayOverViewData(): Overview Data is null");
            bookImage.setImageResource(R.drawable.no_image_available);
            return;
        }

        if (!TextUtils.isEmpty(overviewData.getBookImage()) && bookImage != null) {
            Picasso.with(context).load(overviewData.getBookImage()).error(R.drawable
                    .no_image_available).into(bookImage);
        } else {
            Log.d(TAG, "In displayOverViewData():Image URL is null");
            if (bookImage != null) {
                bookImage.setImageResource(R.drawable.no_image_available);
            }
        }
    }

    private BookOverview searchOverview(List<BooksOverviewList> overviewList) {

        for (BooksOverviewList overviewItem : overviewList) {

            if (overviewItem == null) {
                continue;
            }

            if (overviewItem.getListName().equalsIgnoreCase(context.getString(R.string
                    .book_category))) {

                List<BookOverview> bookList = overviewItem.getBooks();
                if (bookList == null) {
                    continue;
                }

                for (int i = 0; i < bookList.size(); i++) {
                    BookOverview bookOverview = bookList.get(i);
                    if (bookOverview == null) {
                        continue;
                    }
                    if (bookOverview.getPrimaryIsbn13().equalsIgnoreCase(bookDetail
                            .getPrimaryIsbn13())) {
                        return bookOverview;
                    }
                }
            }
        }
        return null;
    }

    private void getBookHistory() {
        if (bookDetail == null) {
            return;
        }

        final Call<BooksHistoryResponse> booksHistory = RetrofitController.getInstance()
                .getNyTimesApi().getBooksHistory(MainActivity.API_KEY, bookDetail
                        .getPrimaryIsbn13());

        booksHistory.enqueue(new Callback<BooksHistoryResponse>() {
            @Override
            public void onResponse(Call<BooksHistoryResponse> call,
                                   Response<BooksHistoryResponse> response) {
                if (communicator != null) {
                    communicator.hideProgressDialog();
                }


                if (response == null) {
                    return;
                }

                if (response.body() == null) {
                    return;
                }

                BooksHistoryResponse booksHistoryResponse = response.body();
                if (booksHistoryResponse == null) {
                    return;
                }

                List<BookHistoryResult> historyResults = booksHistoryResponse.getResults();
                if (historyResults == null) {
                    return;
                }

                for (BookHistoryResult historyResult : historyResults) {

                    List<RanksHistory> list = historyResult.getRanksHistory();
                    if (list == null) {
                        return;
                    }

                    for (RanksHistory history : list) {
                        historyData.setText("Display Name: " + history.getDisplayName() + "\n");
                        historyData.append("List Name: " + history.getListName() + "\n");
                        historyData.append("Weeks On List: " + history.getWeeksOnList() + "\n");
                        historyData.append("Rank: " + history.getRank() + "\n\n\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<BooksHistoryResponse> call, Throwable t) {
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
