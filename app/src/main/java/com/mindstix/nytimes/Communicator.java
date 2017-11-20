package com.mindstix.nytimes;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface Communicator {
    /**
     * Start the fragment
     *
     * @param fragment The fragment that we want to start.
     */
    public void startFragment(@NonNull Fragment fragment);

    /**
     * Checks if the fragment is already added.
     *
     * @param fragmentTag Fragment tag that to check.
     * @return {@code true} Return true if the fragment is already added.
     * {@code false} Returns false if the fragment is not added earlier.
     */
    boolean isFragmentAlreadyAdded(@NonNull final String fragmentTag);

    /**
     * Displays the progress dialog with a loading indicator.
     *
     * @param message Message to be displayed on the progress dialog.
     */
    void showProgressDialog(@NonNull String message);

    /**
     * Hides the progress dialog.
     */
    void hideProgressDialog();

    /**
     * Sets the toolbar title.
     *
     * @param toolbarTitle Title to be displayed.
     */
    void setToolbarTitle(@NonNull String toolbarTitle);

    void toggleNavigationBar(int visibility);

    void displayDetailsActionBar();
}
