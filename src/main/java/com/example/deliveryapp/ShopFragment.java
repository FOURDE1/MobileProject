package com.example.deliveryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the product list manually
        initializeProductList();

        return view;
    }

    private void initializeProductList() {
        productList = new ArrayList<>();

        // Manually add products to the list
        productList.add(new Product(1, "Product 1", 10.99));
        productList.add(new Product(2, "Product 2", 15.99));
        productList.add(new Product(3, "Product 3", 20.99));
        productList.add(new Product(4, "Product 4", 25.99));
        productList.add(new Product(5, "Product 5", 30.99));

        // Set the adapter with the product list
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }
}
