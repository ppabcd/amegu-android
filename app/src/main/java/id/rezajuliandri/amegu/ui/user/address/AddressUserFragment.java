package id.rezajuliandri.amegu.ui.user.address;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Collections;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.databinding.FragmentAddressUserBinding;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;
import id.rezajuliandri.amegu.vo.Resource;

public class AddressUserFragment extends BaseFragment {
    FragmentAddressUserBinding binding;
    AddressUserViewModel viewModel;
    ProvinsiEntity provinsiEntity;
    KelurahanEntity kelurahanEntity;
    KecamatanEntity kecamatanEntity;
    KotaEntity kotaEntity;
    ArrayList<ProvinsiEntity> provinsiEntities;
    ArrayList<KelurahanEntity> kelurahanEntities;
    ArrayList<KecamatanEntity> kecamatanEntities;
    ArrayList<KotaEntity> kotaEntities;
    ArrayAdapter<ProvinsiEntity> provinsiEntityArrayAdapter;
    ArrayAdapter<KotaEntity> kotaEntityArrayAdapter;
    ArrayAdapter<KecamatanEntity> kecamatanEntityArrayAdapter;
    ArrayAdapter<KelurahanEntity> kelurahanEntityArrayAdapter;
    boolean completedCheck = false;
    boolean completedText = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddressUserBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initData() {
        provinsiEntities = new ArrayList<>();
        provinsiEntity = new ProvinsiEntity(0, "Pilih Provinsi");
        provinsiEntities.add(provinsiEntity);
        provinsiEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, provinsiEntities);
        binding.provinsi.setAdapter(provinsiEntityArrayAdapter);

