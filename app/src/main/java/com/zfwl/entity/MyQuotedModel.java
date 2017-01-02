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
	 * list : [{"id":1,"memberId":1,"carNumber":8,"loadNumber":13,"priceType":1,"price":23,"total":299,"status":0,"cdate":1481987874000,"mdate":1481987874000,"remark":"","goodsName":"物流测试","addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"某某大街","toDetail":"某某大街","fromProvinceName":"天津市","fromCityName":"县","fromCountyName":"宁河县","toProvinceName":"山西省","toCityName":"阳泉市","toCountyName":"矿区"}]}]
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
		 * goodsName : 物流测试
		 * addressInfoList : [{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"某某大街","toDetail":"某某大街","fromProvinceName":"天津市","fromCityName":"县","fromCountyName":"宁河县","toProvinceName":"山西省","toCityName":"阳泉市","toCountyName":"矿区"}]
		 */

		private int id;
		private int memberId;
		private int carNumber;
		private int loadNumber;
		private int priceType;
		private int price;
		private int total;
		private int status;
		private long cdate;
		private long mdate;
		private String remark;
		private String goodsName;
		private List<AddressInfoListBean> addressInfoList;

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

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
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

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public List<AddressInfoListBean> getAddressInfoList() {
			return addressInfoList;
		}

		public void setAddressInfoList(List<AddressInfoListBean> addressInfoList) {
			this.addressInfoList = addressInfoList;
		}

		public static class AddressInfoListBean {
			/**
			 * id : null
			 * logisticsInfoId : null
			 * fromProvinceId : null
			 * fromCityId : null
			 * fromCountyId : null
			 * toProvinceId : null
			 * toCityId : null
			 * toCountyId : null
			 * fromDetail : 某某大街
			 * toDetail : 某某大街
			 * fromProvinceName : 天津市
			 * fromCityName : 县
			 * fromCountyName : 宁河县
			 * toProvinceName : 山西省
			 * toCityName : 阳泉市
			 * toCountyName : 矿区
			 */

			private Object id;
			private Object logisticsInfoId;
			private Object fromProvinceId;
			private Object fromCityId;
			private Object fromCountyId;
			private Object toProvinceId;
			private Object toCityId;
			private Object toCountyId;
			private String fromDetail;
			private String toDetail;
			private String fromProvinceName;
			private String fromCityName;
			private String fromCountyName;
			private String toProvinceName;
			private String toCityName;
			private String toCountyName;

			public Object getId() {
				return id;
			}

			public void setId(Object id) {
				this.id = id;
			}

			public Object getLogisticsInfoId() {
				return logisticsInfoId;
			}

			public void setLogisticsInfoId(Object logisticsInfoId) {
				this.logisticsInfoId = logisticsInfoId;
			}

			public Object getFromProvinceId() {
				return fromProvinceId;
			}

			public void setFromProvinceId(Object fromProvinceId) {
				this.fromProvinceId = fromProvinceId;
			}

			public Object getFromCityId() {
				return fromCityId;
			}

			public void setFromCityId(Object fromCityId) {
				this.fromCityId = fromCityId;
			}

			public Object getFromCountyId() {
				return fromCountyId;
			}

			public void setFromCountyId(Object fromCountyId) {
				this.fromCountyId = fromCountyId;
			}

			public Object getToProvinceId() {
				return toProvinceId;
			}

			public void setToProvinceId(Object toProvinceId) {
				this.toProvinceId = toProvinceId;
			}

			public Object getToCityId() {
				return toCityId;
			}

			public void setToCityId(Object toCityId) {
				this.toCityId = toCityId;
			}

			public Object getToCountyId() {
				return toCountyId;
			}

			public void setToCountyId(Object toCountyId) {
				this.toCountyId = toCountyId;
			}

			public String getFromDetail() {
				return fromDetail;
			}

			public void setFromDetail(String fromDetail) {
				this.fromDetail = fromDetail;
			}

			public String getToDetail() {
				return toDetail;
			}

			public void setToDetail(String toDetail) {
				this.toDetail = toDetail;
			}

			public String getFromProvinceName() {
				return fromProvinceName;
			}

			public void setFromProvinceName(String fromProvinceName) {
				this.fromProvinceName = fromProvinceName;
			}

			public String getFromCityName() {
				return fromCityName;
			}

			public void setFromCityName(String fromCityName) {
				this.fromCityName = fromCityName;
			}

			public String getFromCountyName() {
				return fromCountyName;
			}

			public void setFromCountyName(String fromCountyName) {
				this.fromCountyName = fromCountyName;
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

			public String getToCountyName() {
				return toCountyName;
			}

			public void setToCountyName(String toCountyName) {
				this.toCountyName = toCountyName;
			}
		}
	}
}
