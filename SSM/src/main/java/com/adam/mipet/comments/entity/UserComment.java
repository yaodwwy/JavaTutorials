package com.adam.mipet.comments.entity;

import java.util.Date;

public class UserComment {
    private Integer id;

    private Integer articleId;

    private Integer userId;

    private String content;

    private Date pubTime;

    private Integer deleted;

    private Date updateTime;
    
    private String headPic;
    
    private String nickname;
    
    

    
	public String getHeadPic() {
	
		return headPic;
	}

	
	public void setHeadPic(String headPic) {
	
		this.headPic = headPic;
	}

	
	public String getNickname() {
	
		return nickname;
	}

	
	public void setNickname(String nickname) {
	
		this.nickname = nickname;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
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