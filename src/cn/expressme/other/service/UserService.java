package cn.expressme.other.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.ClientDao;
import cn.expressme.driver.bean.DriverBean;
import cn.expressme.driver.dao.DriverDao;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.util.Util;
@Component
public class UserService {

	@Autowired
	private ClientDao clientDao;
	@Autowired
	private DriverDao driverDao;


	@Autowired
	private HttpSession session;

	public VerifyCodeBean getVerifyCode() {
		
		return (VerifyCodeBean) session.getAttribute(FinalInfo.VERIFY_CODE);
	}

	public ClientBean getClientBean() {
		String id=(String)session.getAttribute(FinalInfo.CLIENT_ID);
		if(id==null)
		  return null;
		
		return clientDao.load(id);
	}
	
    public DriverBean getDriverBean() {
    	String id=(String)session.getAttribute(FinalInfo.DRIVER_ID);
		if(id==null)
		  return null;
		return driverDao.load(id);
	}
    
    
	 public static double getPrice(double distance) {
		   double price=8;
	       if(distance>FinalInfo.LIMIT_DISTANCE){
	    	   price+=(distance-FinalInfo.LIMIT_DISTANCE)*0.25;
	       }
	       price=Math.round(price);
	       price=price>15?15:price;
	       return price;
	 }
	 
	 public static int getTime(double distance) {
		   double time=15;
	       time+=distance*1;	     
	       return (int)time;
	 }

    private static String root;
    public static String getRootURL(){
        if(root==null){
        	root=new File(System.getProperty("expressmeweb.root")).getParent();
        	System.out.println(root);
        }
		return root;
	}
    public  String getContextURL(){
            
		return session.getServletContext().getRealPath("/");
	}
	
	public static boolean isSamePassword(String userPwd, String checkedPwd) {

		return userPwd != null
				&& userPwd.equalsIgnoreCase(Util.MD5(checkedPwd));
	}

	public ClientDao getClientDao() {
		return clientDao;
	}

	public HttpSession getSession() {
		return session;
	}

	public DriverDao getDriverDao() {
		return driverDao;
	}

}
