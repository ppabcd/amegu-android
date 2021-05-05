package id.rezajuliandri.amegu.ui.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import id.rezajuliandri.amegu.databinding.FragmentTransactionBinding;

public class TransactionFragment extends Fragment {

    FragmentTransactionBinding transactionBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        transactionBinding = FragmentTransactionBinding.inflate(getLayoutInflater());
        return transactionBinding.getRoot();
    }
}