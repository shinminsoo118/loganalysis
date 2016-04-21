package logio.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logio.fileio.FileIO;
import logio.util.LogUtil;
import logio.variable.LogVar;

public class LogMain {
	public static void main(String[] args){
		
//		long startTime = System.currentTimeMillis(); 
		String logFormatStr = "";
		String resultOutput = "";
		
		List<String> resultSuccessList = new ArrayList<String>();
		
		Map<String, Object> apiKeyMap = new HashMap<String,Object>();
		Map<String, Object> serviceIdMap = new HashMap<String,Object>();
		Map<String, Object> browserMap = new HashMap<String,Object>();
		
		
		FileIO io = new FileIO();											//file io
		
		LogMainSupport logSupport = new LogMainSupport();					//main support method
		
		String readStr = io.fileRead();	   									// input data
		
		logFormatStr = LogUtil.strReplaceAll(readStr);						// [ = 공백 , ] = , replaceAll하여 변경하는 메소드
		
		String[] arrlogList = logFormatStr.split(LogVar.ENTER); 
		
		if(arrlogList.length > 0){
			resultSuccessList = logSupport.addMap(arrlogList);				// 200 성공 데이타 추출
		}
		
		if(resultSuccessList.size() > 0){
			apiKeyMap = logSupport.requestApi(resultSuccessList);			// apikeydata max
			serviceIdMap = logSupport.requestServiceId(resultSuccessList);	// serviceiddata 최대요청수
			browserMap = logSupport.requestBrowser(resultSuccessList);		// browserdata 사용비율
			
			resultOutput = logSupport.outputTemplate(resultSuccessList.size(),
					apiKeyMap, serviceIdMap, browserMap);					// output format template
			System.out.println("**************output format string****");
			System.out.println(resultOutput);
			System.out.println("**************************************");
		}
		io.fileWrite(resultOutput);
//		System.out.println(resultSuccessList.size()); // 200 totalCount
//		long endTime = System.currentTimeMillis() - startTime;
//		System.out.println("end : " + endTime);   2404
		
	}
}
