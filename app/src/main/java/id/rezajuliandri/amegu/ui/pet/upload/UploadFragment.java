package id.rezajuliandri.amegu.ui.pet.upload;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.databinding.FragmentUploadBinding;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.utils.ImageFilePath;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class UploadFragment extends BaseFragment {
    private static final int REQUEST_IMAGE = 1;
    private static final int PERMISSION_REQ_INTERNAL_STORAGE = 2;
    public String[] permissionsNeeded;

    ArrayList<JenisEntity> jenisEntities;
    ArrayList<RasEntity> rasEntities;

    FragmentUploadBinding binding;
    UploadViewModel viewModel;
    long fileId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(getLayoutInflater());
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
        viewModel.getJenis().observe(getViewLifecycleOwner(), jenisEntitiesResource -> {
            if (jenisEntitiesResource != null) {
                switch (jenisEntitiesResource.status) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        if (jenisEntitiesResource.data != null) {
                            List<JenisEntity> jenisEntities = jenisEntitiesResource.data;
                            jenisEntities.add(new JenisEntity(0, "Pilih jenis hewan"));
                            Collections.sort(jenisEntities);
                            jenisEntityArrayAdapter = new ArrayAdapter<>(
                                    requireActivity(),
                                    R.layout.support_simple_spinner_dropdown_item,
                                    jenisEntities
                            );
                            binding.jenis.setAdapter(jenisEntityArrayAdapter);
                            binding.jenis.setOnItemSelectedListener(jenisListener);
                        }
                        break;
                    case ERROR:
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        binding.upload.setOnClickListener(v -> {
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
        binding.jenisKelamin.setOnCheckedChangeListener(jenisKelaminListener);
        binding.namaHewan.addTextChangedListener(textListener);
        binding.usia.addTextChangedListener(textListener);
        binding.beratBadan.addTextChangedListener(textListener);
        binding.kondisi.addTextChangedListener(textListener);
        binding.harga.addTextChangedListener(textListener);
        binding.deskripsi.addTextChangedListener(textListener);
        binding.send.setOnClickListener(v -> {
            RadioButton selectedJenisKelamin = binding.getRoot().findViewById(binding.jenisKelamin.getCheckedRadioButtonId());
            viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                binding.send.setEnabled(false);
                binding.send.setText(R.string.loading);
                viewModel.uploadPet(
                        ((RasEntity)binding.ras.getSelectedItem()).getId(),
                        binding.namaHewan.getText().toString(),
                        Integer.parseInt(binding.usia.getText().toString()),
                        Integer.parseInt(binding.beratBadan.getText().toString()),
                        binding.kondisi.getText().toString(),
                        selectedJenisKelamin.getText().toString().toLowerCase(),
                        Integer.parseInt(binding.harga.getText().toString()),
                        binding.deskripsi.getText().toString(),
                        fileId,
                        userEntity.getToken()
                ).observe(getViewLifecycleOwner(), status -> {
                    if(status.toLowerCase().equals("ok")){
                        Toast.makeText(requireContext(), "Berhasil mengirim data hewan", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_upload_to_petsUserFragment);
                        return;
                    }
                    Toast.makeText(getContext(), "Gagal mengirim alamat", Toast.LENGTH_SHORT).show();
                    binding.send.setText(R.string.action_send);
                    binding.send.setEnabled(true);
                });
            });
        });
    }

    private final RadioGroup.OnCheckedChangeListener jenisKelaminListener = (group, checkedId) -> checkButton();

    private AdapterView.OnItemSelectedListener jenisListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                JenisEntity jenis = (JenisEntity) binding.jenis.getItemAtPosition(position);
                viewModel.getRas(jenis.getId()).observe(getViewLifecycleOwner(), rasEntitiesResource -> {
                    if (rasEntitiesResource != null) {
                        switch (rasEntitiesResource.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                if (rasEntitiesResource.data != null) {
                                    List<RasEntity> rasEntities = rasEntitiesResource.data;
                                    rasEntities.add(new RasEntity(0, "Pilih ras hewan", 0));
                                    Collections.sort(rasEntities);
                                    rasEntityArrayAdapter = new ArrayAdapter<>(
                                            requireActivity(),
                                            R.layout.support_simple_spinner_dropdown_item,
                                            rasEntities
                                    );
                                    binding.ras.setAdapter(rasEntityArrayAdapter);
                                    binding.ras.setOnItemSelectedListener(rasListener);
                                }
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
    private boolean rasChecked = false;
    private AdapterView.OnItemSelectedListener rasListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                JenisEntity jenisEntity = (JenisEntity) binding.jenis.getSelectedItem();
                RasEntity rasEntity = (RasEntity) binding.ras.getItemAtPosition(position);
                if (rasEntity.getJenisId() == jenisEntity.getId()) {
                    rasChecked = true;
                }
            } else {
                rasChecked = false;
            }
            checkButton();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    TextWatcher textListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkButton();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void checkButton() {
        binding.send.setEnabled(false);
        if (!rasChecked) {
            return;
        }
        if (binding.jenisKelamin.getCheckedRadioButtonId() == -1) {
            return;
        }
        if ("".equals(binding.namaHewan.getText().toString())) {
            return;
        }
        if (fileId == 0) {
            return;
        }
        if ("".equals(binding.usia.getText().toString())) {
            return;
        }
        if ("".equals(binding.beratBadan.getText().toString()) ||
                Integer.parseInt(binding.beratBadan.getText().toString()) < 1) {
            return;
        }
        if ("".equals(binding.kondisi.getText().toString())) {
            return;
        }
        if ("".equals(binding.deskripsi.getText().toString())) {
            return;
        }
        if("".equals(binding.harga.getText().toString())){
            return;
        }
        if (Integer.parseInt(binding.harga.getText().toString()) < 0) {
            return;
        }
        binding.send.setEnabled(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && data != null) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();

                binding.imageView.setImageURI(uri);
                binding.imageView.setVisibility(View.VISIBLE);
                binding.uploadText.setText(getFileName(uri));

                File file = new File(getRealPathFromUri(uri));

                viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                    if (userEntity != null) {
                        viewModel.postImage(file, userEntity.getToken()).observe(getViewLifecycleOwner(), attachmentEntityResource -> {
                            Log.i("RESOURCE", attachmentEntityResource.toString());
                            if (attachmentEntityResource != null) {
                                switch (attachmentEntityResource.status) {
                                    case LOADING:
                                        binding.progressBar.setVisibility(View.VISIBLE);
                                        break;
                                    case SUCCESS:
                                        binding.progressBar.setVisibility(View.GONE);
                                        if (attachmentEntityResource.data != null) {
                                            AttachmentEntity attachmentEntity = attachmentEntityResource.data;
                                            binding.uploadText.setText(attachmentEntity.getFileName());
                                            fileId = attachmentEntity.getId();
                                            Toast.makeText(requireContext(), "Berhasil mengupload gambar ke server", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case ERROR:
                                        binding.progressBar.setVisibility(View.GONE);
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
        ).get(UploadViewModel.class);
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
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_navigation_upload_to_searchFragment);
    }
}