package cn.expressme.client.action;

import java.io.IOException;
import java.util.Objects;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.ClientDao;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyCodeType;
import cn.expressme.other.service.UserService;
import cn.expressme.other.util.Util;

@Controller
@RequestMapping("/client")
public class LoginAction {

	private static final String LOGIN_URL =  "login_by_password.html";//"login_by_code.html";

	/**
	 * �����¼
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.xhtm")
	public String requestLogin() {

		String path;
		ClientBean client = userInfoService.getClientBean();
		// �Ѿ���¼��
		if (client == null) { // δ��¼
			path = LOGIN_URL;
		} else {
			path ="redirect:" +loginedPage;
		}

		return path;
	}

	/**
	 * �����¼
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/home.xhtm")
	public ModelAndView home() {
		
		ClientBean client = userInfoService.getClientBean();
		ModelAndView mView=new ModelAndView();
        mView.addObject(FinalInfo.USER_INFO, client);
        mView.setViewName( "home.jsp");
		return mView;
	}
	
	private static final String loginFailPage = "errLogin.jsp"; // ��¼����ҳ
	private static final String loginedPage = "home.xhtm"; // ��¼�ɹ���תҳ

	@Autowired
	private UserService userInfoService;
	
	/**
	 * ��¼У��
	 * 
	 * @param userID
	 * @param verifyCode
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String userID, String verifyCode, String password) {

		ClientDao dao = userInfoService.getClientDao();
		ClientBean client = dao.getByPhone(userID);
		VerifyCodeBean vCode = (VerifyCodeBean) userInfoService.getVerifyCode();

		String path = loginFailPage;

		// �������û��������û�
		if (client == null) {
			// ��֤����Ч
			if (VerifyCodeBean.equals(vCode, userID, verifyCode,
					VerifyCodeType.LOGIN)) {
				// �����û���Ϣ
				client = dao.insert(new ClientBean(userID));
				if (client != null)
					path = loginedPage;// ������ת����������ҳ
			}

		} else if (Util.isNullOrEmpty(password)) { // ʹ����֤���¼
			// ��֤����ȷ��������Чʱ��
			if (VerifyCodeBean.equals(vCode, userID, verifyCode,
					VerifyCodeType.LOGIN)) {
				path = loginedPage;
			}

		} else if (UserService.isSamePassword(client.getPassword(), password)) { // ������ȷ
			path = loginedPage;
		}
		// ��sesion���õ�¼��Ϣ
		if (path.equals(loginedPage)) {
			userInfoService.getSession().setAttribute(
					FinalInfo.CLIENT_ID, client.getID());
		}

		return "redirect:"+path;
	}

	private static final String state = "weixinLogin";

	/**
	 * ΢�ŵ�¼
	 * 
	 * @param code
	 * @param state
	 */
	@RequestMapping("/wcLogin.mhtm")
	public void weChatLogin(String code, String state) throws ClientProtocolException {
		String urls = " https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID" + "&secret=SECRET" + "&code=" + code
				+ "&grant_type=authorization_code";
		JSONObject jsonResult = null;
		if (Objects.equals(state, LoginAction.state)) {
			try {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost(urls);
				HttpResponse response = client.execute(request);
				 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		                
		                String strResult = EntityUtils.toString(response.getEntity());
		                /**��json�ַ���ת����json����**/
		                jsonResult = JSONObject.fromObject(strResult);
		                System.out.println(jsonResult.toString());
		            } else {
		                System.out.println("get�����ύʧ��:" + urls);
		            }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * �˳�
	 * 
	 * @return
	 */
	@RequestMapping("/signOut.xhtm")
	public String signOut() {

		userInfoService.getSession().removeAttribute(FinalInfo.CLIENT_ID);
		return "redirect:login.xhtm";
	}
}
