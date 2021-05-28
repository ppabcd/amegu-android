package id.rezajuliandri.amegu.ui.auth.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.databinding.ActivityAddressBinding;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;
import id.rezajuliandri.amegu.vo.Resource;

public class AddressActivity extends AppCompatActivity {
    ActivityAddressBinding binding;
    Context mContext;
    AddressViewModel viewModel;
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
    private final AdapterView.OnItemSelectedListener provinsiListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                ProvinsiEntity entity = (ProvinsiEntity) binding.provinsi.getItemAtPosition(position);
                viewModel.getKota(entity.getId()).observe(AddressActivity.this, kota -> {
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
                                kotaEntityArrayAdapter = new ArrayAdapter<>(AddressActivity.this, R.layout.support_simple_spinner_dropdown_item, kotaEntities);
                                binding.kota.setAdapter(kotaEntityArrayAdapter);
                                binding.kota.setOnItemSelectedListener(kotaListener);
                                break;
                            case ERROR:
                                Toast.makeText(AddressActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
    ArrayAdapter<KecamatanEntity> kecamatanEntityArrayAdapter;
    private final AdapterView.OnItemSelectedListener kotaListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                KotaEntity entity = (KotaEntity) binding.kota.getItemAtPosition(position);
                viewModel.getKecamatan(entity.getId()).observe(AddressActivity.this, kecamatan -> {
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
                                kecamatanEntityArrayAdapter = new ArrayAdapter<>(AddressActivity.this, R.layout.support_simple_spinner_dropdown_item, kecamatanEntities);
                                binding.kecamatan.setAdapter(kecamatanEntityArrayAdapter);
                                binding.kecamatan.setOnItemSelectedListener(kecamatanListener);
                                break;
                            case ERROR:
                                Toast.makeText(AddressActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
    ArrayAdapter<KelurahanEntity> kelurahanEntityArrayAdapter;
    private final AdapterView.OnItemSelectedListener kecamatanListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                KecamatanEntity entity = (KecamatanEntity) binding.kecamatan.getItemAtPosition(position);
                viewModel.getKelurahan(entity.getId()).observe(AddressActivity.this, kelurahan -> {
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
                                kelurahanEntityArrayAdapter = new ArrayAdapter<>(AddressActivity.this, R.layout.support_simple_spinner_dropdown_item, kelurahanEntities);
                                binding.kelurahan.setAdapter(kelurahanEntityArrayAdapter);
                                binding.kelurahan.setOnItemSelectedListener(kelurahanListener);
                                break;
                            case ERROR:
                                Toast.makeText(AddressActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
    boolean completedCheck = false;
    boolean completedText = false;
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
                Toast.makeText(AddressActivity.this, "Harap mengisi semua field", Toast.LENGTH_SHORT).show();
                checkButton();
                return;
            }
            completedCheck = false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        this.mContext = this;
        setContentView(binding.getRoot());
        setupActionBar();
        setupViewModel();
        initData();
        loadData();
    }

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this.getApplication());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(AddressViewModel.class);
    }

    private void initData() {
        provinsiEntities = new ArrayList<>();
        provinsiEntity = new ProvinsiEntity(0, "Pilih Provinsi");
        provinsiEntities.add(provinsiEntity);
        provinsiEntityArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, provinsiEntities);
        binding.provinsi.setAdapter(provinsiEntityArrayAdapter);

        kotaEntities = new ArrayList<>();
        kotaEntity = new KotaEntity(0, "Pilih Kota", 0);
        kotaEntities.add(kotaEntity);
        kotaEntityArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, kotaEntities);
        binding.kota.setAdapter(kotaEntityArrayAdapter);

        kecamatanEntities = new ArrayList<>();
        kecamatanEntity = new KecamatanEntity(0, "Pilih Kecamatan", 0);
        kecamatanEntities.add(kecamatanEntity);
        kecamatanEntityArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, kecamatanEntities);
        binding.kecamatan.setAdapter(kecamatanEntityArrayAdapter);

        kelurahanEntities = new ArrayList<>();
        kelurahanEntity = new KelurahanEntity(0, "Pilih Kelurahan", 0);
        kelurahanEntities.add(kelurahanEntity);
        kelurahanEntityArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, kelurahanEntities);
        binding.kelurahan.setAdapter(kelurahanEntityArrayAdapter);

        binding.alamat.addTextChangedListener(alamat_listener);
    }

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void loadData() {
        viewModel.getProvinsi().observe(this, provinsi -> {
            if (provinsi != null) {
                switch (provinsi.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        provinsiEntities.addAll(provinsi.data);
                        provinsiEntity = new ProvinsiEntity(0, "Pilih Provinsi");
                        provinsiEntities.add(provinsiEntity);
                        provinsiEntityArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, provinsiEntities);
                        binding.provinsi.setAdapter(provinsiEntityArrayAdapter);
                        binding.provinsi.setOnItemSelectedListener(provinsiListener);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        viewModel.getUser().observe(this, userEntity -> {
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
                    ).observe(this, status -> {
                        if (status.toLowerCase().equals("ok")) {
                            viewModel.getProfile(userEntity.getToken()).observe(this, this::checkProfile);
                            return;
                        }
                        Toast.makeText(this, "Gagal mengirim alamat", Toast.LENGTH_SHORT).show();
                        binding.sendAddress.setText(R.string.simpan);
                        binding.sendAddress.setEnabled(true);
                    });
                } catch (Exception e) {
                    binding.sendAddress.setText(R.string.simpan);
                    binding.sendAddress.setEnabled(true);
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
                    Toast.makeText(this, "Berhasil menambahkan alamat", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddressActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case ERROR:
                    binding.sendAddress.setText(R.string.simpan);
                    binding.sendAddress.setEnabled(true);
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private void checkButton() {
        if (!completedCheck || !completedText) {
            binding.sendAddress.setEnabled(false);
            return;
        }
        binding.sendAddress.setEnabled(true);
    }
}