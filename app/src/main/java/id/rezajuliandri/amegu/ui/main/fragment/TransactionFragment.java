package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.rezajuliandri.amegu.R;
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