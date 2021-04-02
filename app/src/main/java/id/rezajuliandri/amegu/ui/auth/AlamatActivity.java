package id.rezajuliandri.amegu.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.viewmodel.AlamatViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.AlamatViewModelFactory;

public class AlamatActivity extends AppCompatActivity {
    Spinner provinsi, kota, kecamatan, kelurahan;
    EditText alamat;

    AlamatViewModel alamatViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();

        alamatViewModel = new ViewModelProvider(
                this,
                new AlamatViewModelFactory(this.getApplication())
        ).get(AlamatViewModel.class);

        provinsi = findViewById(R.id.provinsi);
        kota = findViewById(R.id.kota);
        kecamatan = findViewById(R.id.kecamatan);
        kelurahan = findViewById(R.id.kelurahan);


    }
}