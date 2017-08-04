package cn.expressme.other.util;

import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.GregorianCalendar;  
import java.util.HashMap;
import java.util.Scanner;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
      
    /** 
     * ���֤����ĸ�ʽ��610821-20061222-612-X 
     * ��18λ������ɣ�ǰ6λΪ��ַ�룬��7��14λΪ���������룬��15��17λΪ˳���룬 
     * ��18λΪУ���롣������ֱ���0-10��11�����֣���������Ϊ��10��ʱ��Ϊ�˱�֤�������֤����18λ�������á�X����ʾ����ȻУ����Ϊ��X�����ܸ�����������ȫ�����ֱ�ʾ��ֻ�轫18λ������ݺ���ת����15λ�������֤���룬ȥ����7��8λ�����1λ3�����롣  
     * ��������֤������15λ��18λ֮�֡�1985���ҹ�ʵ�о������֤�ƶȣ���ʱǩ�������֤������15λ�ģ�1999��ǩ�������֤������ݵ���չ������λ��Ϊ��λ����ĩβ����Ч���룬�ͳ���18λ�� 
     * ��1��ǰ1��2λ���ֱ�ʾ������ʡ�ݵĴ��룻  
     * ��2����3��4λ���ֱ�ʾ�����ڳ��еĴ��룻 
     * ��3����5��6λ���ֱ�ʾ���������صĴ��룻 
     * ��4����7~14λ���ֱ�ʾ�������ꡢ�¡��գ� 
     * ��5����15��16λ���ֱ�ʾ�����ڵص��ɳ����Ĵ��룻  
     * ��6����17λ���ֱ�ʾ�Ա�������ʾ���ԣ�ż����ʾŮ�� 
     * ��7����18λ������У���룺����һ���㷨���� 
     * @author 
     * 
     */  
      
   public class IDCard {
          
        private static final String CHECKED_KEY_ERROR = "���֤У������Ч�����ǺϷ������֤����";
		private static final String LOC_ERROR = "���֤�����������";
		private static final String DAY_ERROR = "���֤������Ч";
		private static final String MONTH_ERROR = "���֤�·���Ч";
		private static final String BIRTHDAY_ERROR = "���֤���ղ�����Ч��Χ��";
		private static final String DATE_ERROR = "���֤����������Ч��";
		public static final String NUMBER_KEY_ERROR = "���֤15λ���붼ӦΪ���� ; 18λ��������һλ�⣬��ӦΪ���֡�";
		public static final String LENGTH_ERROR = "���֤���볤��Ӧ��Ϊ15λ��18λ��";
		public static final String VALIDATE ="�����֤��Ч��";
        
		public static boolean isValidate(String IDStr){
			try {
				return validate(IDStr).equals(VALIDATE);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return false;
		}
        public static String validate(String IDStr) throws ParseException {          
            String tipInfo = VALIDATE;// ��¼������Ϣ  
            String Ai = "";  
            // �жϺ���ĳ��� 15λ��18λ  
            if (IDStr.length() != 15 && IDStr.length() != 18) {  
                tipInfo = LENGTH_ERROR;  
                return tipInfo;  
            }  
              
      
            // 18λ���֤ǰ17λλ���֣������15λ�����֤�����к��붼Ϊ����  
            if (IDStr.length() == 18) {  
                Ai = IDStr.substring(0, 17);  
            } else if (IDStr.length() == 15) {  
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);  
            }  
            if (isNumeric(Ai) == false) {  
                tipInfo = NUMBER_KEY_ERROR;  
                return tipInfo;  
            }  
              
      
            // �жϳ��������Ƿ���Ч   
            String strYear = Ai.substring(6, 10);// ���  
            String strMonth = Ai.substring(10, 12);// �·�  
            String strDay = Ai.substring(12, 14);// ����  
            if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {  
                tipInfo = DATE_ERROR;  
                return tipInfo;  
            }  
            GregorianCalendar gc = new GregorianCalendar();  
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
            try {  
                if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150  
                        || (gc.getTime().getTime() - s.parse(  
                                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
                    tipInfo = BIRTHDAY_ERROR;  
                    return tipInfo;  
                }  
            } catch (NumberFormatException e) {  
                e.printStackTrace();  
            } catch (java.text.ParseException e) {  
                e.printStackTrace();  
            }  
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {  
                tipInfo = MONTH_ERROR;  
                return tipInfo;  
            }  
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {  
                tipInfo = DAY_ERROR;  
                return tipInfo;  
            }  
              
      
            // �жϵ������Ƿ���Ч   
            HashMap<String, String> areacode = GetAreaCode();  
            //������֤ǰ��λ�ĵ����벻��Hashtable�������������  
            if (areacode.get(Ai.substring(0, 2)) == null) {  
                tipInfo = LOC_ERROR;  
                return tipInfo;  
            }  
              
            if(isVarifyCode(Ai,IDStr)==false){  
                tipInfo = CHECKED_KEY_ERROR;  
                return tipInfo;  
            }  
             
              
            return tipInfo;  
        }  
          
          
         /* 
          * �жϵ�18λУ�����Ƿ���ȷ 
         * ��18λУ����ļ��㷽ʽ��  
            ����1. ��ǰ17λ���ֱ������Ȩ���  
            ������ʽΪ��S = Sum(Ai * Wi), i = 0, ... , 16  
            ��������Ai��ʾ��i��λ���ϵ����֤��������ֵ��Wi��ʾ��iλ���ϵļ�Ȩ���ӣ����λ��Ӧ��ֵ����Ϊ�� 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2  
            ����2. ��11�Լ�����ȡģ  
            ����Y = mod(S, 11)  
            ����3. ����ģ��ֵ�õ���Ӧ��У����  
            ������Ӧ��ϵΪ��  
            ���� Yֵ��     0  1  2  3  4  5  6  7  8  9  10  
            ����У���룺 1  0  X  9  8  7  6  5  4  3   2 
         */  
        private static boolean isVarifyCode(String Ai,String IDStr) {  
             String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2" };  
             String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };  
             int sum = 0;  
             for (int i = 0; i < 17; i++) {  
                sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);  
             }  
             int modValue = sum % 11;  
             String strVerifyCode = VarifyCode[modValue];  
             Ai = Ai + strVerifyCode;  
             if (IDStr.length() == 18) {  
                 if (Ai.equals(IDStr) == false) {  
                     return false;  
                      
                 }  
             }   
             return true;  
        }  
          
      
        /** 
         * �����е�ַ���뱣����һ��Hashtable��     
         * @return Hashtable ���� 
         */  
         
        private static HashMap<String, String> GetAreaCode() {  
        	HashMap<String, String> hashMap = new HashMap<String, String>();  
            hashMap.put("11", "����");  
            hashMap.put("12", "���");  
            hashMap.put("13", "�ӱ�");  
            hashMap.put("14", "ɽ��");  
            hashMap.put("15", "���ɹ�");  
            hashMap.put("21", "����");  
            hashMap.put("22", "����");  
            hashMap.put("23", "������");  
            hashMap.put("31", "�Ϻ�");  
            hashMap.put("32", "����");  
            hashMap.put("33", "�㽭");  
            hashMap.put("34", "����");  
            hashMap.put("35", "����");  
            hashMap.put("36", "����");  
            hashMap.put("37", "ɽ��");  
            hashMap.put("41", "����");  
            hashMap.put("42", "����");  
            hashMap.put("43", "����");  
            hashMap.put("44", "�㶫");  
            hashMap.put("45", "����");  
            hashMap.put("46", "����");  
            hashMap.put("50", "����");  
            hashMap.put("51", "�Ĵ�");  
            hashMap.put("52", "����");  
            hashMap.put("53", "����");  
            hashMap.put("54", "����");  
            hashMap.put("61", "����");  
            hashMap.put("62", "����");  
            hashMap.put("63", "�ຣ");  
            hashMap.put("64", "����");  
            hashMap.put("65", "�½�");  
            hashMap.put("71", "̨��");  
            hashMap.put("81", "���");  
            hashMap.put("82", "����");  
            hashMap.put("91", "����");  
            return hashMap;  
        }  
      
        /** 
         * �ж��ַ����Ƿ�Ϊ����,0-9�ظ�0�λ��߶��    
         * @param strnum 
         * @return 
         */  
        private static boolean isNumeric(String strnum) {  
            Pattern pattern = Pattern.compile("[0-9]*");  
            Matcher isNum = pattern.matcher(strnum);  
            if (isNum.matches()) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
      
        /** 
         * ���ܣ��ж��ַ������������Ƿ����������ʽ�����������գ����ꡢƽ���ÿ��31�졢30������µ�28�����29�� 
         *  
         * @param string 
         * @return 
         */  
        public static boolean isDate(String strDate) {  
          
            Pattern pattern = Pattern  
                    .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");  
            Matcher m = pattern.matcher(strDate);  
            if (m.matches()) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
          
        public static void main(String[] args) throws ParseException {  
            
            //String IdCard="61082120061222612X";  
            //�ӿ��ƶ������û����֤  
            Scanner s=new Scanner(System.in);  
            System.out.println("������������֤���룺");  
            String IdCard=new String(s.next());  
            //�����֤���һλ��xת��Ϊ��д������ͳһ  
            IdCard = IdCard.toUpperCase();  
            System.out.println(validate(IdCard));  
        }  
          
          
      
    
}
