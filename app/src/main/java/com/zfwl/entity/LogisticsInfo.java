package com.zfwl.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsInfo implements Serializable{


    /**
     * totalCount : 1
     * pageSize : 10
     * pageNo : 1
     * filterNo : 0
     * list : [{"id":1,"goodsName":"物流信息","weight":50,"length":120,"carNum":2,"departureTime":1482461637000,"isLargeGo":0,"contactWay":null,"remark":null,"addressInfoList":[{"id":1,"logisticsInfoId":1,"fromProvinceId":110000,"fromCityId":110100,"fromCountyId":110101,"toProvinceId":420000,"toCityId":422800,"toCountyId":422801,"fromDetail":"A区","toDetail":"B区","fromProvinceName":"北京市","fromCityName":"北京市","fromCountyName":"海淀区","toProvinceName":"天津市","toCityName":"天津市","toCountyName":"静海区"}]}]
     * firstResult : 0
     * prePage : 1
     * nextPage : 1
     * lastPage : true
     * firstPage : true
     * totalPage : 1
     */

    private int totalCount;
    private int pageSize;
    private int pageNo;
    private int filterNo;
    private int firstResult;
    private int prePage;
    private int nextPage;
    private boolean lastPage;
    private boolean firstPage;
    private int totalPage;
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

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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
         * goodsName : 物流信息
         * weight : 50
         * length : 120
         * carNum : 2
         * departureTime : 1482461637000
         * isLargeGo : 0
         * contactWay : null
         * remark : null
         * addressInfoList : [{"id":1,"logisticsInfoId":1,"fromProvinceId":110000,"fromCityId":110100,"fromCountyId":110101,"toProvinceId":420000,"toCityId":422800,"toCountyId":422801,"fromDetail":"A区","toDetail":"B区","fromProvinceName":"北京市","fromCityName":"北京市","fromCountyName":"海淀区","toProvinceName":"天津市","toCityName":"天津市","toCountyName":"静海区"}]
         */

        private int id;
        private String goodsName;
        private int weight;
        private int length;
        private int carNum;
        private String departureTime;
        private int isLargeGo;
        private String contactWay;
        private String remark;
        private List<AddressInfoListBean> addressInfoList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getCarNum() {
            return carNum;
        }

        public void setCarNum(int carNum) {
            this.carNum = carNum;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public int getIsLargeGo() {
            return isLargeGo;
        }

        public void setIsLargeGo(int isLargeGo) {
            this.isLargeGo = isLargeGo;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<AddressInfoListBean> getAddressInfoList() {
            return addressInfoList;
        }

        public void setAddressInfoList(List<AddressInfoListBean> addressInfoList) {
            this.addressInfoList = addressInfoList;
        }

        public static class AddressInfoListBean implements Serializable {
            /**
             * id : 1
             * logisticsInfoId : 1
             * fromProvinceId : 110000
             * fromCityId : 110100
             * fromCountyId : 110101
             * toProvinceId : 420000
             * toCityId : 422800
             * toCountyId : 422801
             * fromDetail : A区
             * toDetail : B区
             * fromProvinceName : 北京市
             * fromCityName : 北京市
             * fromCountyName : 海淀区
             * toProvinceName : 天津市
             * toCityName : 天津市
             * toCountyName : 静海区
             */

            private int id;
            private int logisticsInfoId;
            private int fromProvinceId;
            private int fromCityId;
            private int fromCountyId;
            private int toProvinceId;
            private int toCityId;
            private int toCountyId;
            private String fromDetail;
            private String toDetail;
            private String fromProvinceName;
            private String fromCityName;
            private String fromCountyName;
            private String toProvinceName;
            private String toCityName;
            private String toCountyName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLogisticsInfoId() {
                return logisticsInfoId;
            }

            public void setLogisticsInfoId(int logisticsInfoId) {
                this.logisticsInfoId = logisticsInfoId;
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
