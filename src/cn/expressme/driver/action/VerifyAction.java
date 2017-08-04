package cn.expressme.driver.action;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.client.dao.ClientVerifyDao;
import cn.expressme.driver.bean.DriverVerifyBean;
import cn.expressme.driver.dao.DriverVerifyDao;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyFile;
import cn.expressme.other.res.VerifyState;
import cn.expressme.other.service.UserService;
import cn.expressme.other.service.VerifyService;

@Controller
@RequestMapping("/**/driver")
public class VerifyAction {

	@Autowired
	private UserService userInfoService;
	@Autowired
	private DriverVerifyDao verifyDao;

	/**
	 * ��֤��Ϣ
	 * 
	 * @param billNumber
	 * @return
	 */
	@RequestMapping("/verifyInput.html")
	public ModelAndView verifyInput(String reVerify) {
		ClientBean userInfo = userInfoService.getClientBean();
		DriverVerifyBean verifyBean = verifyDao.load(userInfo.getID());

		ModelAndView mv = new ModelAndView();
		if (verifyBean == null || Objects.equals(reVerify, "true")) { // û����֤��������֤

			mv.setViewName("verifyInput.jsp");
		} else {
			// ��֤�ȴ����
			mv.addObject(FinalInfo.VERIFICATION, verifyBean);

			if (verifyBean.getVerifyState().equals(VerifyState.VERIFYING)) {
				mv.setViewName("verifyWait.jsp");
			}
		}

		return mv;
	}

	/**
	 * ��֤����
	 * 
	 * @param billNumber
	 * @return
	 */
	@RequestMapping("/verifyInput.do")
	public String verifyDeal(DriverVerifyBean verify, VerifyFile files) {

		String mv = "failPage";
		// У����֤��Ϣ
		if (VerifyService.isLegal(verify)) {
			verify.setDriver(userInfoService.getDriverBean());
			verify.setCardPhoto0(files.getCardPhoto0());
			verify.setCardPhoto1(files.getCardPhoto1());
			verify.setPACPhoto(files.getPACPhoto());
			if (verifyDao.insert(verify)) { //

				mv = "personInfo.jsp";
			}
		}
		return mv;
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
