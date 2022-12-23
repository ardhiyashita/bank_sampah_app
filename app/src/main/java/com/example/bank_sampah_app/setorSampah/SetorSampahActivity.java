package com.example.bank_sampah_app.setorSampah;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.requests.PengajuanRequest;
import com.example.bank_sampah_app.API.responses.PengajuanResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetorSampahActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tipePengambilanSpinner;
    EditText totalBeratEt, catatanSampahEt;
    Button lanjutSetorButton, unggahButton;
    int admin_id = 0;
    String foto_sampah = null;
    private ApiClient apiClient;
    private SessionManager sessionManager;
    String selectedTipePengambilan;
    ImageView fotoSampahImg;
    String path;
    ProgressDialog pd;
    private static final int GALLERY_ADD_PROFILE = 1;
    private Bitmap bitmap = null;
    TextView fotoSampagTxt;
    private static final int requestcamera_code= 12;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    private ProgressDialog pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_sampah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Setor Sampah");

        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);
        User user = sessionManager.fetchUser();


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //init
        tipePengambilanSpinner = findViewById(R.id.tipePengambilanSpinner);
        totalBeratEt = findViewById(R.id.totalBeratEt);
        lanjutSetorButton = findViewById(R.id.lanjutSetorButton);
        catatanSampahEt = findViewById(R.id.catatanSampahEt);
        unggahButton = findViewById(R.id.unggahbutton);
        fotoSampahImg = findViewById(R.id.fotoSampahImg);
        fotoSampagTxt = findViewById(R.id.fotosampahtxt);

        textWatcher();

        //spinner dan adapter pilihan tipe pengambilan
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipePengambilanString, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipePengambilanSpinner.setAdapter(adapter);

        //ambil data spinner
        tipePengambilanSpinner.setOnItemSelectedListener(this);


        //upload dari folder
        unggahButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
//                selectImage();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_ADD_PROFILE);
            }
        });

        lanjutSetorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate
                if (pengajuanValidation()== true){
                    pengajuan();
                }
            }
        });
    }

    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void pengajuan() {
        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        AlertDialog alertD = alertDialogBuilder.create();
        alertDialogBuilder.setCancelable(false);
        alertD.show();

        User user = sessionManager.fetchUser();
        PengajuanRequest pengajuanRequest = new PengajuanRequest();
        pengajuanRequest.setUser_id(user.getId_user());
        pengajuanRequest.setCatatan_sampah(catatanSampahEt.getText().toString());
        pengajuanRequest.setTipe_pengambilan(tipePengambilanSpinner.getSelectedItem().toString());
        pengajuanRequest.setBerat(totalBeratEt.getText().toString());
        pengajuanRequest.setAdmin_id(admin_id);
        pengajuanRequest.setFoto_sampah(bitmapToString(bitmap));

        Call<PengajuanResponse> pengajuanResponseCall = apiClient.getApiService(this).userPengajuan(pengajuanRequest);
        pengajuanResponseCall.enqueue(new Callback<PengajuanResponse>() {
            @Override
            public void onResponse(Call<PengajuanResponse> call, Response<PengajuanResponse> response) {
                PengajuanResponse pengajuanResponse = response.body();
                if (pengajuanResponse.getSuccess()==true) {
//                    Toast.makeText(SetorSampahActivity.this, "Data Berhasil Terkirim", Toast.LENGTH_SHORT).show();
                    Intent intentkirimsampah = new Intent(SetorSampahActivity.this, SelesaiSetorSampahActivity.class);
                    startActivity(intentkirimsampah);
                } else {
                    Log.d("imageupload", String.valueOf(pengajuanResponse.getMessage()));
                    Toast.makeText(SetorSampahActivity.this, "Data Tidak Berhasil Terkirim"+ pengajuanResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
                alertD.dismiss();
            }

            @Override
            public void onFailure(Call<PengajuanResponse> call, Throwable t) {
                Toast.makeText(SetorSampahActivity.this, "Data Tidak Berhasil Terkirim, Coba Lagi", Toast.LENGTH_LONG).show();
                alertD.dismiss();
//                Toast.makeText(SetorSampahActivity.this, "Throwable" +t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                Log.d("image",t.getLocalizedMessage());
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        selectedTipePengambilan = (String) parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private boolean pengajuanValidation() {
        String totalBerat = totalBeratEt.getText().toString();
        String foto = bitmapToString(bitmap);
        if( TextUtils.isEmpty(totalBerat) || TextUtils.isEmpty(foto)) {
            if( TextUtils.isEmpty(totalBerat) && TextUtils.isEmpty(foto)) {
                Toast.makeText(SetorSampahActivity.this, "Berat dan Foto Wajib Diisi", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(totalBerat)){
                totalBeratEt.setError("Berat Sampah tidak boleh kosong (perkiraan berat)");
            } else if(TextUtils.isEmpty(foto)) {
                Toast.makeText(SetorSampahActivity.this, "Foto Sampah Wajib Diisi", Toast.LENGTH_SHORT).show();
            }
            return false;
        } else if (Integer.parseInt(totalBerat)<=0 ) {
            totalBeratEt.setError("Berat Sampah tidak kurang dari 0 KG (perkiraan berat)");
            return false;
        }
        return true;

    }

    private void textWatcher(){
        totalBeratEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(pengajuanValidation()){
                    totalBeratEt.setError(null);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_CAMERA) {
//            try {
//                bitmap = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
//                fotoSampahImg.setImageBitmap(bitmap);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (requestCode == PICK_IMAGE_GALLERY) {
//            Uri imgUri = data.getData();
//            fotoSampahImg.setImageURI(imgUri);
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if(requestCode==GALLERY_ADD_PROFILE && resultCode==RESULT_OK){
            Uri imgUri = data.getData();
            fotoSampahImg.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String bitmapToString(Bitmap bitmap) {
        if(bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array, Base64.DEFAULT);
        }
        return "";
    }
}