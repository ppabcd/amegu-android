package id.rezajuliandri.amegu.ui.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.adapter.PetAdapter;
import id.rezajuliandri.amegu.entity.Pet;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.ui.main.abstraction.ItemDetailAbstract;

public class HomeFragment extends ItemDetailAbstract {
    View view;
    RecyclerView recyclerView;
    PetAdapter petAdapter;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ActionBarHelper.searchLayoutHandler(view, this);
        // Declare view
        recyclerView = view.findViewById(R.id.rv_main_content);

        // Recyclerview settings
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        petAdapter = new PetAdapter(getContext(), requireActivity().getApplication(), this, view);
        recyclerView.setAdapter(petAdapter);

        // Just dummy data
        // TODO Delete this code after connected to API
        List<Pet> pets = new ArrayList<>();
        String[] rasKucing = {"Shorthair", "Abyssinian", "Balinese", "Bengal", "Birman", "British Shorthair", "Burmese", "Burmilla"};
        for (String s : rasKucing) {
            Pet pet = new Pet("Kucing " + s);
            pets.add(pet);
        }
        petAdapter.setData(pets);
        // END Dummy

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = view.findViewById(R.id.search_box);
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);
    }

    @Override
    public void moveToDetailPet(View view, Pet pet) {
        HomeFragmentDirections.ActionNavigationHomeToPetDetailFragment toPetDetailFragment = HomeFragmentDirections.actionNavigationHomeToPetDetailFragment(pet);
        toPetDetailFragment.setPet(pet);
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }

}