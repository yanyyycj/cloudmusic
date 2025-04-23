package com.ixuea.courses.mymusic.model.ui;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现界面，快捷按钮数据
 */
public class ButtonData implements BaseMultiItemEntity {
    private static ArrayList<IconTitleButtonData> results = new ArrayList<IconTitleButtonData>();

    static {
        results.add(new IconTitleButtonData(R.drawable.day_recommend, R.string.day_recommend));
        results.add(new IconTitleButtonData(R.drawable.person_fm, R.string.person_fm));
        results.add(new IconTitleButtonData(R.drawable.sheet, R.string.sheet));
        results.add(new IconTitleButtonData(R.drawable.rank, R.string.rank));
        results.add(new IconTitleButtonData(R.drawable.button_live, R.string.live));
        results.add(new IconTitleButtonData(R.drawable.digital_album, R.string.digital_album));
        results.add(new IconTitleButtonData(R.drawable.digital_album, R.string.digital_album));
    }

    @Override
    public int getItemType() {
        return Constant.STYLE_BUTTON;
    }


    public List<IconTitleButtonData> getData() {
        return results;
    }
}
