package id.rezajuliandri.amegu.ui.adopsi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.FragmentAdopsiBinding;
import id.rezajuliandri.amegu.data.entity.location.Alamat;
import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.data.entity.auth.Users;
import id.rezajuliandri.amegu.helpers.StringHelper;
import id.rezajuliandri.amegu.interfaces.auth.OnAlamat;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;

public class AdopsiFragment extends Fragment {
    FragmentAdopsiBinding fragmentAdopsiBinding;

    Pet pet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAdopsiBinding = FragmentAdopsiBinding.inflate(getLayoutInflater());

        if (getArguments() != null) {
            pet = AdopsiFragmentArgs.fromBundle(getArguments()).getPet();
        }
        Session session = new Session(requireActivity().getApplication());

        fragmentAdopsiBinding.txtProsesNama.setText(pet.getNamaHewan());
        fragmentAdopsiBinding.txtProsesHarga.setText(StringHelper.currency(pet.getHarga()));

        Glide.with(requireContext())
                .load(pet.getAttachment().getUrl())
                .apply(new RequestOptions().override(40, 40))
                .placeholder(R.drawable.anjing)
                .into(fragmentAdopsiBinding.imageView2);

        session.getUser(new OnProfile() {
            @Override
            public void success(Users users) {
                fragmentAdopsiBinding.namaPembeli.setText(users.getFirstName());
                fragmentAdopsiBinding.noTelp.setText(users.getPhoneNumber());
                session.getAlamat(new OnAlamat() {
                    @Override
                    public void success(Alamat alamat) {
                        fragmentAdopsiBinding.alamatPembeli.setText(alamat.getAlamat());
                    }

                    @Override
                    public void error(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void error(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });


        return fragmentAdopsiBinding.getRoot();
    }
}