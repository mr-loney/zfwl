package com.zfwl.entity;

import java.io.Serializable;
import java.util.List;

public class MyQuotedModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;


	/**
	 * totalCount : 1
	 * pageSize : 10
	 * pageNo : 1
	 * filterNo : 0
	 * list : [{"id":1,"memberId":1,"carNumber":8,"loadNumber":13,"priceType":1,"price":23,"total":299,"status":0,"cdate":1481987874000,"mdate":1481987874000,"remark":""}]
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
		 * id : 1
		 * memberId : 1
		 * carNumber : 8
		 * loadNumber : 13
		 * priceType : 1
		 * price : 23
		 * total : 299
		 * status : 0
		 * cdate : 1481987874000
		 * mdate : 1481987874000
		 * remark :
		 */

		private int id;
		private int memberId;
		private int carNumber;
		private int loadNumber;
		private int priceType;
		private double price;
		private double total;
		private int status;
		private long cdate;
		private long mdate;
		private String remark;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getMemberId() {
			return memberId;
		}

		public void setMemberId(int memberId) {
			this.memberId = memberId;
		}

		public int getCarNumber() {
			return carNumber;
		}

		public void setCarNumber(int carNumber) {
			this.carNumber = carNumber;
		}

		public int getLoadNumber() {
			return loadNumber;
		}

		public void setLoadNumber(int loadNumber) {
			this.loadNumber = loadNumber;
		}

		public int getPriceType() {
			return priceType;
		}

		public void setPriceType(int priceType) {
			this.priceType = priceType;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public long getCdate() {
			return cdate;
		}

		public void setCdate(long cdate) {
			this.cdate = cdate;
		}

		public long getMdate() {
			return mdate;
		}

		public void setMdate(long mdate) {
			this.mdate = mdate;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}
}
