package com.rich_it.library.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
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
import androidx.lifecycle.ViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.rich_it.library.Activity.GoogleMapActivity;
import com.rich_it.library.Activity.NavigationActivity;
import com.rich_it.library.Adapter.NearbyBookAdapter;
import com.rich_it.library.Adapter.SuggestedBookAdapter;
import com.rich_it.library.Model.Book;
import com.rich_it.library.ServerCalling.OtherServerCaling;
import com.rich_it.library.R;
import com.rich_it.library.ViewModel.BookViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {
    private static final String TAG = DashboardFragment.class.getName();
    NearbyBookAdapter nearbyBookAdapter;
    RecyclerView nearbyBookRecyclerView;
    SuggestedBookAdapter suggestedBookAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    FragmentActivity listener;
    Activity activity;
    ProgressBar pb;
    private BookViewModel viewModel;
    ArrayList<Book> myBooks = new ArrayList<>() ;
    boolean isLoading = false;
    private int load = 1;
    ScrollView scrollView;
    Button getLocationButton;

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
        int SomeInt = getArguments().getInt("someInt", 0);
        String someTitle = getArguments().getString("someTitle", "");
        Log.d(TAG, "onCreate: " + someTitle + SomeInt);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        BookViewModel.page = 0;
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);
        return root;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getView() {
        return super.getView();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pb = view.findViewById(R.id.dashpbLoading);
        pb.setVisibility(ProgressBar.VISIBLE);
        scrollView = view.findViewById(R.id.parent_scroll);
        activity =  getActivity();
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

        recyclerView = view.findViewById(R.id.suggested_book_rv_f);
        suggestedBookAdapter = new SuggestedBookAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(suggestedBookAdapter);


        viewModel = ViewModelProviders.of(listener).get(BookViewModel.class);
        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            // update UI
            myBooks.addAll(books);
            nearbyBookAdapter.setBooks(myBooks);
            suggestedBookAdapter.setBooks( myBooks);
            pb.setVisibility(ProgressBar.GONE);
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int ydy = 0;
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Toast.makeText(listener, "changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int firstVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition();

                int FirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                int LastCompletelyVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                int diff= LastCompletelyVisibleItemPosition - FirstCompletelyVisibleItemPosition;
                int curr = linearLayoutManager.findLastVisibleItemPosition();

                int total = linearLayoutManager.getItemCount();
                float y = recyclerView.getY() + recyclerView.getChildAt(LastCompletelyVisibleItemPosition).getY();
                Toast.makeText(listener, Integer.toString((int)y), Toast.LENGTH_SHORT).show();

//                scrollView.smoothScrollTo(0, (int) y);
                if(true){
                    pb.setVisibility(View.VISIBLE);
                    viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
                        // update UI
                        if (books.size()==0)
                            Toast.makeText(getActivity(), "All books are loaded", Toast.LENGTH_SHORT).show();
                        myBooks.addAll(books);
                        suggestedBookAdapter.setBooks( myBooks);
                        nearbyBookAdapter.setBooks(myBooks);
                        pb.setVisibility(ProgressBar.GONE);
                        load++;
                    });
                }
                int lastVisibleItemCount = linearLayoutManager.findLastVisibleItemPosition();
//                if(4*load > total-4){
//                    pb.setVisibility(View.VISIBLE);
//                    viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
//                        // update UI
//                        if (books.size()==0)
//                            Toast.makeText(getActivity(), "All books are loaded", Toast.LENGTH_SHORT).show();
//                        myBooks.addAll(books);
//                        suggestedBookAdapter.setBooks( myBooks);
//                        nearbyBookAdapter.setBooks(myBooks);
//                        pb.setVisibility(ProgressBar.GONE);
//                        load++;
//                    });
//                }
//                Toast.makeText(listener, Integer.toString(diff), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "total: " + total );
//
//                if (!isLoading) {
//                    isLoading = true;
//                    if (total > 0){
//                        if ((total - 1) == lastVisibleItemCount){
//                            pb.setVisibility(View.VISIBLE);
//                            viewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
//                                // update UI
//                                if (books.size()==0)
//                                    Toast.makeText(getActivity(), "All books are loaded", Toast.LENGTH_SHORT).show();
//                                myBooks.addAll(books);
//                                suggestedBookAdapter.setBooks( myBooks);
//                                nearbyBookAdapter.setBooks(myBooks);
//                                isLoading = false;
//                                pb.setVisibility(ProgressBar.GONE);
//                            });
//                        }else{
//                            pb.setVisibility(View.GONE);
//                        }
//                    }
//                }
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

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }



    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (NavigationActivity) getActivity();
        ArrayList<Book> books = ((NavigationActivity) activity).getBooks();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);
    }
}
