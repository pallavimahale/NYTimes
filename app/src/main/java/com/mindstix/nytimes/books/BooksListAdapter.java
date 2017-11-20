package com.mindstix.nytimes.books;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindstix.nytimes.R;
import com.mindstix.nytimes.network.models.books.BestSellerResult;
import com.mindstix.nytimes.network.models.books.BookDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private View.OnClickListener listener;
    private List<BestSellerResult> resultList;

    public BooksListAdapter(@NonNull Context context, @NonNull View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    public void setData(@NonNull List<BestSellerResult> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_books, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BestSellerResult result = resultList.get(position);
        if (result == null) {
            return;
        }

        List<BookDetail> bookDetailList = result.getBookDetails();
        if (bookDetailList == null || bookDetailList.size() == 0) {
            return;
        }

        BookDetail bookDetail = bookDetailList.get(0);
        if (bookDetail == null) {
            return;
        }

        holder.bookTitle.setText(bookDetail.getTitle());
        holder.bookAuthor.setText(bookDetail.getAuthor());
        holder.bookRank.setText(String.format(context.getResources().getString(R.string
                .book_rank), result.getRank()));
        holder.bookDescription.setText(bookDetail.getDescription());
        holder.bookCard.setTag(R.id.book_card, bookDetail);
    }

    @Override
    public int getItemCount() {
        if (resultList != null) {
            return resultList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book_rank)
        TextView bookRank;
        @BindView(R.id.book_title)
        TextView bookTitle;
        @BindView(R.id.book_author)
        TextView bookAuthor;
        @BindView(R.id.book_description)
        TextView bookDescription;
        @BindView(R.id.book_card)
        CardView bookCard;

        private ViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bookCard.setOnClickListener(listener);
        }
    }
}
