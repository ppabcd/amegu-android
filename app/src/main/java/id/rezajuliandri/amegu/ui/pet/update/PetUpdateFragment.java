package id.rezajuliandri.amegu.ui.pet.update;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetUpdateBinding;
import id.rezajuliandri.amegu.ui.pet.detail.PetDetailFragmentArgs;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.utils.StringHelper;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class PetUpdateFragment extends BaseFragment {
    FragmentPetUpdateBinding binding;
    PetUpdateViewModel viewModel;

    private static final int REQUEST_IMAGE = 1;
    private static final int PERMISSION_REQ_INTERNAL_STORAGE = 2;
    public String[] permissionsNeeded;

    ArrayList<JenisEntity> jenisEntities;
    ArrayList<RasEntity> rasEntities;

    long petId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPetUpdateBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        super.onViewCreated(view, savedInstanceState);
    }
    ArrayAdapter<JenisEntity> jenisEntityArrayAdapter;
    ArrayAdapter<RasEntity> rasEntityArrayAdapter;
    private void initData() {
        jenisEntities = new ArrayList<>();
        rasEntities = new ArrayList<>();
        JenisEntity jenisEntity = new JenisEntity(0, "Pilih henis hewan");
        RasEntity rasEntity = new RasEntity(0, "Pilih ras", 0);
        jenisEntities.add(jenisEntity);
        rasEntities.add(rasEntity);

        jenisEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, jenisEntities);
        rasEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, rasEntities);

        binding.jenis.setAdapter(jenisEntityArrayAdapter);
        binding.ras.setAdapter(rasEntityArrayAdapter);
    }

    @Override
    protected void getData() {
        petId = PetDetailFragmentArgs.fromBundle(requireArguments()).getPetId();
        viewModel.getPetDetail(petId).observe(getViewLifecycleOwner(), petEntityResource -> {
            if (petEntityResource != null) {
                switch (petEntityResource.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        PetEntity pet = petEntityResource.data;
                        if (pet != null) {
                            Log.i("PETTT", pet.toString());
                            binding.namaHewan.setText(pet.getNamaHewan());
                            binding.harga.setText(StringHelper.currency(pet.getHarga()));
//                            binding.txtJenis.setText(pet.getRas());
                            binding.usia.setText(String.valueOf(pet.getUsia()));
                            binding.beratBadan.setText(String.valueOf(pet.getBeratBadan()));
//                            binding.jenisKelamin.setText(pet.getJenisKelamin());
                            binding.deskripsi.setText(pet.getDeskripsi());

                            Glide.with(requireContext())
                                    .load(pet.getAttachmentUrl())
                                    .apply(new RequestOptions())
                                    .placeholder(R.drawable.anjing)
                                    .into(binding.imageView);
                        }

                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(PetUpdateViewModel.class);
    }

    @Override
    protected void setActionBar() {

    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {

    }

    @Override
    protected void moveToSearchFragment(View view) {

    }
}