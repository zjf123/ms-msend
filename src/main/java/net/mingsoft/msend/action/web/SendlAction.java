package net.mingsoft.msend.action.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingsoft.util.JsonUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.msend.biz.IMailBiz;
import net.mingsoft.msend.constant.ModelCode;

/**
 * 邮件管理控制层
 * 
 * @author 伍晶晶
 * @version 版本号：0.0<br/>
 *          创建日期：2017-8-24 14:41:18<br/>
 *          历史修订：<br/>
 */
@Controller("webMailAction")
@RequestMapping("/msend/mail")
public class SendlAction extends net.mingsoft.msend.action.BaseAction {

	/**
	 * 注入邮件业务层
	 */
	@Autowired
	private IMailBiz mailBiz;

	/**
	 * 自由调用邮箱
	 * 
	 * @param peopleMail
	 *            邮件地址
	 * @param modelCode
	 *            模块编码（AES加密过的）
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "send", method = RequestMethod.POST)
	public void send(HttpServletRequest request, HttpServletResponse response) {
		String mail = request.getParameter("receive");
		String modelCode = request.getParameter("modelCode");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String[] user = null;
		// 后台验证传来的用户邮箱地址是否合法
		if (!StringUtil.isBlank(mail) && StringUtil.isEmail(mail)) {
			user = new String[] { mail };
		}

		// 验证模块编码是否为空
		if (StringUtil.isBlank(modelCode)) {
			this.outJson(response, ModelCode.SEND, false,
					this.getResString("err.error", this.getResString("model.code")));
			return;
		}

		String _modelCode = this.decryptByAES(request, modelCode);
		// 将邮箱地址压如String数组

		Map params = JsonUtil.getJsonToObject(content, Map.class);
		// 发送邮箱
		boolean status = this.sendMail(_modelCode, user, params);
		if (status) {
			// 返回操作成功信息
			this.outJson(response, null, true);
		} else {
			// 返回操作成功信息
			this.outJson(response, null, false);
		}
	}
}