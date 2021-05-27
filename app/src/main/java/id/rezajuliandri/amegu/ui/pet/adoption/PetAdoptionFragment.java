package id.rezajuliandri.amegu.ui.pet.adoption;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
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

import java.io.File;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetAdoptionBinding;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.utils.ImageFilePath;
import id.rezajuliandri.amegu.utils.StringHelper;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class PetAdoptionFragment extends BaseFragment {
    FragmentPetAdoptionBinding binding;
    PetAdoptionViewModel viewModel;
    private static final int REQUEST_IMAGE = 1;
    private static final int PERMISSION_REQ_INTERNAL_STORAGE = 2;
    public String[] permissionsNeeded;
    int fileId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPetAdoptionBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {
        long petId = PetAdoptionFragmentArgs.fromBundle(requireArguments()).getPetId();
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                viewModel.getAdoptionDetail(petId, userEntity.getToken()).observe(getViewLifecycleOwner(), adopsiEntityResource -> {
                    if (adopsiEntityResource != null) {
                        switch (adopsiEntityResource.status) {
                            case LOADING:
                                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show();
                                break;
                            case SUCCESS:
                                if (adopsiEntityResource.data != null) {
                                    AdopsiEntity adopsiEntity = adopsiEntityResource.data;
                                    viewModel.getInvoice(adopsiEntity.getId()).observe(getViewLifecycleOwner(), invoiceEntity -> {
                                        binding.txtBayarHarga.setText(StringHelper.currency(invoiceEntity.getAmount()));
                                        binding.txtBayarAdmin.setText(StringHelper.currency(invoiceEntity.getAdmin()));
                                        binding.txtBayarTotal.setText(StringHelper.currency(invoiceEntity.getTotal()));
                                        binding.unggah.setVisibility(View.GONE);
                                        binding.txtTanggal.setText(StringHelper.deadlineHelper(invoiceEntity.getCreatedAt()));
                                        binding.btnUploadBayar.setOnClickListener(v -> {
                                            if (ImageFilePath.isHasPermissions(getContext(), new String[]{
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, requiredPermissions -> {
                                                permissionsNeeded = requiredPermissions; // permission mana saja yg belum tergranted
                                            })) {
                                                // Yak, granted, langsung takeGallery
                                                takeGallery();
                                            } else {
                                                // Belum granted, send permission request dulu
                                                requestPermissions(permissionsNeeded, PERMISSION_REQ_INTERNAL_STORAGE);
                                            }
                                        });
                                        binding.btnKonfirmBayar.setOnClickListener(v -> {
                                            if (fileId == 0) {
                                                Toast.makeText(requireContext(), "Mohon mengecek kembali gambar yang di upload", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            viewModel.paymentConfirm(
                                                    adopsiEntity.getHewanId(),
                                                    fileId,
                                                    userEntity.getToken()
                                            ).observe(
                                                    getViewLifecycleOwner(),
                                                    status -> {
                                                        if(status != null){
                                                            if (status.toLowerCase().equals("ok")) {
                                                                Toast.makeText(requireContext(), "Berhasil melakukan konfirmasi pembayaran", Toast.LENGTH_SHORT).show();
                                                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_petAdoptionFragment_to_userPetAdoptionFragment);
                                                            } else {
                                                                Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }
                                            );
                                        });
                                    });
                                }
                                break;
                            case ERROR:
                                Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && data != null) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();

                binding.imgUploadBayar.setImageURI(uri);
                binding.imgNama.setText(getFileName(uri));
                binding.unggah.setVisibility(View.VISIBLE);

                File file = new File(getRealPathFromUri(uri));

                viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                    if (userEntity != null) {
                        viewModel.postImage(file, userEntity.getToken()).observe(getViewLifecycleOwner(), attachmentEntityResource -> {
                            Log.i("RESOURCE", attachmentEntityResource.toString());
                            if (attachmentEntityResource != null) {
                                switch (attachmentEntityResource.status) {
                                    case LOADING:
                                        break;
                                    case SUCCESS:
                                        if (attachmentEntityResource.data != null) {
                                            AttachmentEntity attachmentEntity = attachmentEntityResource.data;
                                            binding.imgNama.setText(attachmentEntity.getFileName());
                                            fileId = attachmentEntity.getId();
                                            Toast.makeText(requireContext(), "Berhasil mengupload gambar ke server", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case ERROR:
                                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                        break;

                                }
                            }
                        });
                    }
                });

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Proses dibatalkan.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                assert cursor != null;
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_INTERNAL_STORAGE) {
            takeGallery();
        }
    }

    void takeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(PetAdoptionViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
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