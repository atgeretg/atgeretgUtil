package com.atgeretg.util.Random;

import java.text.SimpleDateFormat;
import java.util.Random;
/**
 * 根据IP（也可以是其它字符串）和时间生成的一个随机数，用于命名不重复
 * @author atgeretg
 *
 */
public class IpTimeStarmp {
    private SimpleDateFormat sim=null;//用来获取时间
    private String ip=null;
    public IpTimeStarmp(){
    }
    public IpTimeStarmp(String ip){
        this.ip=ip;
    }
    public String getIpTimeRand(){
        StringBuffer sbf=new StringBuffer();
        if(this.ip!=null){
            String a[]=this.ip.split("\\.");                //根据点来拆分ip地址，但点要转义
            for(int i=0;i<a.length;i++){
                sbf.append(this.addZero(a[i], 3));            //调用补零的方法，每块ip不足三位的自动补足到三位
            }
            sbf.append(this.getTimeStamp());                //用this来调用外部的方法
            Random random=new Random();                        //要产生随机数
            for(int i=0;i<3;i++){                            //产生三位随机数
                sbf.append(random.nextInt(10));                //每位随机数都不超过10
            }
        }
        return sbf.toString();
    }
    @SuppressWarnings("unused")
    private String getDate(){                                //关于日期与时间的实现
        this.sim=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
        return this.sim.format(new java.util.Date());
    }
    private String getTimeStamp(){                            //返回时间戳
        this.sim=new SimpleDateFormat("yyyymmddhhmmssSSS");
        return this.sim.format(new java.util.Date());
    }
    private String addZero(String str,int len){                //自动补零的方法，参数为指定的字符串和长度
        StringBuffer s=new StringBuffer();
        s.append(str);
        while(s.length()<len){
            s.insert(0,"0");                                //在零的位置上进行补零操作
        }
        return s.toString();
    }
    
    //做测试
    public static void main(String [] ary){
        IpTimeStarmp IpTimeStamp=new IpTimeStarmp("172.168.3.222");//调用有参数的构造方法
        System.out.println(IpTimeStamp.getIpTimeRand());
    }
}