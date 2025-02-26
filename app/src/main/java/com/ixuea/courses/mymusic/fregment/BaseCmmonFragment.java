package com.ixuea.courses.mymusic.fregment;

import android.app.Activity;
import android.content.Intent;

public abstract class BaseCmmonFragment extends BaseFragment {
    protected void startActivityAfterFinishThis(Class<? extends Activity> clazz) {
        Intent intent = new Intent(getHostActivity(), clazz);
        startActivity(intent);

        getHostActivity().finish();
    }
}
