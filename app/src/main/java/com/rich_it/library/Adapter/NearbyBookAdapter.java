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

import java.util.List;

public class NearbyBookAdapter extends RecyclerView.Adapter<NearbyBookAdapter.ViewHolder> {
    Context context;
    List<Book> books;

    public NearbyBookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.nearby_books, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NearbyBookAdapter.ViewHolder holder, int position) {
        holder.nameTv.setText(books.get(position).getName());
        holder.authorTv.setText(books.get(position).getAuthor());
        Picasso.get().load(books.get(position).getImageId()).into(holder.coverIv);
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
            nameTv = itemView.findViewById(R.id.book_name);
            authorTv = itemView.findViewById(R.id.author_name);
            coverIv = itemView.findViewById(R.id.book_cover_img);
        }
    }
}
