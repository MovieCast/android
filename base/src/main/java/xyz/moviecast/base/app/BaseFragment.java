package xyz.moviecast.base.app;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * The BaseFragment for MovieCast.
 * Use the {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle, int)} method to
 * create an view for this fragment, it will automatically bind ButterKnife with the correct layout
 */
public class BaseFragment extends Fragment {

    public BaseFragment() { }

    /**
     * A custom onCreateView which will automatically inflate the layout
     * and bind with ButterKnife
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @param layoutResID The layout resource id of this fragment.
     *
     * @return Return the View for the fragment's UI, binded with ButterKnife
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState, @LayoutRes int layoutResID) {
        View view = inflater.inflate(layoutResID, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
