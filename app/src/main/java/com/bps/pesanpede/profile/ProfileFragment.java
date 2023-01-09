package com.bps.pesanpede.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bps.pesanpede.API.ApiClient;
import com.bps.pesanpede.API.Constant;
import com.bps.pesanpede.API.responses.LogoutResponse;
import com.bps.pesanpede.API.responses.UserDataResponse;
import com.bps.pesanpede.R;
import com.bps.pesanpede.User;
import com.bps.pesanpede.authentication.LoginActivity;
import com.bps.pesanpede.authentication.SessionManager;
import com.bps.pesanpede.help.HelpFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    View view;
    LinearLayout lUbahData, lBantuan, logout;
    SwipeRefreshLayout swipeContainer;
    CircleImageView imgProfile;
    TextView tvNama, tvNomor;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        User user = sessionManager.fetchUser();
        apiClient = new ApiClient();

        swipeContainer =view.findViewById(R.id.refresh_profile);
        lUbahData = view.findViewById(R.id.ubahdataakun);
        lBantuan = view.findViewById(R.id.bantuandanpencarian);
        logout = view.findViewById(R.id.logout);
        imgProfile = view.findViewById(R.id.img_profile);
        tvNama = view.findViewById(R.id.tv_namaprofile);
        tvNomor = view.findViewById(R.id.tv_hpprofile);

        tvNama.setText(user.getName());
        tvNomor.setText(user.getNo_buku());
        imgProfile.setImageResource(R.drawable.ic_ubah_foto_profile);


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUser();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light);

//        String image = user.getFoto();
//        String imgData = null;

//        if (image != null){
//            imgData = imgData.substring(imgData.indexOf(",") + 1);
//            byte[] decodedString = Base64.decode(imgData, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            imgProfile.setImageBitmap(decodedByte);
//        } else{
//            imgProfile.setImageResource(R.drawable.ic_ubah_foto_profile);
//        }


//        String url_image = user.getFoto();
//        if (url_image != null){
//            Picasso.get().load(Constant.BASE_URL+"/user/"+url_image).into(imgProfile);
//        } else{
//            imgProfile.setImageResource(R.drawable.ic_ubah_foto_profile);
//        }


        lUbahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUbahData = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intentUbahData);

            }
        });

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

    public void userLogout(){
        //progress dialog
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.progress_dialog, null);
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        androidx.appcompat.app.AlertDialog alertD = alertDialogBuilder.create();
        alertDialogBuilder.setCancelable(false);
        alertD.show();

        Call<LogoutResponse> logoutResponseCall = apiClient.getApiService(getActivity().getApplicationContext()).userLogout();
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse = response.body();
                if (logoutResponse.getSuccess()==true) {
                    if (sessionManager.fetchAuthToken() != null) {
                        sessionManager.deleteAuthToken();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getActivity(),"Logout Gagal", Toast.LENGTH_SHORT).show();
                }
                alertD.dismiss();
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Logout Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void refreshUser() {
        reLoadFragment();
        Call<UserDataResponse> userDataResponseCall = apiClient.getApiService(getActivity()).getUserData();
        userDataResponseCall.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                UserDataResponse userDataResponse = response.body();
                if (userDataResponse.getSuccess()==true) {
                    sessionManager.saveUser(userDataResponse.getUser());
                    tvNama.setText(userDataResponse.getUser().getName());
                    tvNomor.setText(userDataResponse.getUser().getNo_buku());
                    userDataResponse.getUser().getFoto();
                    if (userDataResponse.getUser().getFoto() != null){
                        Picasso.get().load(Constant.BASE_URL+"/user/"+userDataResponse.getUser().getFoto()).into(imgProfile);
                    }
                    reLoadFragment();
                } else {
                    Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal memuat, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
        swipeContainer.setRefreshing(false);
    }

    private void reLoadFragment()
    {
        FragmentTransaction fragTransaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fragTransaction.detach(this).commitNow();
            fragTransaction.attach(this).commitNow();
        } else {
            fragTransaction.detach(this).attach(this).commit();
        }
    }
}