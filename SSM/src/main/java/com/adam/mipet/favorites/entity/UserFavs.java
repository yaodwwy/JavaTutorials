package com.adam.mipet.favorites.entity;

import java.util.Date;

public class UserFavs {
	private	Integer	id;
	private	Integer	articleId; //文章ID
	private	Integer	userId; //用户表ID
	private	Date	collectTime; //收藏时间
    private String title;//文章标题
    private String htmlContent;//文章内容
    private Integer likes;//点赞数
    private Integer comments;//评论数
    private Date pubTime;//发布时间
    private String nickname;//文件用户昵称
    private String headPic;//用户头像
    private Integer deleted;
    private Date updateTime;
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getId() {
	
		return id;
	}
	
	public void setId(Integer id) {
	
		this.id = id;
	}
	
	
	
	public Integer getArticleId() {
	
		return articleId;
	}

	
	public void setArticleId(Integer articleId) {
	
		this.articleId = articleId;
	}

	public Integer getUserId() {
	
		return userId;
	}
	
	public void setUserId(Integer userId) {
	
		this.userId = userId;
	}
	
	public Date getCollectTime() {
	
		return collectTime;
	}
	
	public void setCollectTime(Date collectTime) {
	
		this.collectTime = collectTime;
	}
	
	public String getTitle() {
	
		return title;
	}
	
	public void setTitle(String title) {
	
		this.title = title;
	}
	
	public String getHtmlContent() {
	
		return htmlContent;
	}
	
	public void setHtmlContent(String htmlContent) {
	
		this.htmlContent = htmlContent;
	}
	
	public Integer getLikes() {
	
		return likes;
	}
	
	public void setLikes(Integer likes) {
	
		this.likes = likes;
	}
	
	public Integer getComments() {
	
		return comments;
	}
	
	public void setComments(Integer comments) {
	
		this.comments = comments;
	}
	
	public Date getPubTime() {
	
		return pubTime;
	}
	
	public void setPubTime(Date pubTime) {
	
		this.pubTime = pubTime;
	}
	
	public String getNickname() {
	
		return nickname;
	}
	
	public void setNickname(String nickname) {
	
		this.nickname = nickname;
	}
	
	public String getHeadPic() {
	
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
	
		this.headPic = headPic;
	}

  
}