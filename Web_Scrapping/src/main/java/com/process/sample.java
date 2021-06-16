package com.process;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class sample extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String url1 = req.getParameter("url1");
		//String url2 = req.getParameter("url2");
		String str[]= {"D:\\s1.txt","D:\\s2.txt"};
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
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
        JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONArray listOfWords = new JSONArray();
		JSONArray listOfLinks = new JSONArray();
		
		File html = new File(str[0]);
		Document doc = Jsoup.parse(html,"UTF-8","");
		Elements els = doc.select("a");
		for (Element e : els) {
			listOfWords.put(e.text());
		    listOfLinks.put(e.attr("href"));
		}
		try {
			obj.put("word", listOfWords);
			pw.println("<p>"+obj+"</p>");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			obj1.put("Links", listOfLinks);
			pw.println("<p>"+obj1+"</p>");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		pw.close();
 }
    
}
