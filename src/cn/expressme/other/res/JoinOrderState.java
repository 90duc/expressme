package cn.expressme.other.res;

public enum JoinOrderState {
	
	FINISH_EVALUATE("�����ɹ�","�������","������"),
	FINISH("�����ɹ�","�������","δ����"),      //�������

	DD_CANCEL("������ֹ","ȡ���������","˾������"),
	DR_CANCEL("������ֹ","ȡ���������","˾������"),	
	CD_CANCEL("������ֹ","ȡ���������","�ͻ�����"),//˾������ʱȡ��
	CR_CANCEL("������ֹ","ȡ���������","�ͻ�����"),
	DELIVERY("���ջ�","����������...","δ�ջ�"),       //������
	RECIEVE("��ȡ��","˾���ӵ�","δ����");    //�ӵ�
	
	
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
