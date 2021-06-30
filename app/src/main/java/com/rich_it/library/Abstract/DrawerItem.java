package com.rich_it.library.Abstract;

import android.view.ViewGroup;

import com.rich_it.library.Adapter.DrawerAdapter;

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder> {
    protected boolean isChecked;
    public abstract T createViewHolder(ViewGroup parent);
    public abstract void bindViewHolder(T Holder);

    public DrawerItem<T>setCheck(boolean isChecked){
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked(){
        return isChecked();
    }

    public boolean isSelectable(){
        return true;
    }
}
