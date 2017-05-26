package com.adam.mipet.article.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserArticleExample {
	
	protected String orderByClause;
	
	protected boolean distinct;
	
	protected List<Criteria> oredCriteria;
	
	private Integer id;// 查询单个文章的时候必须参数
	
	private Integer portion;
	
	private String searchinput;
	
	private int start;
	
	private int limit;
	
	private Integer userId;// 查询单个文章的时候,当前登录的用户ID
	
	private String order; //排序方式
	
	private String sort; //字段名
	
	
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getUserId() {
		if(this.id!=null && this.id == -1){
			userId = null;
		}
		return userId;
	}
	
	public void setUserId(Integer userId) {
	
		this.userId = userId;
	}
	
	public String getSearchinput() {
	
		return searchinput;
	}
	
	public void setSearchinput(String searchinput) {
	
		this.searchinput = searchinput;
	}
	
	public Integer getPortion() {
	
		return portion;
	}
	
	public void setPortion(Integer portion) {
	
		this.portion = portion;
	}
	
	public Integer getId() {
	
		return id;
	}
	
	public void setId(Integer id) {
	
		this.id = id;
	}
	
	public int getStart() {
	
		return start;
	}
	
	public void setStart(int start) {
	
		this.start = start;
	}
	
	public int getLimit() {
	
		return limit;
	}
	
	public void setLimit(int limit) {
	
		this.limit = limit;
	}
	
	public UserArticleExample() {
	
		oredCriteria = new ArrayList<Criteria>();
	}
	
	public void setOrderByClause(String orderByClause) {
	
		this.orderByClause = orderByClause;
	}
	
	public String getOrderByClause() {
	
		return orderByClause;
	}
	
	public void setDistinct(boolean distinct) {
	
		this.distinct = distinct;
	}
	
	public boolean isDistinct() {
	
		return distinct;
	}
	
	public List<Criteria> getOredCriteria() {
	
		return oredCriteria;
	}
	
	public void or(Criteria criteria) {
	
		oredCriteria.add(criteria);
	}
	
	public Criteria or() {
	
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}
	
	public Criteria createCriteria() {
	
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	protected Criteria createCriteriaInternal() {
	
		Criteria criteria = new Criteria();
		return criteria;
	}
	
	public void clear() {
	
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}
	
	protected abstract static class GeneratedCriteria {
		
		protected List<Criterion> criteria;
		
		protected GeneratedCriteria() {
		
			super();
			criteria = new ArrayList<Criterion>();
		}
		
		public boolean isValid() {
		
			return criteria.size() > 0;
		}
		
		public List<Criterion> getAllCriteria() {
		
			return criteria;
		}
		
		public List<Criterion> getCriteria() {
		
			return criteria;
		}
		
		protected void addCriterion(String condition) {
		
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}
		
		protected void addCriterion(String condition, Object value, String property) {
		
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}
		
		protected void addCriterion(String condition, Object value1, Object value2, String property) {
		
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}
		
		public Criteria andIdIsNull() {
		
			addCriterion("id is null");
			return (Criteria) this;
		}
		
		public Criteria andIdIsNotNull() {
		
			addCriterion("id is not null");
			return (Criteria) this;
		}
		
		public Criteria andIdEqualTo(Integer value) {
		
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotEqualTo(Integer value) {
		
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThan(Integer value) {
		
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThan(Integer value) {
		
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThanOrEqualTo(Integer value) {
		
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdIn(List<Integer> values) {
		
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotIn(List<Integer> values) {
		
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdBetween(Integer value1, Integer value2) {
		
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotBetween(Integer value1, Integer value2) {
		
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andPortionIsNull() {
		
			addCriterion("portion is null");
			return (Criteria) this;
		}
		
		public Criteria andPortionIsNotNull() {
		
			addCriterion("portion is not null");
			return (Criteria) this;
		}
		
		public Criteria andPortionEqualTo(Integer value) {
		
			addCriterion("portion =", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionNotEqualTo(Integer value) {
		
			addCriterion("portion <>", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionGreaterThan(Integer value) {
		
			addCriterion("portion >", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("portion >=", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionLessThan(Integer value) {
		
			addCriterion("portion <", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionLessThanOrEqualTo(Integer value) {
		
			addCriterion("portion <=", value, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionIn(List<Integer> values) {
		
			addCriterion("portion in", values, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionNotIn(List<Integer> values) {
		
			addCriterion("portion not in", values, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionBetween(Integer value1, Integer value2) {
		
			addCriterion("portion between", value1, value2, "portion");
			return (Criteria) this;
		}
		
		public Criteria andPortionNotBetween(Integer value1, Integer value2) {
		
			addCriterion("portion not between", value1, value2, "portion");
			return (Criteria) this;
		}
		
		public Criteria andUserIdIsNull() {
		
			addCriterion("user_id is null");
			return (Criteria) this;
		}
		
		public Criteria andUserIdIsNotNull() {
		
			addCriterion("user_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andUserIdEqualTo(Integer value) {
		
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdNotEqualTo(Integer value) {
		
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdGreaterThan(Integer value) {
		
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdLessThan(Integer value) {
		
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdLessThanOrEqualTo(Integer value) {
		
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdIn(List<Integer> values) {
		
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdNotIn(List<Integer> values) {
		
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdBetween(Integer value1, Integer value2) {
		
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}
		
		public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
		
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}
		
		public Criteria andTitleIsNull() {
		
			addCriterion("title is null");
			return (Criteria) this;
		}
		
		public Criteria andTitleIsNotNull() {
		
			addCriterion("title is not null");
			return (Criteria) this;
		}
		
		public Criteria andTitleEqualTo(String value) {
		
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleNotEqualTo(String value) {
		
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleGreaterThan(String value) {
		
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleGreaterThanOrEqualTo(String value) {
		
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleLessThan(String value) {
		
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleLessThanOrEqualTo(String value) {
		
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleLike(String value) {
		
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleNotLike(String value) {
		
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleIn(List<String> values) {
		
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleNotIn(List<String> values) {
		
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleBetween(String value1, String value2) {
		
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}
		
		public Criteria andTitleNotBetween(String value1, String value2) {
		
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentIsNull() {
		
			addCriterion("html_content is null");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentIsNotNull() {
		
			addCriterion("html_content is not null");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentEqualTo(String value) {
		
			addCriterion("html_content =", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentNotEqualTo(String value) {
		
			addCriterion("html_content <>", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentGreaterThan(String value) {
		
			addCriterion("html_content >", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentGreaterThanOrEqualTo(String value) {
		
			addCriterion("html_content >=", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentLessThan(String value) {
		
			addCriterion("html_content <", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentLessThanOrEqualTo(String value) {
		
			addCriterion("html_content <=", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentLike(String value) {
		
			addCriterion("html_content like", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentNotLike(String value) {
		
			addCriterion("html_content not like", value, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentIn(List<String> values) {
		
			addCriterion("html_content in", values, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentNotIn(List<String> values) {
		
			addCriterion("html_content not in", values, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentBetween(String value1, String value2) {
		
			addCriterion("html_content between", value1, value2, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andHtmlContentNotBetween(String value1, String value2) {
		
			addCriterion("html_content not between", value1, value2, "htmlContent");
			return (Criteria) this;
		}
		
		public Criteria andLikesIsNull() {
		
			addCriterion("likes is null");
			return (Criteria) this;
		}
		
		public Criteria andLikesIsNotNull() {
		
			addCriterion("likes is not null");
			return (Criteria) this;
		}
		
		public Criteria andLikesEqualTo(Integer value) {
		
			addCriterion("likes =", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesNotEqualTo(Integer value) {
		
			addCriterion("likes <>", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesGreaterThan(Integer value) {
		
			addCriterion("likes >", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("likes >=", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesLessThan(Integer value) {
		
			addCriterion("likes <", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesLessThanOrEqualTo(Integer value) {
		
			addCriterion("likes <=", value, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesIn(List<Integer> values) {
		
			addCriterion("likes in", values, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesNotIn(List<Integer> values) {
		
			addCriterion("likes not in", values, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesBetween(Integer value1, Integer value2) {
		
			addCriterion("likes between", value1, value2, "likes");
			return (Criteria) this;
		}
		
		public Criteria andLikesNotBetween(Integer value1, Integer value2) {
		
			addCriterion("likes not between", value1, value2, "likes");
			return (Criteria) this;
		}
		
		public Criteria andCommentsIsNull() {
		
			addCriterion("comments is null");
			return (Criteria) this;
		}
		
		public Criteria andCommentsIsNotNull() {
		
			addCriterion("comments is not null");
			return (Criteria) this;
		}
		
		public Criteria andCommentsEqualTo(Integer value) {
		
			addCriterion("comments =", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsNotEqualTo(Integer value) {
		
			addCriterion("comments <>", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsGreaterThan(Integer value) {
		
			addCriterion("comments >", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("comments >=", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsLessThan(Integer value) {
		
			addCriterion("comments <", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsLessThanOrEqualTo(Integer value) {
		
			addCriterion("comments <=", value, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsIn(List<Integer> values) {
		
			addCriterion("comments in", values, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsNotIn(List<Integer> values) {
		
			addCriterion("comments not in", values, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsBetween(Integer value1, Integer value2) {
		
			addCriterion("comments between", value1, value2, "comments");
			return (Criteria) this;
		}
		
		public Criteria andCommentsNotBetween(Integer value1, Integer value2) {
		
			addCriterion("comments not between", value1, value2, "comments");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeIsNull() {
		
			addCriterion("pub_time is null");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeIsNotNull() {
		
			addCriterion("pub_time is not null");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeEqualTo(Date value) {
		
			addCriterion("pub_time =", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeNotEqualTo(Date value) {
		
			addCriterion("pub_time <>", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeGreaterThan(Date value) {
		
			addCriterion("pub_time >", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeGreaterThanOrEqualTo(Date value) {
		
			addCriterion("pub_time >=", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeLessThan(Date value) {
		
			addCriterion("pub_time <", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeLessThanOrEqualTo(Date value) {
		
			addCriterion("pub_time <=", value, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeIn(List<Date> values) {
		
			addCriterion("pub_time in", values, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeNotIn(List<Date> values) {
		
			addCriterion("pub_time not in", values, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeBetween(Date value1, Date value2) {
		
			addCriterion("pub_time between", value1, value2, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andPubTimeNotBetween(Date value1, Date value2) {
		
			addCriterion("pub_time not between", value1, value2, "pubTime");
			return (Criteria) this;
		}
		
		public Criteria andStatusIsNull() {
		
			addCriterion("status is null");
			return (Criteria) this;
		}
		
		public Criteria andStatusIsNotNull() {
		
			addCriterion("status is not null");
			return (Criteria) this;
		}
		
		public Criteria andStatusEqualTo(Integer value) {
		
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusNotEqualTo(Integer value) {
		
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusGreaterThan(Integer value) {
		
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusLessThan(Integer value) {
		
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusLessThanOrEqualTo(Integer value) {
		
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusIn(List<Integer> values) {
		
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusNotIn(List<Integer> values) {
		
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusBetween(Integer value1, Integer value2) {
		
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}
		
		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
		
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}
		
		public Criteria andDeletedIsNull() {
		
			addCriterion("deleted is null");
			return (Criteria) this;
		}
		
		public Criteria andDeletedIsNotNull() {
		
			addCriterion("deleted is not null");
			return (Criteria) this;
		}
		
		public Criteria andDeletedEqualTo(Integer value) {
		
			addCriterion("deleted =", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedNotEqualTo(Integer value) {
		
			addCriterion("deleted <>", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedGreaterThan(Integer value) {
		
			addCriterion("deleted >", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
		
			addCriterion("deleted >=", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedLessThan(Integer value) {
		
			addCriterion("deleted <", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
		
			addCriterion("deleted <=", value, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedIn(List<Integer> values) {
		
			addCriterion("deleted in", values, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedNotIn(List<Integer> values) {
		
			addCriterion("deleted not in", values, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedBetween(Integer value1, Integer value2) {
		
			addCriterion("deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
		
			addCriterion("deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeIsNull() {
		
			addCriterion("update_time is null");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeIsNotNull() {
		
			addCriterion("update_time is not null");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeEqualTo(Date value) {
		
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeNotEqualTo(Date value) {
		
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeGreaterThan(Date value) {
		
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
		
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeLessThan(Date value) {
		
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
		
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeIn(List<Date> values) {
		
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeNotIn(List<Date> values) {
		
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
		
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}
		
		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
		
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}
	}
	
	public static class Criteria extends GeneratedCriteria {
		
		protected Criteria() {
		
			super();
		}
	}
	
	public static class Criterion {
		
		private String condition;
		
		private Object value;
		
		private Object secondValue;
		
		private boolean noValue;
		
		private boolean singleValue;
		
		private boolean betweenValue;
		
		private boolean listValue;
		
		private String typeHandler;
		
		public String getCondition() {
		
			return condition;
		}
		
		public Object getValue() {
		
			return value;
		}
		
		public Object getSecondValue() {
		
			return secondValue;
		}
		
		public boolean isNoValue() {
		
			return noValue;
		}
		
		public boolean isSingleValue() {
		
			return singleValue;
		}
		
		public boolean isBetweenValue() {
		
			return betweenValue;
		}
		
		public boolean isListValue() {
		
			return listValue;
		}
		
		public String getTypeHandler() {
		
			return typeHandler;
		}
		
		protected Criterion(String condition) {
		
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}
		
		protected Criterion(String condition, Object value, String typeHandler) {
		
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}
		
		protected Criterion(String condition, Object value) {
		
			this(condition, value, null);
		}
		
		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
		
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}
		
		protected Criterion(String condition, Object value, Object secondValue) {
		
			this(condition, value, secondValue, null);
		}
	}
}