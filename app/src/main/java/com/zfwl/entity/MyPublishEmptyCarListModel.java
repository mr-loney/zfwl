package com.zfwl.entity;

import java.io.Serializable;
import java.util.List;

public class MyPublishEmptyCarListModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;


	/**
	 * totalCount : 1
	 * pageSize : 10
	 * pageNo : 1
	 * filterNo : 0
	 * list : [{"id":4,"memberId":1,"fromProvinceId":110000,"fromCityId":110100,"fromCountyId":110101,"fromAddressName":"北京市-市辖区-东城区 aaaa","goDate":"2016-12-29 10:00:00.0","carNumber":5,"loadNumber":10,"carLength":15,"cdate":1482908283000,"mdate":1482908282000,"remark":null,"emptyCarAddressList":[{"id":2,"emptyCarId":4,"toProvinceId":120000,"toCityId":120100,"toCountyId":0,"toAddressName":"滨海","toProvinceName":"天津市","toCityName":"市辖区","toCountyName":null},{"id":1,"emptyCarId":4,"toProvinceId":110000,"toCityId":110100,"toCountyId":110112,"toAddressName":"大运河","toProvinceName":"北京市","toCityName":"市辖区","toCountyName":"通州区"}]}]
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

	public static class ListBean implements Serializable {

		private static final long serialVersionUID = -3718423961923385890L;
		/**
		 * id : 4
		 * memberId : 1
		 * fromProvinceId : 110000
		 * fromCityId : 110100
		 * fromCountyId : 110101
		 * fromAddressName : 北京市-市辖区-东城区 aaaa
		 * goDate : 2016-12-29 10:00:00.0
		 * carNumber : 5
		 * loadNumber : 10
		 * carLength : 15
		 * cdate : 1482908283000
		 * mdate : 1482908282000
		 * remark : null
		 * emptyCarAddressList : [{"id":2,"emptyCarId":4,"toProvinceId":120000,"toCityId":120100,"toCountyId":0,"toAddressName":"滨海","toProvinceName":"天津市","toCityName":"市辖区","toCountyName":null},{"id":1,"emptyCarId":4,"toProvinceId":110000,"toCityId":110100,"toCountyId":110112,"toAddressName":"大运河","toProvinceName":"北京市","toCityName":"市辖区","toCountyName":"通州区"}]
		 */

		private int id;
		private int memberId;
		private int fromProvinceId;
		private int fromCityId;
		private int fromCountyId;
		private String fromAddressName;
		private long goDate;
		private int carNumber;
		private int loadNumber;
		private int carLength;
		private long cdate;
		private long mdate;
		private Object remark;
		private List<EmptyCarAddressListBean> emptyCarAddressList;

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

		public int getFromProvinceId() {
			return fromProvinceId;
		}

		public void setFromProvinceId(int fromProvinceId) {
			this.fromProvinceId = fromProvinceId;
		}

		public int getFromCityId() {
			return fromCityId;
		}

		public void setFromCityId(int fromCityId) {
			this.fromCityId = fromCityId;
		}

		public int getFromCountyId() {
			return fromCountyId;
		}

		public void setFromCountyId(int fromCountyId) {
			this.fromCountyId = fromCountyId;
		}

		public String getFromAddressName() {
			return fromAddressName;
		}

		public void setFromAddressName(String fromAddressName) {
			this.fromAddressName = fromAddressName;
		}

		public long getGoDate() {
			return goDate;
		}

		public void setGoDate(long goDate) {
			this.goDate = goDate;
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

		public int getCarLength() {
			return carLength;
		}

		public void setCarLength(int carLength) {
			this.carLength = carLength;
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

		public Object getRemark() {
			return remark;
		}

		public void setRemark(Object remark) {
			this.remark = remark;
		}

		public List<EmptyCarAddressListBean> getEmptyCarAddressList() {
			return emptyCarAddressList;
		}

		public void setEmptyCarAddressList(List<EmptyCarAddressListBean> emptyCarAddressList) {
			this.emptyCarAddressList = emptyCarAddressList;
		}

		public static class EmptyCarAddressListBean implements Serializable {

			private static final long serialVersionUID = -3718423961923385891L;
			/**
			 * id : 2
			 * emptyCarId : 4
			 * toProvinceId : 120000
			 * toCityId : 120100
			 * toCountyId : 0
			 * toAddressName : 滨海
			 * toProvinceName : 天津市
			 * toCityName : 市辖区
			 * toCountyName : null
			 */

			private int id;
			private int emptyCarId;
			private int toProvinceId;
			private int toCityId;
			private int toCountyId;
			private String toAddressName;
			private String toProvinceName;
			private String toCityName;
			private Object toCountyName;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getEmptyCarId() {
				return emptyCarId;
			}

			public void setEmptyCarId(int emptyCarId) {
				this.emptyCarId = emptyCarId;
			}

			public int getToProvinceId() {
				return toProvinceId;
			}

			public void setToProvinceId(int toProvinceId) {
				this.toProvinceId = toProvinceId;
			}

			public int getToCityId() {
				return toCityId;
			}

			public void setToCityId(int toCityId) {
				this.toCityId = toCityId;
			}

			public int getToCountyId() {
				return toCountyId;
			}

			public void setToCountyId(int toCountyId) {
				this.toCountyId = toCountyId;
			}

			public String getToAddressName() {
				return toAddressName;
			}

			public void setToAddressName(String toAddressName) {
				this.toAddressName = toAddressName;
			}

			public String getToProvinceName() {
				return toProvinceName;
			}

			public void setToProvinceName(String toProvinceName) {
				this.toProvinceName = toProvinceName;
			}

			public String getToCityName() {
				return toCityName;
			}

			public void setToCityName(String toCityName) {
				this.toCityName = toCityName;
			}

			public Object getToCountyName() {
				return toCountyName;
			}

			public void setToCountyName(Object toCountyName) {
				this.toCountyName = toCountyName;
			}
		}
	}
}
