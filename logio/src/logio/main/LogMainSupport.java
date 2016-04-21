package logio.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logio.util.LogUtil;
import logio.variable.LogVar;

public class LogMainSupport implements LogMainService{

	@Override
	public List<String> addMap(String[] strArr) {
		// TODO Auto-generated method stub
		List<String> arr = new ArrayList<String>();
		
		for(int i = 0; i < strArr.length ; i ++){
			if(strArr[i].toString().indexOf(LogVar.HTTP_STATUS_SUCCESS) == 0){
				arr.add(strArr[i].toString());
			}
		}
		
		return arr;
	}

	@Override
	public Map<String, Object> requestApi(List<String> list) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		
		List<String> apiKeyList = new ArrayList<String>();
		List<String> newArrList = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] apikey = null;
		String[]  resultApi = null;
		
		
		for(int i = 0; i < list.size(); i++){
			apikey = list.get(i).split(",");
			resultApi = apikey[1].split("[?&=]");
			apiKeyList.add(resultApi[2].toString());
		}
		newArrList = LogUtil.listSet(apiKeyList);
		map = LogUtil.sumCount(newArrList, apiKeyList);
		result = LogUtil.getMax(map, newArrList);
		System.out.println("apikey list : " + map.size());
		
		return result;
	}
	
	@Override
	public Map<String, Object> requestServiceId(List<String> list) {
		// TODO Auto-generated method stub
		List<String> serviceIdList = new ArrayList<String>();
		List<String> newArrList = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] serviceId = null;
		String[] resultService = null;
		
		for(int i = 0; i < list.size(); i++){
			serviceId = list.get(i).split(",");
			resultService = serviceId[1].split("[?&=]");
			resultService = resultService[0].split("[/]");
			serviceIdList.add(resultService[4].toString());
		}

		newArrList = LogUtil.listSet(serviceIdList);
		map = LogUtil.sumCount(newArrList, serviceIdList);
		System.out.println("serviceId list : " + map.size());
		
		return map;
	}
	
	@Override
	public Map<String, Object> requestBrowser(List<String> list) {
		// TODO Auto-generated method stub
		List<String> browserList = new ArrayList<String>();
		List<String> newArrList = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] resultBrowser = null;
		
		for(int i = 0; i < list.size(); i++){
			resultBrowser = list.get(i).split(",");
			browserList.add(resultBrowser[2].toString());
		}

		newArrList = LogUtil.listSet(browserList);
		map = LogUtil.sumCount(newArrList, browserList);
		System.out.println("browser list : " + map.size());
		
		return map;
	}

	@Override
	public String outputTemplate(int succCount, Map<String, Object> apiKeyMap, Map<String, Object> serviceIdMap,
			Map<String, Object> browserMap) {
		// TODO Auto-generated method stub
		String result = "";
		String serviceStr = "";
		String browserStr = "";
		
		// serviceIdMap sort
		Iterator<?> st = LogUtil.sortByValue(serviceIdMap).iterator();
		
		int serviceId = 0;
		while(st.hasNext()){
			if(serviceId == 3){
				break;
			}
			String temp = (String) st.next();
			serviceStr += temp + " : " + serviceIdMap.get(temp) + LogVar.ENTER;
			serviceId++;
		}
		
		// browserMap sort
		Iterator<?> bt = LogUtil.sortByValue(browserMap).iterator();
		
		int total = succCount;	//200 성공 합계 succCount
		while(bt.hasNext()){
			String temp = (String) bt.next();
//			System.out.println(temp + " : " + browserMap.get(temp).toString());
//			System.out.println(temp + " : " + Math.round((double)Integer.parseInt(browserMap.get(temp).toString()) / (double)total * 100) + "%");
			browserStr += temp + " : " + Math.round((double)Integer.parseInt(browserMap.get(temp).toString()) / (double)total * 100) + "%" + LogVar.ENTER;
		}
		
		result = LogVar.STEP_ONE + LogVar.ENTER
			   + apiKeyMap.get(LogVar.MAXAPI).toString() + LogVar.ENTER + LogVar.ENTER
			   + LogVar.STEP_TWO + LogVar.ENTER
			   + serviceStr + LogVar.ENTER
			   + LogVar.STEP_THREE + LogVar.ENTER
			   + browserStr;
		
		return result;
	}

}
