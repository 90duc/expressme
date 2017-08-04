package cn.expressme.driver.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.impl.ClientDaoImpl;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.driver.dao.DriverDao;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyCodeType;
import cn.expressme.other.service.UserService;

@Controller("driverLoginAction")
@RequestMapping("/**/driver")
public class LoginAction {

	// private static final String loginFailPage = "errLogin.jsp"; //登录出错页
	private static final String homeUrl = "home.xhtm"; // 登录成功跳转页
	private static final String LOGIN_URL = "login_by_password.html";

	@Autowired
	private UserService userInfoService;

	/**
	 * 请求登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.xhtm")
	public String requestLogin() {

		String path;
		DriverBean client = userInfoService.getDriverBean();
		// 已经登录了
		if (client == null) { // 未登录
			path = LOGIN_URL;
		} else {
			path = "redirect:" + homeUrl;
		}

		return path;
	}

	/**
	 * 登录校验
	 * 
	 * @param userID
	 * @param passwordt
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginbypass.do")
	public String loginByPassword(String userID, String password) {

		int state = 0; // 密码出错
		String url = "";
		DriverDao userInfoDao = userInfoService.getDriverDao();
		DriverBean userInfo = userInfoDao.getByPhone(userID);
		// 不存在用户
		if (userInfo == null) {
			state = 1;// 用户不存在
		} else if (UserService.isSamePassword(userInfo.getPassword(), password)) { // 密码正确
			userInfoService.getSession().setAttribute(FinalInfo.DRIVER_ID,
					userInfo.getID());
			state = 2;// 登录成功
			url = homeUrl;
		}

		return "{\"state\":" + state + ",\"url\":\"" + url + "\"}";
	}

	/**
	 * 登录校验
	 * 
	 * @param userID
	 * @param verifyCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginbycode.do")
	public String loginByCode(String userID, String verifyCode) {

		DriverDao userInfoDao = userInfoService.getDriverDao();
		DriverBean userInfo = userInfoDao.getByPhone(userID);
		VerifyCodeBean vCode = (VerifyCodeBean) userInfoService.getVerifyCode();

		int state = 0; // 验证码出错
		String url = "";
		// 不存在用户
		if (userInfo == null) {
			userInfo = userInfoDao.insert(userID);
		}
		
		if (userInfo == null) {
			state=1;//新建失败
		} else if (VerifyCodeBean.equals(vCode, userID, verifyCode,
				VerifyCodeType.LOGIN)) { // 验证码有效
			
				state = 2; // 创建用户
				url = homeUrl;
				userInfoService.getSession().setAttribute(FinalInfo.DRIVER_ID,
						userInfo.getID());		
		}

		return "{\"state\":" + state + ",\"url\":\"" + url + "\"}";
	}

	/**
	 * 请求登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/home.xhtm")
	public ModelAndView home() {

		DriverBean driver = userInfoService.getDriverBean();
		ModelAndView mView = new ModelAndView();
		mView.addObject(FinalInfo.USER_INFO, driver);
		mView.setViewName("home.jsp");
		return mView;
	}

	// @RequestMapping("/wxLogin.do")
	// public String weChatLogin() {
	//
	//
	//
	// return null;
	// }

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping("/signOut.do")
	public String signOut() {

		userInfoService.getSession().removeAttribute(FinalInfo.USER_INFO);

		return "driver/home.jsp";
	}
}
