package com.yc.blogtemplate.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * post 标题
     */
    @NotEmpty(message = "标题不能为空.")
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String title;

    /**
     * 摘要
     */
    @NotEmpty(message = "摘要不能为空.")
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String summary;

    /**
     * html文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private String contentHtml;

    /**
     * MD格式文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private String contentMd;


    /**
     * post发布时间
     */
    @Column(nullable = false)
    private Date publishData;

    /**
     * post状态
     * 0 发布
     * 1 草稿
     * 2 回收站
     */
    private Integer postStatus = 0;

    private Long postViews = 0L;

    private Boolean allowComment = true;

    public Post(){

    }

    public Post(@NotEmpty(message = "标题不能为空.") @Size(min = 2, max = 50) String title,
                @NotEmpty(message = "摘要不能为空.") @Size(min = 2, max = 50) String summary,
                String contentHtml, String contentMd, Date publishData, Integer postStatus,
                Long postViews, Boolean allowComment) {
        this.title = title;
        this.summary = summary;
        this.contentHtml = contentHtml;
        this.contentMd = contentMd;
        this.publishData = publishData;
        this.postStatus = postStatus;
        this.postViews = postViews;
        this.allowComment = allowComment;
    }

    public Post(Long id, String title, Date publishData, Integer postStatus){
        this.id = id;
        this.title = title;
        this.publishData = publishData;
        this.postStatus = postStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public Date getPublishData() {
        return publishData;
    }

    public void setPublishData(Date publishData) {
        this.publishData = publishData;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public Long getPostViews() {
        return postViews;
    }

    public void setPostViews(Long postViews) {
        this.postViews = postViews;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }
}
