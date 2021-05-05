package id.rezajuliandri.amegu.ui.pet_detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.entity.pet.Favorite;
import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.databinding.FragmentPetDetailBinding;
import id.rezajuliandri.amegu.helpers.ActionBarHelper;
import id.rezajuliandri.amegu.helpers.StringHelper;
import id.rezajuliandri.amegu.interfaces.pet.OnFavorite;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class PetDetailFragment extends Fragment {
    ActionBarHelper actionBarHelper;
    FragmentPetDetailBinding binding;

    PetDetailViewModel petDetailViewModel;


    public PetDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPetDetailBinding.inflate(getLayoutInflater());
        actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // View Model
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity().getApplication());
        petDetailViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(PetDetailViewModel.class);

        // Get data pet
        Pet pet = PetDetailFragmentArgs.fromBundle(getArguments()).getPet();

        binding.txtJudulNama.setText(pet.getNamaHewan());
        binding.txtHarga.setText(StringHelper.currency(pet.getHarga()));

        String pemilik = pet.getUser().getFirstName() + " " + ((pet.getUser().getLastName() == null) ? pet.getUser().getLastName() : "");
        binding.namaPemilik.setText(pemilik);
        binding.lokasiPemilik.setText(pet.getUser().getAlamat().getProvinsiName());

        binding.txtJenis.setText(pet.getRas().getRas());
        binding.txtUsia.setText(String.valueOf(pet.getUsia()));
        binding.txtKondisi.setText(pet.getKondisi());
        binding.txtBerat.setText(String.valueOf(pet.getBeratBadan()));
        binding.txtJenisKelamin.setText(StringHelper.firstUpper(pet.getJenisKelamin()));
        binding.txtDeskripsi.setText(pet.getDeskripsi());

        // Get image from server
        Glide.with(requireContext())
                .load(pet.getAttachment().getUrl())
                .apply(new RequestOptions())
                .placeholder(R.drawable.anjing)
                .into(binding.imageView);

        Favorite favorite = new Favorite(pet.getId());
        Log.i("FAVORITE_OUTSIDE", Long.toString(favorite.getPetId()));
        final boolean[] isFavorite = {false};
        petDetailViewModel.getFavorite(new OnFavorite() {
            @Override
            public void success(Favorite favorite) {
                if(favorite == null){
                    Log.i("Favorite", "NULL");
                    binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    return;
                }
                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                isFavorite[0] = true;
                Log.i("Favorite", favorite.toString());
            }

            @Override
            public void error(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        }, favorite);

        binding.btnLike.setOnClickListener(v -> {
            petDetailViewModel.insertOrDelete(favorite);
            if(isFavorite[0]){
                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                isFavorite[0] = false;
                return;
            }
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
            isFavorite[0] = true;
        });

        // Adopsi hewan
        binding.btnAdopt.setOnClickListener(v -> {
            PetDetailFragmentDirections.ActionPetDetailFragmentToAdopsiFragment toAdopsiFragment = PetDetailFragmentDirections.actionPetDetailFragmentToAdopsiFragment(pet);
            Navigation.findNavController(binding.getRoot()).navigate(toAdopsiFragment);
        });

        // Share hewan
        binding.btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = "Yuk adopsi " +
                    pet.getRas().getJenis().getJenis() +
                    " ini, gemes banget dan pastinya bisa nemenin kamu. Yuk cek di https://amegu.netlify.app/pet/" +
                    pet.getId();
            String shareSubject = "Bagikan hewan ini";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share using"));
        });
    }
}