package org.gozhe.android.cgt.service;

import java.util.ArrayList;
import java.util.List;

import org.gozhe.android.R;
import org.gozhe.android.cgt.entity.QuestionType;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class QuestionTypeService {

	Context context;

	public QuestionTypeService(Context context) {
		this.context = context;
	}

	public List<QuestionType> getEventDL() throws Exception {

		List<QuestionType> events = null;
		QuestionType event_dl = null;
		XmlResourceParser parser = (XmlResourceParser) context.getResources()
				.getXml(R.xml.event);
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				events = new ArrayList<QuestionType>();
				break;
			case XmlPullParser.START_TAG:
				if ("dl".equals(parser.getName())) {
					event_dl = new QuestionType();
					event_dl.setCode(parser.getAttributeValue(0));
					event_dl.setName(parser.getAttributeValue(1));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("dl".equals(parser.getName())) {
					events.add(event_dl);
					event_dl = null;
				}
				break;
			}
			event = parser.next();
		}
		return events;
	}

	public List<QuestionType> getEventXL(String code) throws Exception {
		List<QuestionType> events = null;
		QuestionType event_xl = null;
		boolean getit = false;
		XmlResourceParser parser = (XmlResourceParser) context.getResources()
				.getXml(R.xml.event);
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				events = new ArrayList<QuestionType>();
				break;
			case XmlPullParser.START_TAG:
				if ("dl".equals(parser.getName())) {
					if (code.equals(parser.getAttributeValue(0))) {
						getit = true;
					} else {
						getit = false;
					}
				}
				if ("xl".equals(parser.getName()) && getit) {
					event_xl = new QuestionType();
					event_xl.setCode(parser.getAttributeValue(0));
					event_xl.setName(parser.getAttributeValue(1));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("xl".equals(parser.getName()) && getit) {
					events.add(event_xl);
					event_xl = null;
				}
				break;
			}
			event = parser.next();
		}
		return events;
	}

	public List<QuestionType> getComponentDL() throws Exception {
		List<QuestionType> comps = null;
		QuestionType comp_dl = null;
		XmlResourceParser parser = (XmlResourceParser) context.getResources()
				.getXml(R.xml.component);
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				comps = new ArrayList<QuestionType>();
				break;
			case XmlPullParser.START_TAG:
				if ("dl".equals(parser.getName())) {
					comp_dl = new QuestionType();
					comp_dl.setCode(parser.getAttributeValue(0));
					comp_dl.setName(parser.getAttributeValue(1));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("dl".equals(parser.getName())) {
					comps.add(comp_dl);
					comp_dl = null;
				}
				break;
			}
			event = parser.next();
		}
		return comps;
	}

	public List<QuestionType> getComponentXL(String code) throws Exception {
		List<QuestionType> comps = null;
		QuestionType comp_xl = null;
		boolean getit = false;
		XmlResourceParser parser = (XmlResourceParser) context.getResources()
				.getXml(R.xml.component);
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				comps = new ArrayList<QuestionType>();
				break;
			case XmlPullParser.START_TAG:
				if ("dl".equals(parser.getName())) {
					if (code.equals(parser.getAttributeValue(0))) {
						getit = true;
					} else {
						getit = false;
					}
				}
				if ("xl".equals(parser.getName()) && getit) {
					comp_xl = new QuestionType();
					comp_xl.setCode(parser.getAttributeValue(0));
					comp_xl.setName(parser.getAttributeValue(1));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("xl".equals(parser.getName()) && getit) {
					comps.add(comp_xl);
					comp_xl = null;
				}
				break;
			}
			event = parser.next();
		}
		return comps;
	}
}
