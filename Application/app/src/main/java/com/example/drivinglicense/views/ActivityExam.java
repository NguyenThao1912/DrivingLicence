package com.example.drivinglicense.views;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.drivinglicense.R;
import com.example.drivinglicense.databinding.ActivityExamBinding;
import com.example.drivinglicense.model.Question;

import java.util.ArrayList;
import java.util.List;

public class ActivityExam extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private ActivityExamBinding binding;
    private Question question = new Question();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exam);
        binding.tvAnswers.setText(question.getZANSWERDESC());
        binding.llQues.setVisibility(View.GONE);
        setOptions();
        setOnClick();
    }
    //TODO khởi tạo dữ liệu cho các view option
    private void setOptions(){
        binding.tvOption1.setText(question.getZOPTION1());
        binding.tvOption2.setText(question.getZOPTION2());
        binding.tvOption3.setText(question.getZOPTION3());
        binding.tvOption4.setText(question.getZOPTION4());
        setViewOptionDefault();
    }

    private void setViewOptionDefault(){
        binding.ivCircleOption1.setBackgroundColor(Color.WHITE);
        binding.ivCircleOption2.setBackgroundColor(Color.WHITE);
        binding.ivCircleOption3.setBackgroundColor(Color.WHITE);
        binding.ivCircleOption4.setBackgroundColor(Color.WHITE);
        binding.tvNumOption1.setTextColor(Color.BLACK);
        binding.tvNumOption2.setTextColor(Color.BLACK);
        binding.tvNumOption3.setTextColor(Color.BLACK);
        binding.tvNumOption4.setTextColor(Color.BLACK);
        if(question.getZOPTION4()==null){
            binding.llOption4.setVisibility(View.GONE);
        }

    }

    //TODO Bắt sự kiện click cho các button
    private void setOnClick(){
        binding.btBack.setOnClickListener(this);
        binding.btBackQues.setOnClickListener(this);
        binding.btNextQues.setOnClickListener(this);
        binding.btNote.setOnClickListener(this);
        binding.llOption1.setOnClickListener(this);
        binding.llOption2.setOnClickListener(this);
        binding.llOption3.setOnClickListener(this);
        binding.llOption4.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:{
                //TODO Quay lại màn hình trước đó
                onBackPressed();
                break;
            }
            case R.id.bt_note:{
                //TODO Hiển thị thanh menu question
                Animation ani;
                if(binding.cardMenuQues.isShown()){
                    ani = AnimationUtils.loadAnimation(this, R.anim.close_activity);
                    ani.setAnimationListener(this);
                    binding.cardMenuQues.startAnimation(ani);
                    binding.cardMenuQues.setVisibility(View.GONE);
                }
                else {
                    ani = AnimationUtils.loadAnimation(this, R.anim.open_activiy);
                    ani.setAnimationListener(this);
                    binding.cardMenuQues.startAnimation(ani);
                    binding.cardMenuQues.setVisibility(View.VISIBLE);
                }
                break;
            }
            case R.id.bt_nextQues:{
                //TODO Chuyển sang câu hỏi tiếp theo
                break;
            }
            case R.id.bt_backQues:{
                //TODO Chuyển sang câu hỏi trước đó
                break;
            }
            case R.id.bt_done:{
                //TODO kết thúc bài thi
                break;
            }
            case R.id.ll_option1:{
                //TODO thay đổi màu sắc cho option 1
                setViewOptionDefault();
                binding.ivCircleOption1.setBackgroundColor(getColor(R.color.green_300));
                binding.tvNumOption1.setTextColor(Color.WHITE);
                break;
            }
            case R.id.ll_option2:{
                //TODO thay đổi màu sắc cho option 2
                setViewOptionDefault();
                binding.ivCircleOption2.setBackgroundColor(getColor(R.color.green_300));
                binding.tvNumOption2.setTextColor(Color.WHITE);
                break;
            }
            case R.id.ll_option3:{
                //TODO thay đổi màu sắc cho option 3
                setViewOptionDefault();
                binding.ivCircleOption3.setBackgroundColor(getColor(R.color.green_300));
                binding.tvNumOption3.setTextColor(Color.WHITE);
                break;
            }
            case R.id.ll_option4:{
                //TODO thay đổi màu sắc cho option 4
                setViewOptionDefault();
                binding.ivCircleOption4.setBackgroundColor(getColor(R.color.green_300));
                binding.tvNumOption4.setTextColor(Color.WHITE);
                break;
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
