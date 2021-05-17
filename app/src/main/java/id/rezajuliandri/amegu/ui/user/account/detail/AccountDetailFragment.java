package id.rezajuliandri.amegu.ui.user.account.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.databinding.FragmentAccountDetailBinding;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;
import id.rezajuliandri.amegu.vo.Resource;


public class AccountDetailFragment extends Fragment {
    FragmentAccountDetailBinding binding;
    AccountDetailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        getData();
    }

    private void getData() {
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                binding.edtNamaDepan.setText(userEntity.getFirstName());
                binding.edtNamaBelakang.setText(userEntity.getLastName());
                binding.edtEmail.setText(userEntity.getEmail());
                binding.edtEmail.setEnabled(false);
                binding.edtNomorTelepon.setText(userEntity.getPhoneNumber());

                binding.btnSimpanAkun.setOnClickListener(v -> {
                    boolean withPassword = false;
                    if (!binding.edtSandiBaru.getText().toString().equals("")) {
                        if (!binding.edtSandiBaru.getText().toString().equals(binding.edtKonfirmSandi.getText().toString())) {
                            Toast.makeText(requireContext(), "Password dengan password konfirmasi harus sama", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (binding.edtSandiBaru.getText().toString().length() < 8) {
                            Toast.makeText(requireContext(), "Password harus lebih dari 8 karakter", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        withPassword = true;
                    }
                    if (
                            binding.edtNamaDepan.getText().toString().equals("") ||
                                    binding.edtNamaBelakang.getText().toString().equals("") ||
                                    binding.edtNomorTelepon.getText().toString().equals("")
                    ) {
                        Toast.makeText(getActivity(), "Mohon untuk mengisi semua field yang dibutuhkan", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!withPassword) {
                        viewModel.updateProfile(
                                binding.edtNamaDepan.getText().toString().trim(),
                                binding.edtNamaBelakang.getText().toString().trim(),
                                binding.edtNomorTelepon.getText().toString().trim(),
                                userEntity.getToken()
                        ).observe(getViewLifecycleOwner(), status -> {
                            if (status.toLowerCase().equals("ok")) {
                                Toast.makeText(requireContext(), "Berhasil mengupdate profile", Toast.LENGTH_SHORT).show();
                                viewModel.getProfile(userEntity.getToken()).observe(getViewLifecycleOwner(), this::checkProfile);
                                return;
                            }
                            Toast.makeText(requireContext(), "Gagal mengupdate profile", Toast.LENGTH_SHORT).show();
                        });
                        return;
                    }
                    viewModel.updateProfilePassword(
                            binding.edtNamaDepan.getText().toString().trim(),
                            binding.edtNamaBelakang.getText().toString().trim(),
                            binding.edtNomorTelepon.getText().toString().trim(),
                            binding.edtSandiBaru.getText().toString().trim(),
                            userEntity.getToken()
                    ).observe(getViewLifecycleOwner(), status -> {
                        if (status.toLowerCase().equals("ok")) {
                            Toast.makeText(requireContext(), "Berhasil mengupdate profile", Toast.LENGTH_SHORT).show();
                            viewModel.getProfile(userEntity.getToken()).observe(getViewLifecycleOwner(), this::checkProfile);
                            return;
                        }
                        Toast.makeText(requireContext(), "Gagal mengupdate profile", Toast.LENGTH_SHORT).show();
                    });
                });
            }
        });
    }

    private void checkProfile(Resource<UserEntity> userEntityResource) {
        if (userEntityResource != null) {
            switch (userEntityResource.status) {
                case LOADING:
                    Toast.makeText(requireContext(), "Memuat data user", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(AccountDetailViewModel.class);
    }
}