package com.rich_it.library.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rich_it.library.Model.Category;
import com.rich_it.library.Model.Publication;
import com.rich_it.library.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PublicatiionAdapter extends RecyclerView.Adapter<PublicatiionAdapter.ViewHolder> {
    private static final String TAG = PublicatiionAdapter.class.getName();
    Context context;
    ArrayList<Publication> publications = new ArrayList<>();

    public PublicatiionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PublicatiionAdapter.ViewHolder holder, int position) {
        Publication publication = publications.get(position);
        holder.name.setText(publication.getName());
        for(Category category: publication.getCategories()){
            Log.d(TAG, "onBindViewHolder: " + category.getName());
        }
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryName);
        }
    }
}
