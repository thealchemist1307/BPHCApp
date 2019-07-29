package com.crux.bphcfreshers;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CInfoFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cinfo, null);

//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button Bhistory = view.findViewById(R.id.button5);
        Button Bc_d = view.findViewById(R.id.button6);
        Button Bnearby = view.findViewById(R.id.button7);
        Bhistory.setOnClickListener(this);
        Bc_d.setOnClickListener(this);
        Bnearby.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                openHistory();
                break;
            case R.id.button6:
               openClub();
                break;
            case R.id.button7:
               openFests();
                break;
        }
    }

    public void openHistory() {
        Intent intent = new Intent(CInfoFragment.this.getActivity(), History.class);
        startActivity(intent);


    }
    public void openClub() {
        Intent intent = new Intent(CInfoFragment.this.getActivity(), clubdep.class);
        startActivity(intent);


    }

    public void openFests() {
        Intent intent = new Intent(CInfoFragment.this.getActivity(), Fests.class);
        startActivity(intent);


    }


}