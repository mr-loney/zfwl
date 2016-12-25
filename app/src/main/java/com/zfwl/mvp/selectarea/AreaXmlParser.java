package com.zfwl.mvp.selectarea;

import com.zfwl.common.MyLog;
import com.zfwl.entity.Area;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2016/12/16.
 */
public class AreaXmlParser extends DefaultHandler {
//    private static final String TAG = "AreaXmlParser";
//    private List<Area> mProvinces = new ArrayList<>();
//    private Area mProvince, mCity, mDistrict;
//    private int mProvinceIndex, mCityIndex, mDistrictIndex;
//
//    public List<Area> getProvinces(){
//        return mProvinces;
//    }
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        if (qName.equals("province")) {
//            mProvince = new Area();
//            mProvince.setId(mProvinceIndex);
//            mProvince.setName(attributes.getValue(0));
//            mProvince.setSubAreas(new ArrayList<>());
//            mProvinceIndex++;
//            mCityIndex = 0;
//            mDistrictIndex = 0;
//        } else if (qName.equals("city")) {
//            mCity = new Area();
//            mCity.setId(mCityIndex);
//            mCity.setParentId(mProvinceIndex - 1);
//            mCity.setName(attributes.getValue(0));
//            mCity.setSubAreas(new ArrayList<>());
//            mCityIndex++;
//            mDistrictIndex = 0;
//        } else if (qName.equals("district")) {
//            mDistrict = new Area();
//            mDistrict.setId(mDistrictIndex);
//            mDistrict.setParentId(mCityIndex - 1);
//            mDistrict.setName(attributes.getValue(0));
//            mDistrict.setZipCode(attributes.getValue(1));
//            mDistrictIndex++;
//        }
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equals("district")) {
//            mCity.getSubAreas().add(mDistrict);
//        } else if (qName.equals("city")) {
//            mProvince.getSubAreas().add(mCity);
//        } else if (qName.equals("province")) {
//            mProvinces.add(mProvince);
//        }
//    }
}
