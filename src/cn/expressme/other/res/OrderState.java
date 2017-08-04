package cn.expressme.other.res;

public enum OrderState {
	FINISH_EVALUATE("订单成功","订单完成","已评价"),
	FINISH("订单成功","订单完成","未评价"),      //订单完成
	PC_FINISH("订单关闭","订单关闭","退款成功"),   //退款完成
	CANCEL_CLOSE("订单关闭","订单已经关闭","未付款，主动关闭"), //主动关闭
	CLOSE("订单关闭","订单已经关闭","未付款，超时关闭"),       //订单关闭
	
	DD_CANCELED("待返回货物","待返回货物","司机取消运送"),	
	//DD_CANCEL_("待商讨","请求取消运送","客户拒绝"),
	DD_CANCELING("待客户取消","请求取消运送","司机发起"),
	
	CD_CANCELED("待返回货物","待返回货物","客户取消运送"),//司机运送时取消
	CD_CANCELING("待司机取消","请求取消运送","客户发起"),   //客户运送时取消
	
	DELIVERY("待收货","订单派送中...","未收货"),       //派送中
	RECIEVE("待取货","司机接单","未运送"),    //接单
	
	PAY_CANCEL("待退款","订单退款中...","待退款"), //退款中
	PAYED("待接单","付款成功","待接单"),      //支付
	NONE("待付款","订单未付款","未付款");       //下单，未付款
	
	 private int value;
	 private String title;
	 private String name;
	 private String type;
		private OrderState(String title,String name,String type) {
			this.title = title;
			this.name=name;
			this.type=type;
		}

		public String getTitle() {
			return title;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public int getValue() {
			return value;
		}
}
