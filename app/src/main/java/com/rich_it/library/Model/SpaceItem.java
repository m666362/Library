package com.rich_it.library.Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.rich_it.library.Abstract.DrawerItem;
import com.rich_it.library.Adapter.DrawerAdapter;

import org.jetbrains.annotations.NotNull;

public class SpaceItem extends DrawerItem<SpaceItem.ViewHolder> {
    private int spaceOp;

    public SpaceItem(int spaceOp) {
        this.spaceOp = spaceOp;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        Context context = parent.getContext();
        View view = new View(context);
        int height = (int) (context.getResources().getDisplayMetrics().density*spaceOp);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return new ViewHolder(view);
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void bindViewHolder(ViewHolder Holder) {

    }

    public class ViewHolder extends DrawerAdapter.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
