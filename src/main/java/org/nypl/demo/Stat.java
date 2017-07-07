package org.nypl.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Stat {
	Map<String, Object> stats = new HashMap<String, Object>();
	
	public Map<String, Object> getStats() {
		return stats;
	}
	public void setStats(Map<String, Object> stats) {
		this.stats = stats;
	}
	
	
}
