package com.rich_it.library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rich_it.library.Model.Book;
import com.rich_it.library.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SuggestedBookAdapter extends RecyclerView.Adapter<SuggestedBookAdapter.ViewHolder> {
    Context context;
    List<Book> books = new ArrayList<>();

    public SuggestedBookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public SuggestedBookAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_suggested_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SuggestedBookAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor().getName());
        holder.pageNumber.setText("Page: " + String.valueOf(book.getRent()));
        holder.price.setText("BDT: " + String.valueOf(book.getActualPrice()));
        Picasso.get().load(book.getCoverPage()).into(holder.coverIv);
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void addBooks(ArrayList<Book> books) {
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView author;
        TextView pageNumber;
        TextView price;
        ImageView coverIv;

        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.book_name_tv);
            author = itemView.findViewById(R.id.book_author_tv);
            pageNumber = itemView.findViewById(R.id.book_page_tv);
            price = itemView.findViewById(R.id.book_price_tv);
            coverIv = itemView.findViewById(R.id.book_cover_tv);
        }
    }
}
