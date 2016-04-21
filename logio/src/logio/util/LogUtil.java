package logio.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import logio.variable.LogVar;

public class LogUtil {

	/**********************************************************
	 * 작성자 : 신민수
	 * [ = 공백 , ] = , 변환하는 메소드
	 * ********************************************************/
	public static String strReplaceAll(String replaceStr){
		
		String result = "";
		
		result = replaceStr.replaceAll("\\[", "");
		result = result.replaceAll("\\]", ",");
//		System.out.println(result);
		return result;
	}
	
	/**********************************************************
	 * 작성자 : 신민수
	 * apikey list 중 요청수별로 비교하여 최대 요청수를 가져오는 메소드
	 * ********************************************************/
	public static Map<String, Object> getMax(Map<String, Object> list, List<String> apiList) {
		 
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int max = 0;
		String result = "";
		 
		for(int i = 0; i < apiList.size(); i++){
			for(int j = 0; j < list.size(); j ++) {
				if( i == 0 || (Integer.parseInt(list.get(apiList.get(i)).toString()) > max) ){
					max = Integer.parseInt(list.get(apiList.get(i)).toString());
					result = apiList.get(i).toString();
				}
			}
		}
		 
		resultMap.put(LogVar.MAXAPI, result);
		resultMap.put(LogVar.MAXAPICOUNT, max);
		return resultMap;
	}
	 
	/**********************************************************
	 * 작성자 : 신민수
	 * 200 성공 데이타 요청 list 중복값을 제거하는 메소드
	 * ********************************************************/
	 public static List<String> listSet(List<String> list){
		 
		 List<String> result = new ArrayList<String>();
		 
		 @SuppressWarnings({ "rawtypes", "unchecked" })
		 HashSet hs = new HashSet(list);
		 @SuppressWarnings("unchecked")
		 ArrayList<String> newArrList = new ArrayList<String>(hs);
			
		 for(int i = 0; i < newArrList.size(); i++){
			 result.add(newArrList.get(i));
		 }
		 return result;
	 }
	 
	/**********************************************************
	 * 작성자 : 신민수
	 * 200 성공 데이타 요청별로 합계 구하는 메소드
	 * ********************************************************/
	 public static Map<String, Object> sumCount(List<String> newlist, List<String> list){
		 int sum_count = 0;
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 for(int i = 0; i < newlist.size();i++){
			 for(int j = 0; j < list.size();j++){
			 	 if(newlist.get(i).trim().toString().equals(list.get(j).trim().toString())){
					 sum_count++;
					 map.put(newlist.get(i), sum_count);
				 }
			 }
			 sum_count = 0;
		 }
		 return map;
	 }
	 
	 /**********************************************************
	 * 작성자 : 신민수
	 * map list 오름차순 정렬하는 메소드
	 * ********************************************************/
     @SuppressWarnings("unchecked")
	 public static List<String> sortByValue(final Map<String, Object> map){
    	 List<String> list = new ArrayList<String>();
    	 list.addAll(map.keySet());
         
    	 Collections.sort(list,new Comparator<Object>(){
    		 public int compare(Object o1,Object o2){
    			 Object v1 = map.get(o1);
    			 Object v2 = map.get(o2);
    			 return ((Comparable<Object>) v1).compareTo(v2);
    		 }
    	 });
    	 Collections.reverse(list); 	// 주석시 오름차순
    	 return list;
     }
	
}
