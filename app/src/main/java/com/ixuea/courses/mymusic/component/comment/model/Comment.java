package com.ixuea.courses.mymusic.component.comment.model;

import com.ixuea.courses.mymusic.component.sheet.model.Sheet;

/**
 * 评论模型
 */
public class Comment {
    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private long likesCount;

    /**
     * 评论关联的歌单
     */
    private Sheet sheet;

    /**
     * 是否点赞
     * 有值表示点赞
     * null表示没点赞
     */
    private String likeId;

    /**
     * 被回复的评论
     */
    private Comment parent;

    /**
     * 被回复评论的id
     * 只有发布评论时候才可能有值
     */
    private String parentId;

    /**
     * 歌单id
     * 只有发布歌单评论时才有值
     */
    private String sheetId;

    /**
     * 动态id
     * 只有发布动态评论时才有值
     */
    private String feedId;

    private String id;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}