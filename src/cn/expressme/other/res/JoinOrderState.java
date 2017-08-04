package cn.expressme.other.res;

public enum JoinOrderState {
	
	FINISH_EVALUATE("订单成功","订单完成","已评价"),
	FINISH("订单成功","订单完成","未评价"),      //订单完成

	DD_CANCEL("订单终止","取消运送完成","司机发起"),
	DR_CANCEL("订单终止","取消运送完成","司机发起"),	
	CD_CANCEL("订单终止","取消运送完成","客户发起"),//司机运送时取消
	CR_CANCEL("订单终止","取消运送完成","客户发起"),
	DELIVERY("待收货","订单派送中...","未收货"),       //派送中
	RECIEVE("待取货","司机接单","未运送");    //接单
	
	
	 private int value;
	 private String title;
	 private String name;
	 private String type;
		private JoinOrderState(String title,String name,String type) {
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
