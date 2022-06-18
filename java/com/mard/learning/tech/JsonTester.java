package com.mard.learning.tech;

import org.json.*;

import com.mard.util.FileUtilities;
import com.mard.util.TimeUtil;

public class JsonTester {
	
	public static void main(String[] args)  throws Exception {
		testJSon();
		System.out.println("Done...");
	}

	public static void testJSon() throws Exception {		
		String content1 = FileUtilities.readFile("C:\\json_small.txt");		
		
	   TimeUtil timeUtil = new TimeUtil();
	   timeUtil.logStartTime();
		
		parsePbp(content1);
		
		timeUtil.logEndTime();
	}
	
	public static void parsePbp(String jsonString) throws Exception {
		JSONObject obj = new JSONObject(jsonString);
//		String value = obj.getJSONObject("pageInfo").getString("pagePic");
//		System.out.println(value);

		JSONArray quarters = obj.getJSONArray("playGrps");		
		for (int i = 0; i < quarters.length() ; i++) //quarters.length()
		{
			JSONArray pbp = quarters.getJSONArray(i);
			int transcriptLength = pbp.length();
			System.out.println(String.format("Quarter: %d = %d pbp", i+1, transcriptLength));
			for (int j = 0; j<pbp.length(); j++) {
				String eventTime =  pbp.getJSONObject(j).getJSONObject("clock").getString("displayValue");
				String eventText =  pbp.getJSONObject(j).getString("text");
				String eventScoreA =  pbp.getJSONObject(j).getString("awayScore");
				String eventScoreB =  pbp.getJSONObject(j).getString("homeScore");
				System.out.println(String.format("  %s: %s:%s - %s", eventTime, eventText, eventScoreA, eventScoreB));
			}

		}
	}

}
