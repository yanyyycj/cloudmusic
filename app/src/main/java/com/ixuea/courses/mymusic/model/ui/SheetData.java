package com.ixuea.courses.mymusic.model.ui;

import com.ixuea.courses.mymusic.component.sheet.model.Sheet;
import com.ixuea.courses.mymusic.util.Constant;

import java.util.List;

/**
 * 发现界面，歌单外层容器数据
 */
public class SheetData implements BaseMultiItemEntity {

    private List<Sheet> data;

    public SheetData(List<Sheet> data) {
        this.data = data;
    }

    public List<Sheet> getData() {
        return data;
    }

    public void setData(List<Sheet> data) {
        this.data = data;
    }

    @Override
    public int getItemType() {
        return Constant.STYLE_SHEET;
    }
}