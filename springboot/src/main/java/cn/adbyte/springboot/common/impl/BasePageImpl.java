package cn.adbyte.springboot.common.impl;

import cn.adbyte.springboot.common.constant.SysConstant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Adam Yao on 2017/12/14.
 */
public class BasePageImpl implements Serializable {

    private Integer page;
    private Integer limit;
    private Sort sort;
    private PageRequest requestPage;

    public BasePageImpl() {
        this.page = SysConstant.DEF_PAGE;
        this.limit = SysConstant.DEF_SIZE;
        this.sort = new Sort(Sort.Direction.DESC, "id");//待封装排序
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public PageRequest getRequestPage() {
        return PageRequest.of(this.page - 1, this.limit, this.sort);
    }

    public void setRequestPage(PageRequest requestPage) {
        this.requestPage = requestPage;
    }

    @Override
    public String toString() {
        return "BasePageImpl{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort=" + sort +
                ", requestPage=" + requestPage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasePageImpl basePage = (BasePageImpl) o;

        if (page != basePage.page) return false;
        if (limit != basePage.limit) return false;
        return sort != null ? sort.equals(basePage.sort) : basePage.sort == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + limit;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }
}