        kotaEntities = new ArrayList<>();
        kotaEntity = new KotaEntity(0, "Pilih Kota", 0);
        kotaEntities.add(kotaEntity);
        kotaEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kotaEntities);
        binding.kota.setAdapter(kotaEntityArrayAdapter);

        kecamatanEntities = new ArrayList<>();
        kecamatanEntity = new KecamatanEntity(0, "Pilih Kecamatan", 0);
        kecamatanEntities.add(kecamatanEntity);
        kecamatanEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kecamatanEntities);
        binding.kecamatan.setAdapter(kecamatanEntityArrayAdapter);

        kelurahanEntities = new ArrayList<>();
        kelurahanEntity = new KelurahanEntity(0, "Pilih Kelurahan", 0);
        kelurahanEntities.add(kelurahanEntity);
        kelurahanEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kelurahanEntities);
        binding.kelurahan.setAdapter(kelurahanEntityArrayAdapter);

        binding.alamat.addTextChangedListener(alamat_listener);
    }

    @Override
    protected void getData() {
        viewModel.getProvinsi().observe(getViewLifecycleOwner(), provinsi -> {
            if (provinsi != null) {
                switch (provinsi.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        if (provinsi.data != null) {
                            provinsiEntities.addAll(provinsi.data);
                            provinsiEntity = new ProvinsiEntity(0, "Pilih Provinsi");
                            provinsiEntities.add(provinsiEntity);
                            provinsiEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, provinsiEntities);
                            binding.provinsi.setAdapter(provinsiEntityArrayAdapter);
                            binding.provinsi.setOnItemSelectedListener(provinsiListener);
                        }
                        break;
                    case ERROR:
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                viewModel.getAlamat(userEntity.getAlamatId(), userEntity.getToken()).observe(getViewLifecycleOwner(), alamatEntityResource -> {
                    switch (alamatEntityResource.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            AlamatEntity alamatEntity = alamatEntityResource.data;
                            if (alamatEntity != null) {
                                binding.txtAlamat.setText(alamatEntity.getAlamat());
                                binding.txtProvinsi.setText(alamatEntity.getProvinsiName());
                                binding.txtKota.setText(alamatEntity.getKotaName());
                                binding.txtKecamatan.setText(alamatEntity.getKecamatanName());
                                binding.txtKelurahan.setText(alamatEntity.getKelurahanName());

                                binding.sendAddress.setOnClickListener(v -> {
                                    try {
                                        binding.sendAddress.setText(R.string.loading);
                                        binding.sendAddress.setEnabled(false);
                                        viewModel.sendAlamat(
                                                binding.alamat.getText().toString(),
                                                ((ProvinsiEntity) binding.provinsi.getSelectedItem()).getId(),
                                                ((KotaEntity) binding.kota.getSelectedItem()).getId(),
                                                ((KecamatanEntity) binding.kecamatan.getSelectedItem()).getId(),
                                                ((KelurahanEntity) binding.kelurahan.getSelectedItem()).getId(),
                                                userEntity.getToken()
                                        ).observe(getViewLifecycleOwner(), status -> {
                                            if(status.toLowerCase().equals("ok")){
                                                viewModel.getProfile(userEntity.getToken()).observe(getViewLifecycleOwner(), this::checkProfile);
                                                return;
                                            }
                                            Toast.makeText(getContext(), "Gagal mengirim alamat", Toast.LENGTH_SHORT).show();
                                            binding.sendAddress.setText(R.string.simpan);
                                            binding.sendAddress.setEnabled(true);
                                        });
                                    } catch (Exception e) {
                                        binding.sendAddress.setText(R.string.simpan);
                                        binding.sendAddress.setEnabled(true);
                                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            break;
                        case ERROR:
                            Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                });
            }
        });
    }

    private void checkProfile(Resource<UserEntity> userEntityResource) {
        if (userEntityResource != null) {
            switch (userEntityResource.status) {
                case LOADING:
                    binding.sendAddress.setText(R.string.loading);
                    binding.sendAddress.setEnabled(false);
                    break;
                case SUCCESS:
                    binding.sendAddress.setText(R.string.simpan);
                    binding.sendAddress.setEnabled(true);
                    Toast.makeText(requireContext(), "Berhasil mengubah alamat", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    binding.sendAddress.setText(R.string.simpan);
                    binding.sendAddress.setEnabled(true);
                    Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private final TextWatcher alamat_listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            completedText = !"".equals(binding.alamat.getText().toString());
            checkButton();
        }
    };

    private void checkButton() {
        if (!completedCheck || !completedText) {
            binding.sendAddress.setEnabled(false);
            return;
        }
        binding.sendAddress.setEnabled(true);
    }

    private final AdapterView.OnItemSelectedListener kelurahanListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                KelurahanEntity kelurahanEntity = (KelurahanEntity) binding.kelurahan.getItemAtPosition(position);
                KecamatanEntity kecamatanEntity = (KecamatanEntity) binding.kecamatan.getSelectedItem();
                KotaEntity kotaEntity = (KotaEntity) binding.kota.getSelectedItem();
                ProvinsiEntity provinsiEntity = (ProvinsiEntity) binding.provinsi.getSelectedItem();
                if (
                        kelurahanEntity != null && kelurahanEntity.getId() != 0 &&
                                kecamatanEntity != null && kecamatanEntity.getId() != 0 &&
                                kotaEntity != null && kotaEntity.getId() != 0 &&
                                provinsiEntity != null && provinsiEntity.getId() != 0
                ) {
                    completedCheck = true;
                    checkButton();
                    return;
                }
                completedCheck = false;
                Toast.makeText(requireContext(), "Harap mengisi semua field", Toast.LENGTH_SHORT).show();
                checkButton();
                return;
            }
            completedCheck = false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener kecamatanListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                KecamatanEntity entity = (KecamatanEntity) binding.kecamatan.getItemAtPosition(position);
                viewModel.getKelurahan(entity.getId()).observe(getViewLifecycleOwner(), kelurahan -> {
                    if (kelurahan != null) {
                        switch (kelurahan.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                kelurahanEntities = new ArrayList<>();
                                kelurahanEntities.addAll(kelurahan.data);
                                kelurahanEntity = new KelurahanEntity(0, "Pilih Kelurahan", 0);
                                kelurahanEntities.add(kelurahanEntity);
                                Collections.sort(kelurahanEntities);
                                kelurahanEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kelurahanEntities);
                                binding.kelurahan.setAdapter(kelurahanEntityArrayAdapter);
                                binding.kelurahan.setOnItemSelectedListener(kelurahanListener);
                                break;
                            case ERROR:
                                Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener kotaListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                KotaEntity entity = (KotaEntity) binding.kota.getItemAtPosition(position);
                viewModel.getKecamatan(entity.getId()).observe(getViewLifecycleOwner(), kecamatan -> {
                    if (kecamatan != null) {
                        switch (kecamatan.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                kecamatanEntities = new ArrayList<>();
                                kecamatanEntities.addAll(kecamatan.data);
                                kecamatanEntity = new KecamatanEntity(0, "Pilih Kecamatan", 0);
                                kecamatanEntities.add(kecamatanEntity);
                                Collections.sort(kecamatanEntities);
                                kecamatanEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kecamatanEntities);
                                binding.kecamatan.setAdapter(kecamatanEntityArrayAdapter);
                                binding.kecamatan.setOnItemSelectedListener(kecamatanListener);
                                break;
                            case ERROR:
                                Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener provinsiListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                ProvinsiEntity entity = (ProvinsiEntity) binding.provinsi.getItemAtPosition(position);
                viewModel.getKota(entity.getId()).observe(getViewLifecycleOwner(), kota -> {
                    if (kota != null) {
                        switch (kota.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                kotaEntities = new ArrayList<>();
                                kotaEntities.addAll(kota.data);
                                kotaEntity = new KotaEntity(0, "Pilih Kota", 0);
                                kotaEntities.add(kotaEntity);
                                Collections.sort(kotaEntities);
                                kotaEntityArrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, kotaEntities);
                                binding.kota.setAdapter(kotaEntityArrayAdapter);
                                binding.kota.setOnItemSelectedListener(kotaListener);
                                break;
                            case ERROR:
                                Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(AddressUserViewModel.class);
    }

    @Override
    protected void setActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_addressUserFragment_to_searchFragment);
    }
}