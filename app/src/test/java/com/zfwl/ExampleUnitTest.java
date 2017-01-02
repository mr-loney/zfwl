package com.zfwl;

import com.zfwl.entity.OrderDetails;
import com.zfwl.util.GsonUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        String json = "{\"id\":10,\"memberId\":1,\"orderCode\":\"E201711173546719\",\"emptyCarId\":4,\"memberPriceId\":null,\"logisticsId\":15,\"payMethod\":null,\"status\":\"0\",\"waitConfirmTime\":1483263492000,\"waitPayTime\":null,\"payTime\":null,\"transportTime\":null,\"endTime\":null,\"msgPrice\":12,\"unitPrice\":12,\"chargeMethod\":\"0\",\"orderFrom\":\"0\",\"logisticsInfo\":{\"id\":15,\"goodsName\":\"物流测试\",\"weight\":423,\"length\":42,\"carNum\":34,\"departureTime\":1483263492000,\"departureTimeStr\":\"2017-38-01 05:01:12\",\"createTime\":1482741467000,\"createTimeStr\":\"2016-37-26 04:12:47\",\"isLargeGo\":0,\"isLargeGoDesc\":\"允许\",\"contactWay\":\"12345678901\",\"remark\":\"物流测试信息\",\"status\":1,\"statusDesc\":\"已抢\",\"addressInfoList\":[{\"id\":23,\"logisticsInfoId\":15,\"fromProvinceId\":120000,\"fromCityId\":120200,\"fromCountyId\":120221,\"toProvinceId\":150000,\"toCityId\":150200,\"toCountyId\":150202,\"fromDetail\":\"某某大街\",\"toDetail\":\"某某大街\",\"fromProvinceName\":\"天津市\",\"fromCityName\":\"县\",\"fromCountyName\":\"宁河县\",\"toProvinceName\":\"内蒙古自治区\",\"toCityName\":\"包头市\",\"toCountyName\":\"东河区\"},{\"id\":24,\"logisticsInfoId\":15,\"fromProvinceId\":120000,\"fromCityId\":120200,\"fromCountyId\":120221,\"toProvinceId\":140000,\"toCityId\":140300,\"toCountyId\":140303,\"fromDetail\":\"某某大街\",\"toDetail\":\"某某大街\",\"fromProvinceName\":\"天津市\",\"fromCityName\":\"县\",\"fromCountyName\":\"宁河县\",\"toProvinceName\":\"山西省\",\"toCityName\":\"阳泉市\",\"toCountyName\":\"矿区\"},{\"id\":25,\"logisticsInfoId\":15,\"fromProvinceId\":120000,\"fromCityId\":120200,\"fromCountyId\":120221,\"toProvinceId\":310000,\"toCityId\":310200,\"toCountyId\":310230,\"fromDetail\":\"某某大街\",\"toDetail\":\"某某大街\",\"fromProvinceName\":\"天津市\",\"fromCityName\":\"县\",\"fromCountyName\":\"宁河县\",\"toProvinceName\":\"上海市\",\"toCityName\":\"县\",\"toCountyName\":\"崇明县\"}]},\"logisticsAddressInfo\":{\"id\":null,\"logisticsInfoId\":null,\"fromProvinceId\":null,\"fromCityId\":null,\"fromCountyId\":null,\"toProvinceId\":null,\"toCityId\":null,\"toCountyId\":null,\"fromDetail\":\"某某大街\",\"toDetail\":\"某某大街\",\"fromProvinceName\":\"天津市\",\"fromCityName\":\"县\",\"fromCountyName\":\"宁河县\",\"toProvinceName\":\"内蒙古自治区\",\"toCityName\":\"包头市\",\"toCountyName\":\"东河区\"},\"addressInfoList\":null,\"memberEmptyCar\":{\"id\":4,\"memberId\":1,\"fromProvinceId\":110000,\"fromCityId\":110100,\"fromCountyId\":110101,\"fromAddressName\":\"北京市-市辖区-东城区 aaaa\",\"goDate\":\"2016-12-29 10:00:00.0\",\"carNumber\":5,\"loadNumber\":10,\"carLength\":15,\"cdate\":1482908283000,\"mdate\":1482908282000,\"remark\":null,\"successOfferNum\":null,\"offerNum\":null,\"address\":null,\"succAndTotal\":null,\"fromAddress\":null,\"toAddress\":null,\"fromProvinceName\":null,\"fromCityName\":null,\"fromCountyName\":null,\"breachNum\":null,\"account\":null,\"status\":\"1\",\"emptyCarAddressList\":[{\"id\":1,\"emptyCarId\":4,\"toProvinceId\":110000,\"toCityId\":110100,\"toCountyId\":110112,\"toAddressName\":\"大运河\",\"toProvinceName\":\"北京市\",\"toCityName\":\"市辖区\",\"toCountyName\":\"通州区\"},{\"id\":2,\"emptyCarId\":4,\"toProvinceId\":120000,\"toCityId\":120100,\"toCountyId\":0,\"toAddressName\":\"滨海\",\"toProvinceName\":\"天津市\",\"toCityName\":\"市辖区\",\"toCountyName\":null}],\"toAddressJsonData\":null},\"memberPrice\":null,\"orderComment\":null,\"totalPrice\":null,\"goodsName\":null,\"length\":null,\"departureTime\":null,\"account\":null,\"carNumber\":null,\"loadNumber\":null,\"address\":null,\"statusDesc\":null,\"fromAddress\":null,\"toAddress\":null,\"weight\":null,\"carNum\":null,\"isLargeGo\":null,\"isLargeGoDesc\":null,\"carLength\":null,\"remark\":null}";
        OrderDetails details = GsonUtils.jsonToObject(json, OrderDetails.class);
        System.out.println(details.getId());
    }
}