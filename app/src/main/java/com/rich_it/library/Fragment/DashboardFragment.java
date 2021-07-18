package com.rich_it.library.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.rich_it.library.Activity.GoogleMapActivity;
import com.rich_it.library.Activity.NavigationActivity;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.R;
import com.rich_it.library.ServerCalling.BookServerCalling;
import com.rich_it.library.ViewModel.BookViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardFragment extends Fragment {

    private static final String TAG = DashboardFragment.class.getName();

    NearbyBookAdapter nearbyBookAdapter;
    SuggestedBookAdapter suggestedBookAdapter;

    RecyclerView nearbyBookRecyclerView;
    RecyclerView recyclerViewSuggestedBooks;

    LinearLayoutManager linearLayoutManager;

    Activity activity;

    ScrollView scrollView;
    ProgressBar pb;
    Button getLocationButton;

    ArrayList<Book> bookArrayList = new ArrayList<>();
    int pageNumber = 1;
    int itemsPerPage = 5;

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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pb = view.findViewById(R.id.dashpbLoading);
        scrollView = view.findViewById(R.id.parent_scroll);
        activity = getActivity();
        getLocationButton = view.findViewById(R.id.getLocationButton_f);
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
                startActivity(intent);
            }
        });

        nearbyBookRecyclerView = view.findViewById(R.id.nearby_book_rv_f);
        nearbyBookAdapter = new NearbyBookAdapter(getActivity());
        nearbyBookRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        nearbyBookRecyclerView.setNestedScrollingEnabled(false);
        nearbyBookRecyclerView.setAdapter(nearbyBookAdapter);

        recyclerViewSuggestedBooks = view.findViewById(R.id.suggested_book_rv_f);
        suggestedBookAdapter = new SuggestedBookAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewSuggestedBooks.setLayoutManager(linearLayoutManager);
        recyclerViewSuggestedBooks.setNestedScrollingEnabled(false);
        recyclerViewSuggestedBooks.setAdapter(suggestedBookAdapter);

        recyclerViewSuggestedBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d(TAG, "onScrolled: ");
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d(TAG, "onScrollStateChanged: ");

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int pntbs = totalItemCount / itemsPerPage;

                if (pntbs != pageNumber) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= itemsPerPage) {

                        pageNumber = pntbs;
                        getBooks();
                    }
                }
            }
        });

        getBooks();
    }

    private void getBooks() {
        pb.setVisibility(View.VISIBLE);
        BookServerCalling.getBooks(pageNumber, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Page: " + pageNumber);

                Book[] books = new Gson().fromJson(response, Book[].class);
                bookArrayList = new ArrayList<>(Arrays.asList(books));
                if (pageNumber == 1) {
                    suggestedBookAdapter.setBooks(bookArrayList);
                } else {
                    suggestedBookAdapter.addBooks(bookArrayList);
                }

                pb.setVisibility(View.GONE);
            }

            @Override
            public void onError(ANError anError) {
                pb.setVisibility(View.GONE);
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
