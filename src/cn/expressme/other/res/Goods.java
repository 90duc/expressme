package cn.expressme.other.res;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Embeddable;



@Embeddable
public class Goods implements Serializable{
	
	/**
	 * 
	 */

	private String goodsName;
	private Double goodsValue=null;
	private Double goodsWeight=null;
	private Double goodsLength=null;
	private Double goodsWidth=null;
	private Double goodsHeight=null;
	private String goodsPhoto;

	public Goods() {
		super();		
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(Double goodsValue) {
		this.goodsValue = goodsValue;
	}

	public Double getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(Double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public Double getGoodsLength() {
		return goodsLength;
	}

	public void setGoodsLength(Double goodsLength) {
		this.goodsLength = goodsLength;
	}

	public Double getGoodsWidth() {
		return goodsWidth;
	}

	public void setGoodsWidth(Double goodsWidth) {
		this.goodsWidth =goodsWidth;
	}

	public Double getGoodsHeight() {
		return goodsHeight;
	}

	public void setGoodsHeight(Double goodsHeight) {
		this.goodsHeight =goodsHeight;
	}

	public String getGoodsPhoto() {
		return goodsPhoto;
	}

	public void setGoodsPhoto(String goodsPhoto) {
		this.goodsPhoto = goodsPhoto;
	}

	public void update(Goods goods) {
		Object o;
        for(Field f:Goods.class.getDeclaredFields()){
        	try {
				o=f.get(goods);
				if(o!=null&&!o.equals("")&&!o.equals(f.get(this))){
	        		f.set(this, o);
	        	}
			} catch (IllegalArgumentException | IllegalAccessException e) {			
				e.printStackTrace();
			}
        	
        }
	}
}