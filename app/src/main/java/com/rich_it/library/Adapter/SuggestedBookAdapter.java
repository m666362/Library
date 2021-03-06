package com.rich_it.library.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rich_it.library.Model.Author;
import com.rich_it.library.Model.Book;
import com.rich_it.library.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestedBookAdapter extends RecyclerView.Adapter<SuggestedBookAdapter.ViewHolder> {
    Context context;
    List<Book> books = new ArrayList<>();
    List<String> words;

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
        words = Arrays.asList(book.getName().split(","));
        holder.name.setText(words.get(0));
//        if(TextUtils.isEmpty(book.getAuthor().getName())){
//            holder.author.setText("Unknown Author");
//        }else{
////            holder.author.setText(book.getAuthor().getName());
//            holder.author.setText("Unknown Author");
//        }
        holder.author.setText(words.get(1));
        holder.pageNumber.setText( String.valueOf(book.getDuration()) + " days");
        holder.price.setText("BDT: " + String.valueOf(book.getRent()));
        Picasso.get().load("https://img.freepik.com/free-psd/book-cover-mockup_125540-453.jpg?size=626&ext=jpg&ga=GA1.2.1937619873.1628553600").into(holder.coverIv);
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
