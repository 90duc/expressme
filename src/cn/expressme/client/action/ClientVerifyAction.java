package cn.expressme.client.action;


import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.ClientVerifyDao;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyState;
import cn.expressme.other.service.UserService;
import cn.expressme.other.util.IDCard;
import cn.expressme.other.util.Util;

@Controller
@RequestMapping("/client")
public class ClientVerifyAction {

	@Autowired
	private UserService userInfoService;
	@Autowired
	private ClientVerifyDao verifyDao;

	/**
	 * 
	 * 
	 * @param billNumber
	 * @return
	 */
	@RequestMapping("/verify.xhtm")
	public ModelAndView verifyInput() {
		ClientBean userInfo = userInfoService.getClientBean();
		ClientVerifyBean verifyBean = verifyDao.load(userInfo.getID());

		ModelAndView mv = new ModelAndView();
		if (verifyBean.isVerified()) { 
			mv.setViewName("verifyInput.jsp");
		} else if(verifyBean.isVerifying()){
			mv.addObject(FinalInfo.USER_INFO, userInfo);
			mv.addObject(FinalInfo.VERIFICATION, verifyBean);
			mv.setViewName("client_certifying.jsp");
		}else {
			mv.addObject(FinalInfo.USER_INFO, userInfo);
			mv.setViewName("client_certification.jsp");
		}	

		return mv;
	}

	/**
	 * 
	 * 
	 * @param billNumber
	 * @return
	 */
	@RequestMapping("/verify.do")
	public String verifyDeal(String city,String realName,String idCard) {

		ClientBean userInfo = userInfoService.getClientBean();
		ClientVerifyBean verifyBean =userInfo.getVerify();
		String mv = "errLogin.jsp";
		
		if (IDCard.isValidate(idCard)) {
			verifyBean.setCardID(idCard);
			if(!Util.isNullOrEmpty(realName))
				verifyBean.setRealName(realName);
			if(!Util.isNullOrEmpty(city))
				verifyBean.setLocation(city);
			    verifyBean.setVerifyState(VerifyState.VERIFYING);
			    verifyBean.setVerifyTime(new Date());
			if (verifyDao.update(verifyBean)) { //
				mv = "home.jsp";
			}
		}
		return "redirect:"+mv;
	}

	// @RequestMapping("/**/imgc.do")
	// public ModelAndView verifyDeal1() {
	//
	// ModelAndView mv = new ModelAndView();
	//
	// byte[] data=null;
	// try(FileInputStream inputStream=new
	// FileInputStream("C:\\Users\\MK\\Desktop\\1.png");) {
	// data = new byte[inputStream.available()];
	// inputStream.read(data);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// String url =VerifyOutBean.toBase64Url(data);
	// System.out.println(url);
	// mv.addObject("url",url);
	// mv.setViewName("show.jsp");
	// return mv;
	// }
}
