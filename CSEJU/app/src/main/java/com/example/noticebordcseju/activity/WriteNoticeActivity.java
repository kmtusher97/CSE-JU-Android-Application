package com.example.noticebordcseju.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noticebordcseju.R;
import com.example.noticebordcseju.model.Notice;
import com.example.noticebordcseju.service.NoticeServices;

import java.util.Calendar;

public class WriteNoticeActivity extends AppCompatActivity {

    private EditText editTextNoticeTitle, editTextNoticeDescription;
    private Button buttonPostNotice;
    private Notice notice;

    private NoticeServices noticeServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notice);

        setTitle("Create Notice");

        notice = new Notice();

        noticeServices = new NoticeServices();

        editTextNoticeTitle = findViewById(R.id.notice_title_input);
        editTextNoticeDescription = findViewById(R.id.notice_description_input);
        buttonPostNotice = findViewById(R.id.post_notice_button);

        /**
         * post notice
         */
        buttonPostNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputData();
                if (!validateData(notice)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Empty Post",
                            Toast.LENGTH_LONG
                    ).show();
                    return;
                } else {
                    noticeServices.addPost(notice);
                    Toast.makeText(
                            getApplicationContext(),
                            "Notice Uploaded",
                            Toast.LENGTH_LONG
                    ).show();
                    WriteNoticeActivity.super.onBackPressed();
                }
            }
        });
    }

    /**
     * @param notice
     * @return
     */
    private boolean validateData(Notice notice) {
        if (notice.getTitle().isEmpty() && notice.getDescription().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * get input data
     */
    private void getInputData() {
        notice.setTitle(editTextNoticeTitle.getText().toString().trim());
        notice.setDescription(editTextNoticeDescription.getText().toString().trim());
        notice.setDate(Calendar.getInstance().getTime());

        System.out.println(notice);
    }
}
