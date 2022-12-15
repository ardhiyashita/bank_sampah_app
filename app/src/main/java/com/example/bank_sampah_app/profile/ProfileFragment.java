package com.example.bank_sampah_app.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bank_sampah_app.API.ApiClient;
import com.example.bank_sampah_app.API.responses.LogoutResponse;
import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.User;
import com.example.bank_sampah_app.authentication.LoginActivity;
import com.example.bank_sampah_app.authentication.SessionManager;
import com.example.bank_sampah_app.help.HelpFragment;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private SessionManager sessionManager;
    View view;
    LinearLayout lUbahData, lUbahPass, lBantuan, logout;
    ImageView imgProfile;
    TextView tvNama, tvNoHp;
//    AlertDialog.Builder builder;

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        User user = sessionManager.fetchUser();

        lUbahData = view.findViewById(R.id.ubahdataakun);
        lUbahPass = view.findViewById(R.id.ubahpassword);
        lBantuan = view.findViewById(R.id.bantuandanpencarian);
        logout = view.findViewById(R.id.logout);
        imgProfile = view.findViewById(R.id.img_profile);
        tvNama = view.findViewById(R.id.tv_namaprofile);
        tvNoHp = view.findViewById(R.id.tv_hpprofile);

        tvNama.setText(user.getName());
        tvNoHp.setText(user.getNo_hp());

        String url_image = user.getFoto();
        if (url_image != null){
            Picasso.get().load(url_image).into(imgProfile);
        }

        lUbahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUbahData = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intentUbahData);

            }
        });

//        lUbahPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentUbahPass = new Intent(getActivity(), UbahPasswordActivity.class);
//                startActivity(intentUbahPass);
//
//            }
//        });

        lBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HelpFragment());
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Apakah Yakin Ingin Keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userLogout();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.show();
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void userLogout(){
        ApiClient apiClient = new ApiClient();
        Call<LogoutResponse> logoutResponseCall = apiClient.getApiService(getActivity().getApplicationContext()).userLogout();
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse = response.body();
                if (logoutResponse.getSuccess()==true) {
                    if (sessionManager.fetchAuthToken() != null) {
                        sessionManager.deleteAuthToken();
                        Toast.makeText(getActivity(),"Logout Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getActivity(),"Logout Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}