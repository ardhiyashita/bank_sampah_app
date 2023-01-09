package com.bps.pesanpede.panduan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bps.pesanpede.R;

public class PanduanDetailActivity extends AppCompatActivity {
    TextView panduanDetailTv, panduanContentDetailTv;
    ImageView panduanDetailImg;
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_detail);

        panduanDetailTv = findViewById(R.id.panduanDetailTv);
        panduanContentDetailTv = findViewById(R.id.panduanContentDetailTv);
        panduanDetailImg = findViewById(R.id.panduanDetailImage);

        PanduanItem panduanItem = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(panduanItem != null){
            panduanDetailTv.setText(panduanItem.getPanduan());
            panduanContentDetailTv.setText(panduanItem.getContent());
            panduanDetailImg.setImageResource(panduanItem.getGambar());
        }
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Panduan");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}