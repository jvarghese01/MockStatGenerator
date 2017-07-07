package org.nypl.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

public class StatGenerator {

	public static void main(String[] args) throws ParseException, IOException {
		BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("/Users/nypltech/Downloads/stats.json")));
		Stat stats = new Stat();
		Map<String, Object> stat;
		String dt = "2016-01-01";  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		dt = sdf.format(c.getTime());  // dt is now the new date
		String[] formats = {"Books","DVD","Audiobook", "Blue-ray", "E-Audiobook", "E-book","Large Print","CD"};
		String[] ptypes = {"82","61","72", "60", "106", "50", "31", "30", "84", "62", "11", "70", "10", "150"};
		String[] location_codes = {"maj0a","nda0n","rcma2", "mmj0v", "os", "rc2ma", "mal82", "rcmb2", "fta0f", "maj0v", "mma2f", "bea0v", "mma2n", "bcj0i", "mmy0v", "ssj0v", "mma0f", "bey0v", "mma0v", "bcj0f", "mra0f", "be", "mpj0i"};
		String[] patron_home_library_codes = {"ht","ma","hd","ml","ewa","fe","dy","tm","ns","my","jm","pm","kp","br","yv","hs","in","mm","lb","cl","bl","ss","bc","rs","vc","mh","sa","mp","ep","gk","ba","th","se","ot","gc","xfill","nd","be","pr","lm"};
		String[] op_codes = {"checkout","checkin","hold","filled_hold","renewal"};
		Map<String, String> codes = loadCodes(new File("/Users/nypltech/Downloads/location_codes.csv"));
		int idInt=0;
		Random generator = new Random();

			for (int days=0;days<366;days++){
				for(int o=0;o<generator.nextInt(10500);o++){
				for(int types=0;types<formats.length;types++){
					String id = ++idInt + "";
	
					//int num = generator.nextInt(150000);
					int age = generator.nextInt(75);
					String ptype = ptypes[generator.nextInt(13)];
					String location_code = codes.get(location_codes[generator.nextInt(22)]);
					String patron_home_library_code = codes.get(patron_home_library_codes[generator.nextInt(30)]);
					String op_code = op_codes[generator.nextInt(4)];

					
					stat = new HashMap<String, Object>();
					stat.put("id",id);
					stat.put("date", dt);
					stat.put("type", formats[types]);

					//stat.put("count",num);
					stat.put("age",age);
					stat.put("ptype",ptype);
					stat.put("location_code",location_code);
					stat.put("op_code",op_code);

					
					stats.setStats(stat);
					
					//bfw.write("{\"index\":{\"_id\":\""+id+"\"}}"+"\n");
					//bfw.write(toJSON(stat)+"\n");
					bfw.write("id"+","+"2333309322"+(generator.nextInt(8999)+1000)+","+dt+","+ptype+","+patron_home_library_code+","+formats[types]+","+location_code+","+op_code+"\n");
				}
				}
				c.add(Calendar.DATE, 1);  // number of days to add
				dt = sdf.format(c.getTime());  // dt is now the new date
	
			}
	
		bfw.flush();
		bfw.close();		
	}

	
	public static String toJSON(Object src){
		Gson gson = new Gson();
		return gson.toJson(src);
		
	}
	
	public static Map<String, String> loadCodes(File file) throws IOException{
		Map<String, String> codes = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine())!=null){
			String code[] = line.split(",");
			codes.put(code[0], code[1]);
		}	
		return codes;
	}
}
