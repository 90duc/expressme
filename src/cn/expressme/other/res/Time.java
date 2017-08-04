package cn.expressme.other.res;

public class Time {
     private long time=0;
     public Time() {
		
	}
     public Time(long time){
    	 this.time=time;
    	 
     }
     public long getHours() {
		
    	 return time/(1000*60*60)%24;
	}
    public long getMinutes() {
    	 return time/(1000*60)%60;
 	}
     public long getSeconds() {
    	 return time/1000%60;
 	}
     
     public long getTime() {
    	 return time;
 	}
}
