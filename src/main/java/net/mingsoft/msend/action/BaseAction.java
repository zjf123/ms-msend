package net.mingsoft.msend.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.SpringUtil;
import net.mingsoft.msend.biz.IMailBiz;
import net.mingsoft.msend.biz.ISmsBiz;
import net.mingsoft.msend.biz.ITemplateBiz;
import net.mingsoft.msend.constant.e.MailEnum;
import net.mingsoft.msend.constant.e.SendEnum;
import net.mingsoft.msend.constant.e.ThridEnum;
import net.mingsoft.msend.entity.MailEntity;
import net.mingsoft.msend.entity.SmsEntity;
import net.mingsoft.msend.entity.TemplateEntity;
import net.mingsoft.msend.util.MailUtil;
import net.mingsoft.msend.util.SendcloudUtil;

/**
 * msend基础控制层
 * 
 * @author 伍晶晶
 * @version 版本号：0.0<br/>
 *          创建日期：2017-8-24 14:41:18<br/>
 *          历史修订：<br/>
 */
public class BaseAction extends com.mingsoft.basic.action.BaseAction {

	@Override
	protected String getResString(String key) {
		// TODO Auto-generated method stub
		String str = "";
		try {
			str = super.getResString(key);
		} catch (MissingResourceException e) {
			str = net.mingsoft.msend.constant.Const.RESOURCES.getString(key);
		}

		return str;
	}

}
