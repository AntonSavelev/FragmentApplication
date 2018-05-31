package com.example.fragmentapplication.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentapplication.R;
import com.example.fragmentapplication.managers.App;
import com.example.fragmentapplication.model.AppDatabase;
import com.example.fragmentapplication.model.Employee;
import com.example.fragmentapplication.model.EmployeeDao;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int size = Integer.parseInt(sp.getString("list1", "14"));
        String color = sp.getString("list2", "#000000");

        Bundle bundle = getArguments();
        if (bundle != null) {
            int id = bundle.getInt("key");

            AppDatabase db = App.getInstance().getDatabase();
            EmployeeDao employeeDao = db.employeeDao();
            Employee employee = employeeDao.getById(id);

            TextView tvId = getActivity().findViewById(R.id.id);
            TextView tvName = getActivity().findViewById(R.id.name);
            TextView tvAge = getActivity().findViewById(R.id.age);
            TextView tvPosition = getActivity().findViewById(R.id.position);

            if (employee != null) {
                tvId.setText("Id: " + Integer.toString(employee.getId()));
                tvId.setTextSize(size);
                tvId.setTextColor(Color.parseColor(color));
                tvName.setText("Name: " + employee.getName());
                tvName.setTextSize(size);
                tvName.setTextColor(Color.parseColor(color));
                tvAge.setText("Age: " + Integer.toString(employee.getAge()));
                tvAge.setTextSize(size);
                tvAge.setTextColor(Color.parseColor(color));
                tvPosition.setText("Position: " + employee.getPosition());
                tvPosition.setTextSize(size);
                tvPosition.setTextColor(Color.parseColor(color));
            }
        }

    }
}
