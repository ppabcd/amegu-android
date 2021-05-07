package id.rezajuliandri.amegu.ui.account_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.data.api.ApiConfig;
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.data.entity.auth.Users;
import id.rezajuliandri.amegu.data.entity.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.databinding.FragmentAccountDetailBinding;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountDetailFragment extends Fragment {
    FragmentAccountDetailBinding binding;
    Session session;
    AccountDetailViewModel accountDetailViewModel;

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
        session = new Session(requireActivity().getApplication());

        // View Model
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity().getApplication());
        accountDetailViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(AccountDetailViewModel.class);

        // Mengisi field edit profile
        session.getUser(new OnProfile() {
            @Override
            public void success(Users users) {
                binding.edtNamaDepan.setText(users.getFirstName());
                binding.edtNamaBelakang.setText(users.getLastName());
                binding.edtEmail.setText(users.getEmail());
                binding.edtEmail.setEnabled(false);
                binding.edtNomorTelepon.setText(users.getPhoneNumber());
            }

            @Override
            public void error(String message) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSimpanAkun.setOnClickListener(v -> {
            session.getToken(new OnToken() {
                @Override
                public void success(String token) {
                    boolean withPassword = false;
                    if (!binding.edtSandiBaru.getText().toString().equals("")) {
                        if (
                                !binding.edtSandiBaru.getText().toString().equals(binding.edtKonfirmSandi.getText().toString())
                        ) {
                            Toast.makeText(getActivity(), "Password dengan password konfirmasi harus sama", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (binding.edtSandiBaru.getText().toString().length() < 8) {
                            Toast.makeText(getActivity(), "Password harus lebih dari 8 karakter", Toast.LENGTH_SHORT).show();
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
                    if(!withPassword){
                        // Jika tanpa password
                        Call<EmptyOkResponse> updateProfile = ApiConfig.getApiService().profile(
                                binding.edtNamaDepan.getText().toString().trim(),
                                binding.edtNamaBelakang.getText().toString().trim(),
                                binding.edtNomorTelepon.getText().toString().trim(),
                                token
                        );
                        updateProfile.enqueue(new Callback<EmptyOkResponse>() {
                            @Override
                            public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                                session.refreshUserData(new OnProfile() {
                                    @Override
                                    public void success(Users users) {
                                        Toast.makeText(getActivity(), "Berhasil mengupdate data profile", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    @Override
                                    public void error(String message) {
                                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                    }
                    Call<EmptyOkResponse> updateProfile = ApiConfig.getApiService().profileWithPassword(
                            binding.edtNamaDepan.getText().toString().trim(),
                            binding.edtNamaBelakang.getText().toString().trim(),
                            binding.edtNomorTelepon.getText().toString().trim(),
                            binding.edtSandiBaru.getText().toString().trim(),
                            token
                    );
                    updateProfile.enqueue(new Callback<EmptyOkResponse>() {
                        @Override
                        public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                            session.refreshUserData(new OnProfile() {
                                @Override
                                public void success(Users users) {
                                    Toast.makeText(getActivity(), "Berhasil mengupdate data profile", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                @Override
                                public void error(String message) {
                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                }

                @Override
                public void error(String error) {
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}