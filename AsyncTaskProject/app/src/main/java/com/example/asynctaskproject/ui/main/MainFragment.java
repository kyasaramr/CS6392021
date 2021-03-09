package com.example.asynctaskproject.ui.main;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asynctaskproject.ImageDownloader;
import com.example.asynctaskproject.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance()
    {
        return new MainFragment();
    }

    String web_link = "https://static0.cbrimages.com/wordpress/wp-content/uploads/2020/09/roronoa-zoro.jpg";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);

        ImageView remote_image = (ImageView) root.findViewById(R.id.remote_image);

        ImageDownloader async_task = new ImageDownloader(this.getActivity());
        async_task.execute(web_link);

        return root;
    }



}