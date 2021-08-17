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

import com.rich_it.library.Model.Book;
import com.rich_it.library.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NearbyBookAdapter extends RecyclerView.Adapter<NearbyBookAdapter.ViewHolder> {
    Context context;
    List<Book> books = new ArrayList<>();
    List<String> words;

    public NearbyBookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.card_view_nearby_books, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NearbyBookAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        words = Arrays.asList(book.getName().split(","));
        holder.nameTv.setText(words.get(0));
//        if(TextUtils.isEmpty(book.getAuthor().getName())){
//        }else{
////            holder.authorTv.setText(book.getAuthor().getName());
//            holder.authorTv.setText("Unknown Author");
//
//        }
        holder.authorTv.setText(words.get(1));

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

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView authorTv;
        ImageView coverIv;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nearby_book_name);
            authorTv = itemView.findViewById(R.id.nearby_book_author_name);
            coverIv = itemView.findViewById(R.id.nearby_book_cover_iv);
        }
    }
}
