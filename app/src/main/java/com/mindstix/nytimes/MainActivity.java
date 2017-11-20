package com.mindstix.nytimes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mindstix.nytimes.books.BookDetailsFragment;
import com.mindstix.nytimes.books.BooksListFragment;
import com.mindstix.nytimes.home.HomeFragment;
import com.mindstix.nytimes.search.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener, Communicator {

    public static final String API_KEY = "2b2e412d87c548138a47b1fc84371992";
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationBar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_search_button)
    ImageButton toolbarSearchButton;
    @BindView(R.id.toolbar_back_button)
    ImageButton toolbarBackButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        bottomNavigationBar.setOnNavigationItemSelectedListener(this);

        startFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_home:
                startFragment(new HomeFragment());
                setToolbarTitle(getString(R.string.menu_item_home));
                break;
            case R.id.menu_books:
                startFragment(new BooksListFragment());
                setToolbarTitle(getString(R.string.menu_item_books));
                break;
        }
        return true;
    }

    @Override
    public void startFragment(@NonNull Fragment fragment) {
        Log.v(TAG, "In startFragment(): " + fragment.getClass().getSimpleName());
        if (isFinishing()) {
            return;
        }

        if (isFragmentAlreadyAdded(fragment.getClass().getSimpleName())) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String fragmentTag = fragment.getClass().getSimpleName();
        fragmentTransaction.replace(R.id.container, fragment, fragmentTag);
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean isFragmentAlreadyAdded(@NonNull String fragmentTag) {
        return getFragmentManager().findFragmentByTag(fragmentTag) != null;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "In onBackPressed()");

        final FragmentManager fragmentManager = getSupportFragmentManager();

        if ((fragmentManager.getBackStackEntryCount() - 1) <= 0) {
            finish();
            return;
        }

        int currentBackStackIndex = fragmentManager.getBackStackEntryCount() - 1;

        if (fragmentManager.getBackStackEntryAt(currentBackStackIndex) != null) {
            String currentTag = fragmentManager.getBackStackEntryAt(currentBackStackIndex)
                    .getName();

            Log.d(TAG, " Current Fragment Tag - " + currentTag);

            if (currentTag.equalsIgnoreCase(SearchFragment.class.getSimpleName())) {
                fragmentManager.popBackStack();

                if (currentBackStackIndex - 1 >= 0) {
                    String previousTag = fragmentManager.getBackStackEntryAt
                            (currentBackStackIndex - 1).getName();
                    Log.e("Previous Tag", "tag " + previousTag);

                    if (previousTag.equalsIgnoreCase(HomeFragment.class.getSimpleName())) {
                        setToolbarTitle(getResources().getString(R.string.menu_item_home));
                    } else if (previousTag.equalsIgnoreCase(BooksListFragment.class.getSimpleName
                            ())) {
                        setToolbarTitle(getResources().getString(R.string.menu_item_books));
                    }
                }
                toggleNavigationBar(View.VISIBLE);
                toolbarSearchButton.setVisibility(View.VISIBLE);
                toolbarBackButton.setVisibility(View.INVISIBLE);
                return;
            }

            if (currentTag.equalsIgnoreCase(HomeFragment.class.getSimpleName()) || currentTag
                    .equalsIgnoreCase(BooksListFragment.class.getSimpleName())) {
                finish();
                return;
            }

            if (currentTag.equalsIgnoreCase(BookDetailsFragment.class.getSimpleName())) {
                fragmentManager.popBackStack();
                setToolbarTitle(getResources().getString(R.string.menu_item_books));
                toggleNavigationBar(View.VISIBLE);
                toolbarSearchButton.setVisibility(View.VISIBLE);
                toolbarBackButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void showProgressDialog(@NonNull String message) {
        Log.v(TAG, "In showProgressDialog()");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        Log.v(TAG, "In hideProgressDialog()");

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setToolbarTitle(@NonNull String title) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }

    @Override
    public void toggleNavigationBar(int visibility) {
        bottomNavigationBar.setVisibility(visibility);
    }

    @Override
    public void displayDetailsActionBar() {
        toolbarSearchButton.setVisibility(View.INVISIBLE);
        toolbarBackButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @OnClick({R.id.toolbar_search_button, R.id.toolbar_back_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_search_button:
                startFragment(SearchFragment.getInstance());
                break;
            case R.id.toolbar_back_button:
                onBackPressed();
                break;
        }

    }
}
