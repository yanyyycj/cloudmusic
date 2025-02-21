package com.ixuea.courses.mymusic.component.guide.activity;

import android.view.View;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.databinding.ActivityGuideBinding;

public class GuideActivity extends BaseViewModelActivity<ActivityGuideBinding> implements View.OnClickListener {

    @Override
    protected void initListeners() {
        super.initListeners();
        binding.loginOrRegister.setOnClickListener(this);
        binding.experienceNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_or_register) {
            setShowGuide();

        } else {
            startActivityAfterThis(MainActivity.class);
            setShowGuide();
        }

    }

    private void setShowGuide() {

    }

}
