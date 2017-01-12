package com.isobar.sample.architecturepatterns.mvc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isobar.sample.architecturepatterns.R;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */
public class FragmentFormMvc extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_form, container, false);
    }
}
