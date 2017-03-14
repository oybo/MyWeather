package app.android.oyb.com.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.ui.BaseFragment;

/**
 * Created by O on 2017/3/14.
 */

public class PrettyPicturesFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pretty_pictures, container, false);
    }
}
