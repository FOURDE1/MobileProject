package com.example.deliveryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private TextView textUpcomingOrders, textPastOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Set up the toolbar
        textUpcomingOrders = view.findViewById(R.id.text_upcoming_orders);
        textPastOrders = view.findViewById(R.id.text_past_orders);

        textUpcomingOrders.setOnClickListener(v -> selectUpcomingOrders());
        textPastOrders.setOnClickListener(v -> selectPastOrders());

        recyclerView = view.findViewById(R.id.recycler_view_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderList = new ArrayList<>();

        // Add sample orders (for demonstration)
        addSampleOrders();

        adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);

        // Initially select Upcoming Orders
        selectUpcomingOrders();

        return view;
    }

    private void addSampleOrders() {
        // Generate some sample orders
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int orderId = i;
            String orderName = "Order " + orderId;
            double orderTotal = random.nextDouble() * 100; // Random total amount

            orderList.add(new Order(orderId, orderName, orderTotal));
        }
    }

    private void selectUpcomingOrders() {
        textUpcomingOrders.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        textPastOrders.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // Filter and display upcoming orders
        List<Order> upcomingOrders = filterUpcomingOrders(orderList);
        adapter.setOrderList(upcomingOrders);
    }

    private void selectPastOrders() {
        textUpcomingOrders.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        textPastOrders.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        // Filter and display past orders
        List<Order> pastOrders = filterPastOrders(orderList);
        adapter.setOrderList(pastOrders);
    }

    private List<Order> filterUpcomingOrders(List<Order> orders) {
        List<Order> upcomingOrders = new ArrayList<>();
        // Assuming upcoming orders are orders with even ids (for demonstration)
        for (Order order : orders) {
            if (order.getId() % 2 == 0) {
                upcomingOrders.add(order);
            }
        }
        return upcomingOrders;
    }

    private List<Order> filterPastOrders(List<Order> orders) {
        List<Order> pastOrders = new ArrayList<>();
        // Assuming past orders are orders with odd ids (for demonstration)
        for (Order order : orders) {
            if (order.getId() % 2 != 0) {
                pastOrders.add(order);
            }
        }
        return pastOrders;
    }
}
