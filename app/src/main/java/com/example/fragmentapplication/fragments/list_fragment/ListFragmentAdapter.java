package com.example.fragmentapplication.fragments.list_fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentapplication.R;
import com.example.fragmentapplication.model.Employee;

import java.util.Collections;
import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {

    Listener listener;
    private List<Employee> employees;

    public ListFragmentAdapter() {
        employees = Collections.EMPTY_LIST;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public ListFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ListFragmentAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.bind(employee);
        final int id = employee.getId();
        CardView cardView = holder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setData(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public interface Listener {
        void onClick(int id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView age;
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            name = cardView.findViewById(R.id.name);
            age = cardView.findViewById(R.id.age);
        }

        public void bind(Employee employee) {
            name.setText(employee.getName());
            age.setText(Integer.toString(employee.getAge()));
        }
    }

}
