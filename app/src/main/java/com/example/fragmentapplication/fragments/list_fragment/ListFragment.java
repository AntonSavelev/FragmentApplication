package com.example.fragmentapplication.fragments.list_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentapplication.R;
import com.example.fragmentapplication.fragments.DetailFragment;
import com.example.fragmentapplication.managers.App;
import com.example.fragmentapplication.model.AppDatabase;
import com.example.fragmentapplication.model.Employee;
import com.example.fragmentapplication.model.EmployeeDao;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private List<Employee> employees;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        employees = new ArrayList<>();

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        employees = employeeDao.getAll();
        if (employees.size() == 0) {
            addEmployees();
            employeeDao.insert(employees);
            employees = employeeDao.getAll();
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ListFragmentAdapter adapter = new ListFragmentAdapter();
        adapter.setData(employees);
        adapter.setListener(new ListFragmentAdapter.Listener() {
            @Override
            public void onClick(int id) {
                openDetailInformation(id);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void openDetailInformation(int id) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", id);
        detailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, detailFragment).commit();
    }

    public void addEmployees() {
        Employee employee1 = new Employee("Andrey", 35, "director");
        Employee employee2 = new Employee("Dmitriy", 31, "developer");
        Employee employee3 = new Employee("Andrey", 32, "developer");
        Employee employee4 = new Employee("Tom", 24, "developer");
        Employee employee5 = new Employee("Alex", 27, "developer");
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
    }
}
