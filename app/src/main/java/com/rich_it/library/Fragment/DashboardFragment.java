package com.rich_it.library.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.rich_it.library.Activity.BookDetailsActivity;
import com.rich_it.library.Activity.GoogleMapActivity;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Others.GlobalVars;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.BookServerCalling;
import com.rich_it.library.Utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardFragment extends Fragment {

    private static final String TAG = DashboardFragment.class.getName();

    NearbyBookAdapter nearbyBookAdapter;
    SuggestedBookAdapter suggestedBookAdapter;

    RecyclerView nearbyBookRecyclerView;
    RecyclerView suggestedBooksRecyclerView;

    LinearLayoutManager linearLayoutManager;


    NestedScrollView scrollView;
    ProgressBar progressBar;
    Button getLocationButton;

    int pageNumber = 0;
    int itemsPerPage = 10;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int SomeInt = getArguments().getInt("someInt", 0);
        String someTitle = getArguments().getString("someTitle", "");
        Log.d(TAG, "onCreate: " + someTitle + SomeInt);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        progressBar = view.findViewById(R.id.dashpbLoading);
        scrollView = view.findViewById(R.id.parent_scroll);
//        getLocationButton = view.findViewById(R.id.getLocationButton_f);
//        getLocationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
//                startActivity(intent);
//            }
//        });

        nearbyBookRecyclerView = view.findViewById(R.id.nearby_book_rv_f);
        nearbyBookAdapter = new NearbyBookAdapter(getActivity());
        nearbyBookRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        nearbyBookRecyclerView.setNestedScrollingEnabled(false);
        nearbyBookRecyclerView.setAdapter(nearbyBookAdapter);

        suggestedBooksRecyclerView = view.findViewById(R.id.suggested_book_rv_f);
        suggestedBookAdapter = new SuggestedBookAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        suggestedBooksRecyclerView.setLayoutManager(linearLayoutManager);
        suggestedBooksRecyclerView.setNestedScrollingEnabled(false);
        suggestedBooksRecyclerView.setAdapter(suggestedBookAdapter);

        suggestedBooksRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), suggestedBooksRecyclerView , new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), GlobalVars.bookArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("Book", (Parcelable) GlobalVars.bookArrayList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getActivity(), "long hello", Toast.LENGTH_SHORT).show();
            }
        }));

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);

                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView
                        .getScrollY()));

//                Log.d(TAG, "onScrollChanged: " + diff);

                if (diff == 0) {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int pntbs = totalItemCount / itemsPerPage;
                    Log.d(TAG, "onScrollChanged: PPP " + String.format("visibleItemCount: %d totalItemCount: %d firstVisibleItemPosition: %d pntbs: %d", visibleItemCount, totalItemCount, firstVisibleItemPosition, pntbs));

                    if (pntbs != pageNumber) {
                        pageNumber = pntbs;
                        Log.d(TAG, "onScrollChanged: Pag: " + pageNumber);
                        getBooks();
                    }
                }
            }
        });

        if (GlobalVars.bookArrayList.size() == 0) {
            getBooks();
        } else {
            suggestedBookAdapter.setBooks(GlobalVars.bookArrayList);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Add new book", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getBooks() {
        progressBar.setVisibility(View.VISIBLE);
        BookServerCalling.getBooks(pageNumber, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Page: " + pageNumber);

                if (pageNumber == 0) {
                    suggestedBookAdapter.setBooks(new ArrayList<>(Arrays.asList(new Gson().fromJson(response, Book[].class))));
                } else {
                    suggestedBookAdapter.addBooks(new ArrayList<>(Arrays.asList(new Gson().fromJson(response, Book[].class))));
                }

                GlobalVars.bookArrayList = (ArrayList<Book>) suggestedBookAdapter.getBooks();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(ANError anError) {
                Log.d(TAG, "onError: ");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public static DashboardFragment newInstance(int someInt, String someTitle) {
        DashboardFragment dashboardFragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someTitle", someTitle);
        dashboardFragment.setArguments(args);
        return dashboardFragment;
    }

}
