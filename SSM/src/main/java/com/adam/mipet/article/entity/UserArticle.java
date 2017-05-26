package com.adam.mipet.article.entity;

import java.util.Date;
import java.util.List;

public class UserArticle {
    private Integer id;//文章ID
    private Integer portion;//版块
    private Integer userId;//用户ID
    private String title;//文章标题
    private String htmlContent;//文章内容
    private Integer likes;//点赞数
    private Integer comments;//评论数
    private Date pubTime;//发布时间
    private Integer status;//状态
    private Integer deleted;//是否删除
    private Date updateTime;//更新时间
    private String nickname;//文件用户昵称
    private String headPic;//用户头像
    private Boolean isLiked;//是否点赞
    private Boolean isFavs;//是否已收藏
    private List<ArticleImage> images;//文章包含的图片
	
    
	
	
	public Boolean getIsLiked() {
	
		return isLiked;
	}


	
	public void setIsLiked(Boolean isLiked) {
	
		this.isLiked = isLiked;
	}


	
	public Boolean getIsFavs() {
	
		return isFavs;
	}


	
	public void setIsFavs(Boolean isFavs) {
	
		this.isFavs = isFavs;
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


	public List<ArticleImage> getImages() {
	
		return images;
	}

	
	public void setImages(List<ArticleImage> images) {
	
		this.images = images;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPortion() {
        return portion;
    }

    public void setPortion(Integer portion) {
        this.portion = portion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}