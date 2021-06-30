package com.rich_it.library.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rich_it.library.Abstract.DrawerItem;
import com.rich_it.library.Activity.NavigationActivity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    Context context;
    List<DrawerItem> items;

    private Map<Class<? extends DrawerItem>, Integer> viewTypes;
    private SparseArray<DrawerItem> holderFactories;
    private OnItemSelectedListener listener;

    public DrawerAdapter(List<DrawerItem> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();
        processViewTypes();
    }

    public void setListener(OnItemSelectedListener listener){
        this.listener = listener;
    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item:items){
            if(!viewTypes.containsKey(item.getClass())){
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

    public void setSelected(int position){
        DrawerItem newChecked = items.get(position);
        if(!newChecked.isSelectable()){
            return;
        }
        for (int i=0; i<items.size(); i++){
            DrawerItem item = items.get(i);
            if (item.isChecked()){
                item.setCheck(false);
                notifyItemChanged(i);
                break;
            }
        }
        newChecked.setCheck(true);
        notifyItemChanged(position);
        if(listener!=null){
            listener.onItemSelected(position);
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    @NonNull
    @NotNull
    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.drawerAdapter = this;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DrawerAdapter.ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DrawerAdapter drawerAdapter;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            drawerAdapter.setSelected(getAbsoluteAdapterPosition());
        }
    }
}
