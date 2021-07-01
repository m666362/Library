package com.rich_it.library.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import com.rich_it.library.Activity.NavigationActivity;
import com.rich_it.library.Adapter.DrawerAdapter;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.ThingsAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private static final String TAG = DashboardFragment.class.getName();
    NearbyBookAdapter adapter;
    FragmentActivity listener;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Book> books = new ArrayList<Book>();
        adapter = new NearbyBookAdapter(getActivity(), books);
        int SomeInt = getArguments().getInt("someInt", 0);
        String someTitle = getArguments().getString("someTitle", "");
        Log.d(TAG, "onCreate: " + someTitle + SomeInt);
        Toast.makeText(listener, someTitle + SomeInt, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);
        return root;
    }

    public static DashboardFragment newInstance(int someInt, String someTitle) {
        DashboardFragment dashboardFragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someTitle", someTitle);
        dashboardFragment.setArguments(args);
        return dashboardFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Activity activity = (NavigationActivity) getActivity();
        ArrayList<Book> books = ((NavigationActivity) activity).getBooks();
        for (Book book:books){
            Log.d(TAG, "onStart: " + book.getName()+book.getPublication());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
