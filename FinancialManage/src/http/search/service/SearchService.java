package http.search.service;

import java.util.List;
import java.util.Map;

public interface SearchService {
	public List<Map<String,Object>> Search(List<Object> params);

}
