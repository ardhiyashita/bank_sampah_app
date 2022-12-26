package com.example.bank_sampah_app.tentangKami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.help.HelpItem;

public class TentangDetailActivity extends AppCompatActivity {

    TextView judulTentangKami, contentTentangKami;
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_detail);

        judulTentangKami = findViewById(R.id.judulTentangKami);
        contentTentangKami = findViewById(R.id.contentTentangKami);

        TentangKamiItem tentangKamiItem = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(tentangKamiItem != null){
            judulTentangKami.setText(tentangKamiItem.getTentang());
            contentTentangKami.setText(tentangKamiItem.getContent());
        }
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Tentang Kami");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}