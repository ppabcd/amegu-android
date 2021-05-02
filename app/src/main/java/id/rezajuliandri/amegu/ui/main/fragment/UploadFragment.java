package id.rezajuliandri.amegu.ui.main.fragment;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.api.responses.data.pet.Attachment;
import id.rezajuliandri.amegu.api.responses.data.pet.Jenis;
import id.rezajuliandri.amegu.api.responses.data.pet.Ras;
import id.rezajuliandri.amegu.databinding.FragmentUploadBinding;
import id.rezajuliandri.amegu.entity.Session;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.helper.ImageFilePath;
import id.rezajuliandri.amegu.interfaces.OnImageUploaded;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.interfaces.pet.OnJenis;
import id.rezajuliandri.amegu.interfaces.pet.OnPetUploaded;
import id.rezajuliandri.amegu.interfaces.pet.OnRas;
import id.rezajuliandri.amegu.ui.auth.AlamatActivity;
import id.rezajuliandri.amegu.ui.splash.SplashActivity;
import id.rezajuliandri.amegu.viewmodel.UploadViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.UploadViewModelFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment {
//    View view;
//    TextView btnUpload, uploadText, namaHewan, usia, beratBadan, harga, kondisi, deskripsi;
//    RadioButton jantan, betina;
//    RadioGroup jenisKelamin;
//    ImageView imageView;
    ActionBarHelper actionBarHelper;
//    Spinner jenis;
//    Spinner ras;
//    Button send;

    UploadViewModel uploadViewModel;
    Session session;

    ArrayList<Jenis> jenisList;
    ArrayList<Ras> rasList;

    // Placeholder
    Jenis jen;
    Ras ra;

    Jenis jenis1;
    Ras ras1;

    FragmentUploadBinding binding;

    ArrayAdapter<Jenis> jenisArrayAdapter;
    ArrayAdapter<Ras> rasArrayAdapter;
    long fileId = 0;

    public String[] permissionsNeeded;
    private static final int REQUEST_IMAGE = 1;
    private static final int PERMISSION_REQ_INTERNAL_STORAGE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUploadBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());

        session = new Session(requireActivity().getApplication());

        // List
        jenisList = new ArrayList<>();
        rasList = new ArrayList<>();

        // Call placeholder data
        initData();
        checkUploadButton();

        uploadViewModel = new ViewModelProvider(
                this,
                new UploadViewModelFactory(requireActivity().getApplication())
        ).get(UploadViewModel.class);
        uploadViewModel.getJenis(new OnJenis() {
            @Override
            public void success(ArrayList<Jenis> jenisResponse) {
                jenisList = jenisResponse;
                jenisList.add(new Jenis(0, "Pilih jenis hewan"));
                Collections.sort(jenisList);
                jenisArrayAdapter = new ArrayAdapter<>(
                        getContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        jenisList
                );
                binding.jenis.setAdapter(jenisArrayAdapter);
                binding.jenis.setOnItemSelectedListener(jenis_listener);
            }

            @Override
            public void error(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });


        binding.upload.setOnClickListener(v -> {
            // cek dulu apakah READ_EXTERNAL_STORAGE & WRITE_EXTERNAL_STORAGE sudah granted
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
            checkUploadButton();
        });

        binding.send.setOnClickListener(v -> {
            if (
                    "".equals(binding.namaHewan.getText().toString()) ||
                            fileId == 0 ||
                            ras1 == null ||
                            jenis1 == null ||
                            ras1.getJenisId() != jenis1.getId() ||
                            binding.jenisKelamin.getCheckedRadioButtonId() == -1 ||
                            "".equals(binding.usia.getText().toString()) ||
                            "".equals(binding.beratBadan.getText().toString()) ||
                            Integer.parseInt(binding.beratBadan.getText().toString()) < 1 ||
                            "".equals(binding.kondisi.getText().toString()) ||
                            "".equals(binding.deskripsi.getText().toString()) ||
                            Integer.parseInt(binding.harga.getText().toString()) < 0
            ) {
                Toast.makeText(getActivity(), "Please fill all required form", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedJenisKelamin = view.findViewById(binding.jenisKelamin.getCheckedRadioButtonId());
            session.getToken(new OnToken() {
                @Override
                public void success(String token) {
                    uploadViewModel.uploadPet(
                            ras1.getId(),
                            binding.namaHewan.getText().toString(),
                            Integer.parseInt(binding.usia.getText().toString()),
                            Integer.parseInt(binding.beratBadan.getText().toString()),
                            binding.kondisi.getText().toString(),
                            selectedJenisKelamin.getText().toString().toLowerCase(),
                            Integer.parseInt(binding.harga.getText().toString()),
                            binding.deskripsi.getText().toString(),
                            fileId,
                            token,
                            new OnPetUploaded() {
                                @Override
                                public void success() {
                                    Toast.makeText(getContext(), "Berhasil menambahkan hewan", Toast.LENGTH_SHORT).show();
                                    moveToHomeFragment(view);
                                }

                                @Override
                                public void error(String error) {
                                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }

                @Override
                public void error(String error) {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            });
        });
        binding.jenisKelamin.setOnCheckedChangeListener((group, checkedId) -> checkUploadButton());
    }

    private void moveToHomeFragment(View view){
        Navigation.findNavController(view).navigate(R.id.action_navigation_upload_to_navigation_home2);
    }

    private final AdapterView.OnItemSelectedListener jenis_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                jenis1 = (Jenis) binding.jenis.getItemAtPosition(position);
                uploadViewModel.getRas(jenis1.getId(), new OnRas() {

                    @Override
                    public void success(ArrayList<Ras> rasArrayList) {
                        rasList = rasArrayList;
                        rasList.add(ra);
                        Collections.sort(rasList);
                        ;
                        rasArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, rasList);
                        binding.ras.setAdapter(rasArrayAdapter);
                        binding.ras.setOnItemSelectedListener(ras_listener);
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
                checkUploadButton();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final AdapterView.OnItemSelectedListener ras_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                ras1 = (Ras) binding.ras.getItemAtPosition(position);
                checkUploadButton();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    void takeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE);
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

    // Menerima balikan request permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_INTERNAL_STORAGE) {
            takeGallery();
        }
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
                session.getToken(new OnToken() {
                    @Override
                    public void success(String token) {
                        uploadViewModel.postImage(file, token, new OnImageUploaded() {

                            @Override
                            public void success(Attachment dataImage) {
                                Log.i("DATA_IMAGE", dataImage.toString());
                                binding.uploadText.setText(dataImage.getFilename());
                                fileId = dataImage.getId();
                                Toast.makeText(getContext(), "Berhasil mengupload gambar ke server", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void error(String message) {
                                Log.i("DATA_IMAGE", message);
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void error(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Proses dibatalkan.", Toast.LENGTH_SHORT).show();
            }
        }
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

    private void initData() {
        jen = new Jenis(0, "Pilih jenis hewan");
        ra = new Ras(0, "Pilih jenis ras", 0, jen);
        jenisList.add(jen);
        rasList.add(ra);

        jenisArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, jenisList);
        binding.jenis.setAdapter(jenisArrayAdapter);

        rasArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, rasList);
        binding.ras.setAdapter(rasArrayAdapter);
    }

    private void checkUploadButton() {
        binding.send.setEnabled(false);
        if (fileId == 0) {
            return;
        }
        if (ras1 == null) {
            return;
        }
        if (jenis1 == null) {
            return;
        }
        if (ras1.getJenisId() != jenis1.getId()) {
            return;
        }
        if (binding.jenisKelamin.getCheckedRadioButtonId() == -1) {
            return;
        }
        binding.send.setEnabled(true);
    }
}