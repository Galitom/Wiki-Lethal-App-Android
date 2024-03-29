package com.example.lethalcompany.ui.store;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lethalcompany.R;
import com.example.lethalcompany.ui.store.MyItemRecyclerViewAdapter;
import com.example.lethalcompany.ui.store.placeholder.PlaceholderContent;
import com.example.lethalcompany.ui.store.placeholder.StoreItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class StoreFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoreFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StoreFragment newInstance(int columnCount) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        ArrayList<StoreItem> listaStoreItem = new ArrayList<>();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(listaStoreItem);
            recyclerView.setAdapter(adapter);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Store")
                    .get()
                    .addOnCompleteListener(task -> {

                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                listaStoreItem.add(document.toObject(StoreItem.class));
                            }
                            getActivity().runOnUiThread(adapter::notifyDataSetChanged);
                        }else{
                            Log.w("fetch_storeItem","Error getting documents.", task.getException());
                        }

                    });
        }
        return view;
    }
}