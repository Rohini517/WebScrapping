package com.webscrapping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;


public class ParsingHtml{
	public void main (String args[]){
//		JSONObject obj = new JSONObject();
//		JSONObject obj1 = new JSONObject();
//		JSONArray listOfWords = new JSONArray();
//		JSONArray listOfLinks = new JSONArray();
		String url1 = "https://www.google.com/";
		String str[]= {"D:\\s1.txt","D:\\s2.txt"};
		try
        {
           File new_dir = new File("D:\\Puppeteer_Lib");
           String cmd[]={"cmd.exe","/c","start","npm run test",url1,str[0]};
           Runtime.getRuntime().exec(cmd,null,new_dir);
           //ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "node test.js",url1,str[0]);
//           pb.directory(new_dir);
//           pb.start();
           
        }
        catch (Exception e)
        {
            System.out.println("Invalid");
            e.printStackTrace();
        }
		
//		File html = new File("D:\\src.txt");
//		Document doc = Jsoup.parse(html,"UTF-8","");
//		Elements els = doc.select("a");
//		for (Element e : els) {
//			listOfWords.put(e.text());
//		    listOfLinks.put(e.attr("href"));
//			//System.out.println(e.attr("href"));
//		}
//		PrintWriter pw = res.getWriter();
//		res.setContentType("text/html");
//		try {
//			obj.put("word", listOfWords);
//			pw.println("<p>"+obj+"</p>");
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			obj1.put("Links", listOfLinks);
//			pw.println("<p>"+obj1+"</p>");
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		pw.close();
		
	}

}
