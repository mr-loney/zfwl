package com.zfwl.entity;

import java.util.List;

public class WJModel {

	/**
	 * totalCount : 1
	 * pageSize : 10
	 * pageNo : 1
	 * filterNo : 0
	 * list : [{"id":3,"title":"(╯\u2035□\u2032)╯︵┻━┻我跟你讲 我就这个表情","details":"へ~不开心 (#~～~#)","createTime":"2016-12-06 11:12:58","status":0,"lookCount":0,"answerCount":2}]
	 * firstResult : 0
	 * totalPage : 1
	 * firstPage : true
	 * lastPage : true
	 * nextPage : 1
	 * prePage : 1
	 */

	private int totalCount;
	private int pageSize;
	private int pageNo;
	private int filterNo;
	private int firstResult;
	private int totalPage;
	private boolean firstPage;
	private boolean lastPage;
	private int nextPage;
	private int prePage;
	private List<ListBean> list;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getFilterNo() {
		return filterNo;
	}

	public void setFilterNo(int filterNo) {
		this.filterNo = filterNo;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * id : 3
		 * title : (╯‵□′)╯︵┻━┻我跟你讲 我就这个表情
		 * details : へ~不开心 (#~～~#)
		 * createTime : 2016-12-06 11:12:58
		 * status : 0
		 * lookCount : 0
		 * answerCount : 2
		 */

		private int id;
		private String title;
		private String details;
		private String createTime;
		private int status;
		private int lookCount;
		private int answerCount;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getLookCount() {
			return lookCount;
		}

		public void setLookCount(int lookCount) {
			this.lookCount = lookCount;
		}

		public int getAnswerCount() {
			return answerCount;
		}

		public void setAnswerCount(int answerCount) {
			this.answerCount = answerCount;
		}
	}
}
