package logio.main;

import java.util.List;
import java.util.Map;

public interface LogMainService {
	
	// 200 성공 데이타 리스트 추출 담는 작업
	List<String> addMap(String[] strArr);
	
	// request apikey 
	Map<String, Object> requestApi(List<String> list);
	
	// request service id list 
	Map<String, Object> requestServiceId(List<String> list);
	
	// request browser id list 
	Map<String, Object> requestBrowser(List<String> list);
	
	String outputTemplate(int succCount, Map<String, Object> apiKeyMap,
			Map<String, Object> serviceIdMap, Map<String, Object> browserMap);
}
