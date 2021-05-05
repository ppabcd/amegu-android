package id.rezajuliandri.amegu.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.entity.location.Kecamatan;
import id.rezajuliandri.amegu.data.entity.location.Kelurahan;
import id.rezajuliandri.amegu.data.entity.location.Kota;
import id.rezajuliandri.amegu.data.entity.location.Provinsi;
import id.rezajuliandri.amegu.databinding.ActivityAlamatBinding;
import id.rezajuliandri.amegu.interfaces.location.OnAlamatSent;
import id.rezajuliandri.amegu.interfaces.location.OnKecamatan;
import id.rezajuliandri.amegu.interfaces.location.OnKelurahan;
import id.rezajuliandri.amegu.interfaces.location.OnKota;
import id.rezajuliandri.amegu.interfaces.location.OnProvinsi;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.middleware.BaseActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class AddressActivity extends BaseActivity {
    AddressViewModel addressViewModel;

    ArrayAdapter<Kecamatan> kecamatanArrayAdapter;
    ArrayAdapter<Kelurahan> kelurahanArrayAdapter;
    ArrayAdapter<Kota> kotaArrayAdapter;
    ArrayAdapter<Provinsi> provinsiArrayAdapter;
    // Init data
    Provinsi prov;
    Kota kot;
    Kecamatan kec;
    Kelurahan kel;
    Provinsi provinsi1;
    Kota kota1;
    Kecamatan kecamatan1;
    Kelurahan kelurahan1;
    ActivityAlamatBinding binding;
    private final AdapterView.OnItemSelectedListener kelurahan_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                kelurahan1 = (Kelurahan) binding.kelurahan.getItemAtPosition(position);
                checkSubmitData();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private ArrayList<Kecamatan> kecamatans;
    private ArrayList<Kelurahan> kelurahans;
    private final AdapterView.OnItemSelectedListener kecamatan_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                kecamatan1 = (Kecamatan) binding.kecamatan.getItemAtPosition(position);
                addressViewModel.getKelurahan(new OnKelurahan() {
                    @Override
                    public void success(ArrayList<Kelurahan> kelurahanList) {
                        kelurahans = kelurahanList;
                        kelurahans.add(kel);
                        Collections.sort(kelurahans);
                        kelurahanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kelurahans);
                        binding.kelurahan.setAdapter(kelurahanArrayAdapter);
                        binding.kelurahan.setOnItemSelectedListener(kelurahan_listener);
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(AddressActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, kecamatan1.getId());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener kota_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                kota1 = (Kota) binding.kota.getItemAtPosition(position);
                addressViewModel.getKecamatan(new OnKecamatan() {
                    @Override
                    public void success(ArrayList<Kecamatan> kecamatanList) {
                        kecamatans = kecamatanList;
                        kecamatans.add(kec);
                        Collections.sort(kecamatans);
                        kecamatanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kecamatans);
                        binding.kecamatan.setAdapter(kecamatanArrayAdapter);
                        binding.kecamatan.setOnItemSelectedListener(kecamatan_listener);

                        // Rollback data if city changed
                        kelurahans = new ArrayList<>();
                        kelurahans.add(kel);
                        kelurahanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kelurahans);
                        binding.kelurahan.setAdapter(kelurahanArrayAdapter);
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(AddressActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, kota1.getId());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private ArrayList<Kota> kotas;
    // Handle every change data in spinner
    private final AdapterView.OnItemSelectedListener provinsi_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                provinsi1 = (Provinsi) binding.provinsi.getItemAtPosition(position);
                addressViewModel.getKota(new OnKota() {
                    @Override
                    public void success(ArrayList<Kota> kotaList) {
                        kotas = kotaList;
                        kotas.add(kot);
                        Collections.sort(kotas);
                        kotaArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kotas);
                        binding.kota.setAdapter(kotaArrayAdapter);
                        binding.kota.setOnItemSelectedListener(kota_listener);

                        // Rollback data if city changed
                        kecamatans = new ArrayList<>();
                        kelurahans = new ArrayList<>();
                        kecamatans.add(kec);
                        kelurahans.add(kel);
                        kecamatanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kecamatans);
                        binding.kecamatan.setAdapter(kecamatanArrayAdapter);

                        kelurahanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kelurahans);
                        binding.kelurahan.setAdapter(kelurahanArrayAdapter);
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(AddressActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }, provinsi1.getId());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private ArrayList<Provinsi> provinsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlamatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this.getApplication());
        addressViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(AddressViewModel.class);

        provinsis = new ArrayList();
        kotas = new ArrayList<>();
        kecamatans = new ArrayList<>();
        kelurahans = new ArrayList<>();

        initData();
        binding.alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.provinsi.setEnabled(true);
                binding.kota.setEnabled(true);
                binding.kecamatan.setEnabled(true);
                binding.kelurahan.setEnabled(true);
                binding.provinsi.setClickable(false);
                if ("".contentEquals(binding.alamat.getText())) {
                    binding.provinsi.setEnabled(false);
                    binding.kota.setEnabled(false);
                    binding.kecamatan.setEnabled(false);
                    binding.kelurahan.setEnabled(false);
                }
                checkSubmitData();
            }
        });

        binding.sendAddress.setOnClickListener(v -> {
            try {
                addressViewModel.sendAlamat(new OnAlamatSent() {
                    @Override
                    public void success() {
                        Toast.makeText(AddressActivity.this, "Berhasil mengirimkan alamat ke server", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddressActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(AddressActivity.this, "Gagal mengirimkan alamat ke server", Toast.LENGTH_SHORT).show();
                    }
                }, binding.alamat.getText().toString(), kelurahan1);
            } catch (Exception exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        addressViewModel.getProvinsi(new OnProvinsi() {
            @Override
            public void success(ArrayList<Provinsi> provinsiList) {
                provinsis = provinsiList;
                provinsis.add(new Provinsi(0, "Choose a Province"));
                Collections.sort(provinsis);
                provinsiArrayAdapter = new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        provinsis
                );
                binding.provinsi.setAdapter(provinsiArrayAdapter);
                binding.provinsi.setOnItemSelectedListener(provinsi_listener);

                // Rollback data if province changed
                kotas = new ArrayList<>();
                kecamatans = new ArrayList<>();
                kelurahans = new ArrayList<>();
                kotas.add(kot);
                kecamatans.add(kec);
                kelurahans.add(kel);
            }

            @Override
            public void error(String message) {
                Toast.makeText(AddressActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkSubmitData() {
        try {
            if ("".contentEquals(binding.alamat.getText())) {
                binding.sendAddress.setEnabled(false);
                return;
            }
            if (
                    kelurahan1.getKecamatan().getId() != kecamatan1.getId() ||
                            kelurahan1.getKecamatan().getKota().getId() != kota1.getId() ||
                            kelurahan1.getKecamatan().getKota().getProvinsi().getId() != provinsi1.getId()

            ) {
                Toast.makeText(AddressActivity.this, "Mohon mengisi kembali data tidak valid.", Toast.LENGTH_SHORT).show();
                binding.sendAddress.setEnabled(false);
                return;
            }

            binding.sendAddress.setEnabled(true);
        } catch (Exception exception) {
            binding.sendAddress.setEnabled(false);
        }
    }

    private void initData() {
        prov = new Provinsi(0, "Choose Province");
        kot = new Kota(0, "Choose City", 0, prov);
        kec = new Kecamatan(0, "Choose Districts", 0, kot);
        kel = new Kelurahan(0, "Choose Sub-district", 0, kec);
        provinsis.add(prov);
        kotas.add(kot);
        kecamatans.add(kec);
        kelurahans.add(kel);

        provinsiArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, provinsis);
        binding.provinsi.setAdapter(provinsiArrayAdapter);

        kotaArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kotas);
        binding.kota.setAdapter(kotaArrayAdapter);

        kecamatanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kecamatans);
        binding.kecamatan.setAdapter(kecamatanArrayAdapter);

        kelurahanArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, kelurahans);
        binding.kelurahan.setAdapter(kelurahanArrayAdapter);
    }
}