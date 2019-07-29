package com.crux.bphcfreshers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TimeTableFragment extends Fragment {

    private ListView timetableList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timetable, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timetableList = view.findViewById(R.id.timetableList);
        String[] title = getResources().getStringArray(R.array.titleList);
        String[] description = getResources().getStringArray(R.array.descriptionList);

        ListAdapter listAdapter = new ListAdapter(getContext(), title, description);
        timetableList.setAdapter(listAdapter);

        timetableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(getContext(), timetableView.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(getContext(), Subject.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

    }

    public class ListAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray, descArray;
        private ImageView imageView;

        public ListAdapter (Context context, String[] titleArr, String[] descArr) {

            titleArray = titleArr;
            descArray = descArr;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.fragment_timetable_card, null);
            }

            title = convertView.findViewById(R.id.cardTitle);
            description = convertView.findViewById(R.id.cardDesc);
            imageView = convertView.findViewById(R.id.cardImg);

            title.setText(titleArray[position]);
            description.setText(descArray[position]);

            if (titleArray[position].equalsIgnoreCase("TimeTable")) {
                imageView.setImageResource(R.drawable.timetable);
            } else if (titleArray[position].equalsIgnoreCase("Subjects")) {
                imageView.setImageResource(R.drawable.subjects);
            }

            return convertView;

        }
    }

}
