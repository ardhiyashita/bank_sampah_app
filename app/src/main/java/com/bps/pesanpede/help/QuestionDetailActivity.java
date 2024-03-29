package com.bps.pesanpede.help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.bps.pesanpede.R;

public class QuestionDetailActivity extends AppCompatActivity {

    TextView questionDetailTv, answerDetailTv;
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        questionDetailTv = findViewById(R.id.questionDetailTv);
        answerDetailTv = findViewById(R.id.answerDetailTv);

        HelpItem helpItem = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(helpItem != null){
            questionDetailTv.setText(helpItem.getQuestion());
            answerDetailTv.setText(helpItem.getAnswer());
        }
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Pertanyaan");


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_dark);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}