package http.utils;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonTools {

	private static final Object Object = null;

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param key
	 *            表示json字符串的头信息
	 * @param object
	 *            是对解析的集合的类型
	 * @return
	 */
	public static String createJsonString(String key,Object value) {
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
		JSONObject jsonObject=new JSONObject();
		jsonObject.put(key, value);
	//	jsonObject.fromObject(Object,jsonConfig);
		return jsonObject.toString();
	}

}
