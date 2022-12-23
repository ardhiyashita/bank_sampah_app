package com.example.bank_sampah_app.panduan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bank_sampah_app.R;

public class PanduanDetailActivity extends AppCompatActivity {
    TextView panduanDetailTv, panduanContentDetailTv;
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_detail);

        panduanDetailTv = findViewById(R.id.panduanDetailTv);
        panduanContentDetailTv = findViewById(R.id.panduanContentDetailTv);

        PanduanItem panduanItem = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(panduanItem != null){
            panduanDetailTv.setText(panduanItem.getPanduan());
            panduanContentDetailTv.setText(panduanItem.getContent());
        }
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Panduan");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}