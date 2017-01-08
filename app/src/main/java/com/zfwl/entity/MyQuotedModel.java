package com.zfwl.entity;

import java.io.Serializable;
import java.util.List;

public class MyQuotedModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;


	/**
	 * totalCount : 7
	 * pageSize : 10
	 * pageNo : 1
	 * filterNo : 0
	 * list : [{"id":5,"memberId":1,"carNumber":1,"loadNumber":1,"priceType":0,"price":1,"total":0,"status":1,"cdate":1483834303000,"mdate":1483834303000,"remark":"","logisticsId":4,"breachNum":null,"successOfferNum":null,"offerNum":null,"account":null,"goodsName":"兔子","length":null,"departureTime":null,"address":null,"succAndTotal":null,"weight":null,"isLargeGo":null,"carNum":null,"fromAddress":null,"toAddress":null,"logisticsInfo":null,"addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"你说呢","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"山西省","toCityName":"长治市","toCountyName":"长治县"},{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"哈哈","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"上海市","toCityName":"市辖区","toCountyName":"闸北区"}]},{"id":4,"memberId":1,"carNumber":4,"loadNumber":1,"priceType":0,"price":1,"total":0,"status":1,"cdate":1483833303000,"mdate":1483833303000,"remark":"","logisticsId":4,"breachNum":null,"successOfferNum":null,"offerNum":null,"account":null,"goodsName":"兔子","length":null,"departureTime":null,"address":null,"succAndTotal":null,"weight":null,"isLargeGo":null,"carNum":null,"fromAddress":null,"toAddress":null,"logisticsInfo":null,"addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"你说呢","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"山西省","toCityName":"长治市","toCountyName":"长治县"},{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"哈哈","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"上海市","toCityName":"市辖区","toCountyName":"闸北区"}]},{"id":3,"memberId":2,"carNumber":3,"loadNumber":9,"priceType":1,"price":89,"total":801,"status":1,"cdate":1483799042000,"mdate":1483799042000,"remark":"","logisticsId":3,"breachNum":null,"successOfferNum":null,"offerNum":null,"account":null,"goodsName":"一车美女","length":null,"departureTime":null,"address":null,"succAndTotal":null,"weight":null,"isLargeGo":null,"carNum":null,"fromAddress":null,"toAddress":null,"logisticsInfo":null,"addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"万商大厦","toDetail":"黄焖鸡米饭街道","fromProvinceName":"北京市","fromCityName":"市辖区","fromCountyName":"石景山区","toProvinceName":"山西省","toCityName":"太原市","toCountyName":"杏花岭区"}]},{"id":2,"memberId":1,"carNumber":1,"loadNumber":1,"priceType":0,"price":1,"total":0,"status":1,"cdate":1483797655000,"mdate":1483797655000,"remark":"","logisticsId":3,"breachNum":null,"successOfferNum":null,"offerNum":null,"account":null,"goodsName":"一车美女","length":null,"departureTime":null,"address":null,"succAndTotal":null,"weight":null,"isLargeGo":null,"carNum":null,"fromAddress":null,"toAddress":null,"logisticsInfo":null,"addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"万商大厦","toDetail":"黄焖鸡米饭街道","fromProvinceName":"北京市","fromCityName":"市辖区","fromCountyName":"石景山区","toProvinceName":"山西省","toCityName":"太原市","toCountyName":"杏花岭区"}]},{"id":1,"memberId":1,"carNumber":6,"loadNumber":15,"priceType":0,"price":15,"total":0,"status":1,"cdate":1483758747000,"mdate":1483758747000,"remark":"","logisticsId":1,"breachNum":null,"successOfferNum":null,"offerNum":null,"account":null,"goodsName":"大米","length":null,"departureTime":null,"address":null,"succAndTotal":null,"weight":null,"isLargeGo":null,"carNum":null,"fromAddress":null,"toAddress":null,"logisticsInfo":null,"addressInfoList":[{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"高碑店","toDetail":"某某大街","fromProvinceName":"北京市","fromCityName":"市辖区","fromCountyName":"朝阳区","toProvinceName":"天津市","toCityName":"市辖区","toCountyName":"和平区"}]}]
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

		private static final long serialVersionUID = -3718423961923385894L;

		/**
		 * id : 5
		 * memberId : 1
		 * carNumber : 1
		 * loadNumber : 1
		 * priceType : 0
		 * price : 1
		 * total : 0
		 * status : 1
		 * cdate : 1483834303000
		 * mdate : 1483834303000
		 * remark :
		 * logisticsId : 4
		 * breachNum : null
		 * successOfferNum : null
		 * offerNum : null
		 * account : null
		 * goodsName : 兔子
		 * length : null
		 * departureTime : null
		 * address : null
		 * succAndTotal : null
		 * weight : null
		 * isLargeGo : null
		 * carNum : null
		 * fromAddress : null
		 * toAddress : null
		 * logisticsInfo : null
		 * addressInfoList : [{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"你说呢","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"山西省","toCityName":"长治市","toCountyName":"长治县"},{"id":null,"logisticsInfoId":null,"fromProvinceId":null,"fromCityId":null,"fromCountyId":null,"toProvinceId":null,"toCityId":null,"toCountyId":null,"fromDetail":"不知道","toDetail":"哈哈","fromProvinceName":"内蒙古自治区","fromCityName":"乌海市","fromCountyName":"乌达区","toProvinceName":"上海市","toCityName":"市辖区","toCountyName":"闸北区"}]
		 */

		private int id;
		private int memberId;
		private double carNumber;
		private double loadNumber;
		private int priceType;
		private double price;
		private double total;
		private int status;
		private long cdate;
		private long mdate;
		private String remark;
		private int logisticsId;
		private Object breachNum;
		private Object successOfferNum;
		private Object offerNum;
		private Object account;
		private String goodsName;
		private Object length;
		private Object departureTime;
		private Object address;
		private Object succAndTotal;
		private Object weight;
		private boolean isLargeGo;
		private Object carNum;
		private Object fromAddress;
		private Object toAddress;
		private Object logisticsInfo;
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

		public double getCarNumber() {
			return carNumber;
		}

		public void setCarNumber(double carNumber) {
			this.carNumber = carNumber;
		}

		public double getLoadNumber() {
			return loadNumber;
		}

		public void setLoadNumber(double loadNumber) {
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

		public int getLogisticsId() {
			return logisticsId;
		}

		public void setLogisticsId(int logisticsId) {
			this.logisticsId = logisticsId;
		}

		public Object getBreachNum() {
			return breachNum;
		}

		public void setBreachNum(Object breachNum) {
			this.breachNum = breachNum;
		}

		public Object getSuccessOfferNum() {
			return successOfferNum;
		}

		public void setSuccessOfferNum(Object successOfferNum) {
			this.successOfferNum = successOfferNum;
		}

		public Object getOfferNum() {
			return offerNum;
		}

		public void setOfferNum(Object offerNum) {
			this.offerNum = offerNum;
		}

		public Object getAccount() {
			return account;
		}

		public void setAccount(Object account) {
			this.account = account;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public Object getLength() {
			return length;
		}

		public void setLength(Object length) {
			this.length = length;
		}

		public Object getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(Object departureTime) {
			this.departureTime = departureTime;
		}

		public Object getAddress() {
			return address;
		}

		public void setAddress(Object address) {
			this.address = address;
		}

		public Object getSuccAndTotal() {
			return succAndTotal;
		}

		public void setSuccAndTotal(Object succAndTotal) {
			this.succAndTotal = succAndTotal;
		}

		public Object getWeight() {
			return weight;
		}

		public void setWeight(Object weight) {
			this.weight = weight;
		}

		public boolean getIsLargeGo() {
			return isLargeGo;
		}

		public void setIsLargeGo(boolean isLargeGo) {
			this.isLargeGo = isLargeGo;
		}

		public Object getCarNum() {
			return carNum;
		}

		public void setCarNum(Object carNum) {
			this.carNum = carNum;
		}

		public Object getFromAddress() {
			return fromAddress;
		}

		public void setFromAddress(Object fromAddress) {
			this.fromAddress = fromAddress;
		}

		public Object getToAddress() {
			return toAddress;
		}

		public void setToAddress(Object toAddress) {
			this.toAddress = toAddress;
		}

		public Object getLogisticsInfo() {
			return logisticsInfo;
		}

		public void setLogisticsInfo(Object logisticsInfo) {
			this.logisticsInfo = logisticsInfo;
		}

		public List<AddressInfoListBean> getAddressInfoList() {
			return addressInfoList;
		}

		public void setAddressInfoList(List<AddressInfoListBean> addressInfoList) {
			this.addressInfoList = addressInfoList;
		}

		public static class AddressInfoListBean implements Serializable {

			private static final long serialVersionUID = -3718423961923385896L;

			/**
			 * id : null
			 * logisticsInfoId : null
			 * fromProvinceId : null
			 * fromCityId : null
			 * fromCountyId : null
			 * toProvinceId : null
			 * toCityId : null
			 * toCountyId : null
			 * fromDetail : 不知道
			 * toDetail : 你说呢
			 * fromProvinceName : 内蒙古自治区
			 * fromCityName : 乌海市
			 * fromCountyName : 乌达区
			 * toProvinceName : 山西省
			 * toCityName : 长治市
			 * toCountyName : 长治县
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
