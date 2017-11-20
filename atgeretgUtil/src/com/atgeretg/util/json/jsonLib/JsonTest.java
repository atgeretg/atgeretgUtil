package com.atgeretg.util.json.jsonLib;

public class JsonTest {
		public static void main(String[] args) {
		    String jsonStr="{\"id\":\"101\",\"name\":\"张三\",\"age\":\"20\",\"birthday\":\"1992-10-19 23:52:18\"}";
//		    Student s=new Student();
//		    Timestamp b=Timestamp.valueOf("1992-10-19 23:52:18");
//		    s.setId(123456);
//		    s.setName("李四");
//		    s.setAge(20);
//		    s.setBirthday(b);
//		    Student s1=jsonToBean(jsonStr);
//		    System.out.println(s1.getBirthday());
//		    System.out.println(beanToJson(s));
		}
//		public static Student jsonToBean(String json){
//		    String[] formats={"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};
//		    JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
//		    JSONObject jsonObject=JSONObject.fromObject(json);
//		    return (Student)JSONObject.toBean(jsonObject,Student.class);
//		}
//
//		public static String beanToJson(Student s){
//		    JsonConfig config=new JsonConfig();
//		    config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
//		    JSONObject json=JSONObject.fromObject(s,config);
//		    return json.toString();}
		}
