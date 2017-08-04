package cn.expressme.other.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.util.Base64Utils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.jdbc.Buffer;

import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.Point;

public class Util {
	/**
	 * 将字符串转成MDd5字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String MD5(String str) {

		str = str == null ? "" : str;
		String dstr = str;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dstr;
	}

	public static String UUID() {

		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static final boolean isNullOrEmpty(String s) {

		return s == null || s.isEmpty();
	}

	public static String toBase64Url(byte[] data) {
		if (data == null)
			return null;

		return "data:image/" + FinalInfo.IMAGE_TYPE + ";base64,"
				+ Base64.getEncoder().encodeToString(data);
	}

	// public static byte[] toByteArray(InputStream in, int w, int h) {
	//
	// ByteArrayOutputStream bo = new ByteArrayOutputStream();
	//
	// try {
	// Image image = ImageIO.read(in).getScaledInstance(w, h,
	// Image.SCALE_DEFAULT);
	// BufferedImage nImage = new BufferedImage(w, h,
	// BufferedImage.TYPE_INT_BGR);
	// Graphics2D g = (Graphics2D) nImage.getGraphics();
	// g.drawImage(image, 0, 0, null);
	// g.dispose();
	// nImage.flush();
	//
	// ImageIO.write(nImage, FinalInfo.IMAGE_TYPE, bo);
	// return bo.toByteArray();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

	public static final String savePhoto(String root, String path,
			InputStream in, int w, int h) {

		try {
			File file = new File(root, path);
			file.getParentFile().mkdirs();
			BufferedImage nImage = ImageIO.read(in);
			if (w > 0 && h > 0) {
				Image image = nImage.getScaledInstance(w, h,
						Image.SCALE_DEFAULT);
				nImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
				Graphics2D g = (Graphics2D) nImage.getGraphics();
				g.drawImage(image, 0, 0, null);
				g.dispose();
				nImage.flush();
			}
			ImageIO.write(nImage, FinalInfo.IMAGE_TYPE, file);
			return path;
		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public static final boolean isNullOrEmpty(CommonsMultipartFile photo) {
		return photo == null || photo.isEmpty();
	}
	/**
	 * 获取经纬度的地图距离
	 * @return km
	 */
	public static double getMapDistance(Point p1,Point p2){    
		 // 纬度
        double lat1= (Math.PI/180)*p1.getY();    
        double lat2 = (Math.PI/180)*p2.getY();    
        // 经度    
        double lon1 = (Math.PI/180)*p1.getX();    
        double lon2 = (Math.PI/180)*p2.getX();    
            
        //地球半径    
        double R = 6371;    
            
        //两点间距离 km，如果想要米的话，结果*1000就可以了    
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;    
            
        return d;    
    }
	private static final DateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat DATE_TIME_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static String formateDate(Date date) {
		
		return date==null?"":DATE_FORMAT.format(date);
	}
    public static Date formateDate(String date) {
		
		try {
			return DATE_TIME_FORMAT.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
