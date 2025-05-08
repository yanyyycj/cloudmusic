package com.ixuea.courses.mymusic.component.song.model;

import com.ixuea.courses.mymusic.component.user.model.User;
import com.ixuea.courses.mymusic.model.Common;

/**
 * 单曲模型
 */
public class Song extends Common {
    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String icon;

    /**
     * 音乐地址
     */
    private String uri;

    /**
     * 点击数
     */
    private int clicksCount;

    /**
     * 评论数
     */
    private int commentsCount;

    /**
     * 歌词类型
     */
    private Integer style;

    /**
     * 歌词内容
     */
    private String lyric;


    /**
     * 创建该音乐的人
     */
    private User user;

    /**
     * 歌手
     */
    private User singer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getClicksCount() {
        return clicksCount;
    }

    public void setClicksCount(int clicksCount) {
        this.clicksCount = clicksCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSinger() {
        return singer;
    }

    public void setSinger(User singer) {
        this.singer = singer;
    }
}
