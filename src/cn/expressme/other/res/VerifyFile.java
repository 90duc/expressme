package cn.expressme.other.res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class VerifyFile   {

	private MultipartFile cardPhoto0;
	private MultipartFile cardPhoto1;
	private MultipartFile PACPhoto;
	private MultipartFile driverPhoto0;
	private MultipartFile driverPhoto1;
	private MultipartFile drivingPhoto0;
	private MultipartFile drivingPhoto1;

	public byte[] getCardPhoto0() {
		return toPNGBytes(cardPhoto0);
	}

	public void setCardPhoto0(MultipartFile cardPhoto0) {
		this.cardPhoto0 = cardPhoto0;
	}

	public byte[] getCardPhoto1() {

		return toPNGBytes(cardPhoto1);
	}

	public void setCardPhoto1(MultipartFile cardPhoto1) {
		this.cardPhoto1 = cardPhoto1;
	}

	public byte[] getPACPhoto() {
		return toPNGBytes(PACPhoto);
	}

	public void setPACPhoto(MultipartFile pACPhoto) {
		PACPhoto = pACPhoto;
	}

	public byte[] getDriverPhoto0() {
		return toPNGBytes(driverPhoto0);
	}

	public void setDriverPhoto0(MultipartFile driverPhoto0) {
		this.driverPhoto0 = driverPhoto0;
	}

	public byte[] getDriverPhoto1() {
		return toPNGBytes(driverPhoto1);
	}

	public void setDriverPhoto1(MultipartFile driverPhoto1) {
		this.driverPhoto1 = driverPhoto1;
	}

	public byte[] getDrivingPhoto0() {
		return toPNGBytes(drivingPhoto0);
	}

	public void setDrivingPhoto0(MultipartFile drivingPhoto0) {
		this.drivingPhoto0 = drivingPhoto0;
	}

	public byte[] getDrivingPhoto1() {
		return toPNGBytes(drivingPhoto1);
	}

	public void setDrivingPhoto1(MultipartFile drivingPhoto1) {
		this.drivingPhoto1 = drivingPhoto1;
	}

	public byte[] toPNGBytes(MultipartFile file) {
		if (file != null)
			try {
                ByteArrayOutputStream  bo =new ByteArrayOutputStream();
				ImageIO.write(ImageIO.read(file.getInputStream()),"png", bo);
				return bo.toByteArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null;
	}

}
