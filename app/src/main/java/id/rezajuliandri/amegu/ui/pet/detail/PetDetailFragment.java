package id.rezajuliandri.amegu.ui.pet.detail;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetDetailBinding;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.ItemDetailAbstract;
import id.rezajuliandri.amegu.utils.StringHelper;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class PetDetailFragment extends ItemDetailAbstract {
    FragmentPetDetailBinding binding;
    ActionBarHelper actionBarHelper;
    PetDetailViewModel viewModel;
    long petId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPetDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBar();
        setViewModel();
        getData();
    }

    private void getData() {
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
                            binding.txtJudulNama.setText(pet.getNamaHewan());
                            binding.txtHarga.setText(StringHelper.currency(pet.getHarga()));
                            binding.namaPemilik.setText(pet.getFullName());
                            binding.lokasiPemilik.setText(pet.getLokasi());
                            binding.txtJenis.setText(pet.getRas());
                            binding.txtUsia.setText(String.valueOf(pet.getUsia()));
                            binding.txtBerat.setText(String.valueOf(pet.getBeratBadan()));
                            binding.txtJenisKelamin.setText(pet.getJenisKelamin());
                            binding.txtDeskripsi.setText(pet.getDeskripsi());

                            if (pet.getIsFavorite() == 0) {
                                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                            } else {
                                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                            }

                            binding.btnLike.setOnClickListener(v -> {
                                int favorite = (pet.getIsFavorite() == 1) ? 0 : 1;
                                viewModel.updateFavoritePet(favorite, petId);
                                if (favorite == 0) {
                                    binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                } else {
                                    binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                                }
                            });

                            Glide.with(requireContext())
                                    .load(pet.getAttachmentUrl())
                                    .apply(new RequestOptions())
                                    .placeholder(R.drawable.anjing)
                                    .into(binding.imageView);
                            viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                                if (userEntity != null) {
                                    binding.btnAdopt.setVisibility(View.VISIBLE);
                                    binding.btnEdit.setVisibility(View.GONE);
                                    binding.btnDelete.setVisibility(View.GONE);
                                    if (userEntity.getId() == pet.getUserId()) {
                                        binding.btnAdopt.setVisibility(View.GONE);
                                        binding.btnEdit.setVisibility(View.VISIBLE);
                                        binding.btnDelete.setVisibility(View.VISIBLE);

                                        binding.btnEdit.setOnClickListener(v -> {
                                            PetDetailFragmentDirections.ActionPetDetailFragmentToPetUpdateFragment toPetUpdateFragment = PetDetailFragmentDirections.actionPetDetailFragmentToPetUpdateFragment(pet.getId());
                                            Navigation.findNavController(binding.getRoot()).navigate(toPetUpdateFragment);
                                        });
                                        binding.btnDelete.setOnClickListener(v -> {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                            builder.setTitle("Apakah anda yakin ingin menghapus hewan ini?");
                                            builder.setMessage("Data hewan yang dihapus tidak bisa dikembalikan lagi");
                                            builder.setNegativeButton("Batal", (dialog, which) -> {

                                            });
                                            builder.setPositiveButton("Ok", ((dialog, which) -> viewModel.deleteHewan(pet.getId(), userEntity.getToken()).observe(getViewLifecycleOwner(), status -> {
                                                if (status.toLowerCase().equals("ok")) {
                                                    Toast.makeText(requireContext(), "Berhasil menghapus data hewan", Toast.LENGTH_SHORT).show();
                                                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_petDetailFragment_to_petsUserFragment);
                                                    return;
                                                }
                                                Toast.makeText(getContext(), "Gagal menghapus hewan", Toast.LENGTH_SHORT).show();
                                            })));
                                            builder.show();
                                        });

                                    }
                                    binding.btnAdopt.setOnClickListener(v -> {
                                        if (userEntity.getBankAccountId() == 0) {
                                            Toast.makeText(requireContext(), "User harus menambahkan rekening sebelum melakukan adopsi", Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_petDetailFragment_to_bankAccountFragment);
                                            return;
                                        }
                                        viewModel.adopt(pet.getId(), userEntity.getToken()).observe(getViewLifecycleOwner(), status -> {
                                            if (status.toLowerCase().equals("ok")) {
                                                PetDetailFragmentDirections.ActionPetDetailFragmentToPetAdoptionFragment toPetAdoptionFragment = PetDetailFragmentDirections.actionPetDetailFragmentToPetAdoptionFragment(petId);
                                                toPetAdoptionFragment.setPetId(pet.getId());
                                                Navigation.findNavController(binding.getRoot()).navigate(toPetAdoptionFragment);
                                            }
                                        });
                                    });
                                }
                            });
                        }

                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        // Share hewan
        binding.btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = "Yuk adopsi Hewan ini, gemes banget dan pastinya bisa nemenin kamu. Yuk cek di https://amegu.netlify.app/pet/" +
                    petId;
            String shareSubject = "Bagikan hewan ini";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share using"));
        });
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(PetDetailViewModel.class);
    }

    private void setActionBar() {
        actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_petDetailFragment_to_searchFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {

    }
}