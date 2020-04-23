package com.hanseltritama.notesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class AlertDialogFragment extends Fragment {

    public ArrayList<String> mList;
    public SharedPreferences sharedPreferences;
    public FragmentManager fragmentManager;
    public MainAdapter mainAdapter;
    public RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alert_dialog_fragment, container, false);

        sharedPreferences = getContext().getSharedPreferences("com.hanseltritama.notesapp", MODE_PRIVATE);
        fragmentManager = getFragmentManager();
        mRecyclerView = getActivity().findViewById(R.id.my_recycler_view);

        try {
           mList  = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("notes", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Bundle bundle = this.getArguments();
        final Button submit_button = rootView.findViewById(R.id.submit_button);
        final Button cancel_button = rootView.findViewById(R.id.cancel_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int array_position = bundle.getInt("ARRAY_POSITION");
                mList.remove(array_position);
                updateSharedPreferences(mList);

                mainAdapter = new MainAdapter(mList);
                mainAdapter.notifyItemRemoved(array_position);
                mainAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mainAdapter);

                bundle.remove("ARRAY_POSITION");
                fragmentManager.beginTransaction()
                        .remove(AlertDialogFragment.this)
                        .commit();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.remove("ARRAY_POSITION");
                fragmentManager.beginTransaction()
                        .remove(AlertDialogFragment.this)
                        .commit();
            }
        });

        return rootView;
    }

    public void updateSharedPreferences(ArrayList<String> mList) {
        try {
            sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(mList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
