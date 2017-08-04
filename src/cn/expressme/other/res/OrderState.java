package cn.expressme.other.res;

public enum OrderState {
	FINISH_EVALUATE("�����ɹ�","�������","������"),
	FINISH("�����ɹ�","�������","δ����"),      //�������
	PC_FINISH("�����ر�","�����ر�","�˿�ɹ�"),   //�˿����
	CANCEL_CLOSE("�����ر�","�����Ѿ��ر�","δ��������ر�"), //�����ر�
	CLOSE("�����ر�","�����Ѿ��ر�","δ�����ʱ�ر�"),       //�����ر�
	
	DD_CANCELED("�����ػ���","�����ػ���","˾��ȡ������"),	
	//DD_CANCEL_("������","����ȡ������","�ͻ��ܾ�"),
	DD_CANCELING("���ͻ�ȡ��","����ȡ������","˾������"),
	
	CD_CANCELED("�����ػ���","�����ػ���","�ͻ�ȡ������"),//˾������ʱȡ��
	CD_CANCELING("��˾��ȡ��","����ȡ������","�ͻ�����"),   //�ͻ�����ʱȡ��
	
	DELIVERY("���ջ�","����������...","δ�ջ�"),       //������
	RECIEVE("��ȡ��","˾���ӵ�","δ����"),    //�ӵ�
	
	PAY_CANCEL("���˿�","�����˿���...","���˿�"), //�˿���
	PAYED("���ӵ�","����ɹ�","���ӵ�"),      //֧��
	NONE("������","����δ����","δ����");       //�µ���δ����
	
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
