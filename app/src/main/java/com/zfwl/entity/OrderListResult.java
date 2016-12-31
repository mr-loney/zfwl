package com.zfwl.entity;

import java.util.List;

/**
 * Created by ZZB on 2016/12/31.
 */
public class OrderListResult {
    public int totalCount;
    public int pageSize;
    public int pageNo;
    public int filterNo;
    public List<Order> list;
    public int firstResult;
    public int totalPage;
    public boolean firstPage;
    public boolean lastPage;
    public int nextPage;
    public int prePage;
}
