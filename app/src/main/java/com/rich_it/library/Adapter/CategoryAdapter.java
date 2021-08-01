package com.rich_it.library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rich_it.library.Model.Category;
import com.rich_it.library.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    ArrayList<Category> categories = new ArrayList<>();
    Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_details_activity_layout_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_layout_tv);
        }
    }
}
