package com.example.whea.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.zq.whea.domian.WeatherInfo;

public class WeatherService {

	public static List<WeatherInfo> getWeatherInfo(InputStream is) {

		XmlPullParser parser = Xml.newPullParser();

		List<WeatherInfo> weatherinfos = null;

		WeatherInfo weatherInfo = null;
		try {
			// 初始化解析器
			parser.setInput(is, "UTF-8");

			int type = parser.getEventType();

			while (type != XmlPullParser.END_DOCUMENT) {

				switch (type) {
				case XmlPullParser.START_TAG:
					if ("infos".equals(parser.getName())) {
						// 解析到全局开始标签
						weatherinfos = new ArrayList<WeatherInfo>();
					} else if ("city".equals(parser.getName())) {
						weatherInfo = new WeatherInfo();
						String idstr = parser.getAttributeValue(0);
						weatherInfo.setId(Integer.parseInt(idstr));
					} else if ("temp".equals(parser.getName())) {
						String tempstr = parser.nextText();
						weatherInfo.setTemp(tempstr);
					} else if ("wind".equals(parser.getName())) {
						String windstr = parser.nextText();
						weatherInfo.setWind(windstr);
					} else if ("name".equals(parser.getName())) {
						String namestr = parser.nextText();
						weatherInfo.setName(namestr);
					} 
					
					else if ("weather".equals(parser.getName())) {
						String weatherstr = parser.nextText();
						weatherInfo.setWeather(weatherstr);
					} else if ("pm".equals(parser.getName())) {
						String pmstr = parser.nextText();
						weatherInfo.setPm(pmstr);
					}

					break;
				case XmlPullParser.END_TAG:
					if ("city".equals(parser.getName())) {
						weatherinfos.add(weatherInfo);
						weatherInfo = null;
					}
					break;
					
				}

				type = parser.next(); 

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return weatherinfos;
	}

}
