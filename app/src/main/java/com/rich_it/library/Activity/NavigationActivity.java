package com.rich_it.library.Activity;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.rich_it.library.Abstract.DrawerItem;
import com.rich_it.library.Adapter.DrawerAdapter;
import com.rich_it.library.Fragment.AboutFragment;
import com.rich_it.library.Fragment.DashboardFragment;
import com.rich_it.library.Fragment.NearbyBookFragment;
import com.rich_it.library.Fragment.ProfileFragment;
import com.rich_it.library.Fragment.SettingFragment;
import com.rich_it.library.Model.Book;
import com.rich_it.library.Model.SimpleItem;
import com.rich_it.library.Model.SpaceItem;
import com.rich_it.library.R;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class NavigationActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_BOOK = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_ABOUT_US = 5;
    private static final int POS_lOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;

    Toolbar toolbar;
    ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);
        createList();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(200)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();;
        screenTitles = loadScreenTitles();
        DrawerAdapter drawerAdapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setCheck(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_BOOK),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(60),
                createItemFor(POS_lOGOUT)
        ));
        drawerAdapter.setListener(this);
        RecyclerView recyclerView = findViewById(R.id.drawer_list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(drawerAdapter);
        drawerAdapter.setSelected(POS_DASHBOARD);
    }

    private void createList() {
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
        books.add(new Book("Islami", "Author", "Publication", "300", "3", R.drawable.amu_bubble_mask));
    }

    public ArrayList<Book> getBooks() {

        return books;
        // do anything with response
        // handle error
    }

    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withNormalItemIconTint(color(R.color.black))
                .withNormalItemTextTint(color(R.color.black))
                .withSelectedItemIconTint(color(R.color.purple_500))
                .withSelectedItemTextTint(color(R.color.purple_500));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(NavigationActivity.this, res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_ActivityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.id_ActivityScreenIcon);
        Drawable[] icons = new Drawable[typedArray.length()];
        for (int i=0; i<typedArray.length(); i++){
            int id = typedArray.getResourceId(i, 0);
            if(id!=0){
                icons[i] = ContextCompat.getDrawable(NavigationActivity.this, id);
            }
        }
        typedArray.recycle();
        return icons;
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(position == POS_DASHBOARD){
            DashboardFragment dashboardFragment = DashboardFragment.newInstance(7, "hello");
            transaction.replace(R.id.container, dashboardFragment);
        }else if(position == POS_ABOUT_US){
            AboutFragment aboutFragment = new AboutFragment();
            transaction.replace(R.id.container, aboutFragment);
        }else if(position == POS_MY_PROFILE){
            ProfileFragment profileFragment = new ProfileFragment();
            transaction.replace(R.id.container, profileFragment);
        }else if(position == POS_SETTINGS){
            SettingFragment settingFragment = new SettingFragment();
            transaction.replace(R.id.container, settingFragment);
        }else if(position == POS_NEARBY_BOOK){
            NearbyBookFragment nearbyBookFragment = new NearbyBookFragment();
            transaction.replace(R.id.container, nearbyBookFragment);
        }else if(position == POS_lOGOUT){
            finish();
        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}