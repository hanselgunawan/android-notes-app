package com.hanseltritama.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AlertDialogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alert_dialog_fragment, container, false);
        final Button submit_button = rootView.findViewById(R.id.submit_button);
        final Button cancel_button = rootView.findViewById(R.id.cancel_button);
        return rootView;
    }
}
