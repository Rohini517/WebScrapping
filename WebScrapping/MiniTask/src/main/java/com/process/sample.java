package com.process;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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


//import task1.WorkerThread;

public class sample extends HttpServlet{	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String url1 = req.getParameter("url1");
		String url2 = req.getParameter("url2");
//		String url1="https://www.google.com/";
//		String url2="http://tomcat.apache.org/";
		String url3 = req.getParameter("url3");
		String url4 = req.getParameter("url4");
		String url5 = req.getParameter("url5");
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String file[]= {"D:\\s1.txt","D:\\s2.txt","D:\\s3.txt","D:\\s4.txt","D:\\s5.txt"};
		//String file[]= {"D:\\s1.txt","D:\\s2.txt","D:\\s3.txt","D:\\s4.txt","D:\\s5.txt"};
		String urlsAndFileLocation = url1+","+file[0]+","+url2+","+file[1]+","+url3+","+file[2]+","+url4+","+file[3]+","+url5+","+file[4];
	
	
        try
        {
        	File new_dir = new File("D:\\Puppeteer_Lib");
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("cmd.exe","/c","npm run test").directory(new_dir);
			Process process = processBuilder.start();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			writer.write(urlsAndFileLocation);
			writer.close();
			
		    BufferedReader reader = new BufferedReader(new InputStreamReader(
		            process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    reader.close();
		    process.waitFor();
           
        }
        catch (Exception e)
        {
            System.out.println("Invalid");
            e.printStackTrace();
        }
//--------------------------------------------------------------------------------------    
        ExecutorService executor = Executors.newFixedThreadPool(5);  
        for (int i = 0; i < 5; i++) {  
            @SuppressWarnings("rawtypes")
			Callable worker = new WorkerThread(file[i]); 
            @SuppressWarnings("unchecked")
			Future<String> workerThread = executor.submit(worker); 
            try {
				System.out.println("<p>"+workerThread.get()+"</p>");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
          }  
        executor.shutdown(); 
        
 }
    
}
class WorkerThread implements Callable<String> {  
    private String fileName;  
    
    public WorkerThread(String s){  
        this.fileName=s;  
    }  
     public String call() throws IOException, JSONException { 
    	 JSONObject obj = new JSONObject();
    	 JSONArray listOfWords = new JSONArray();
    	 JSONArray listOfLinks = new JSONArray();
    	 File html = new File(fileName);
 		Document doc = Jsoup.parse(html,"UTF-8","");
 		Elements els = doc.select("a");
 		for (Element e : els) {
 			listOfWords.put(e.text());
 		    listOfLinks.put(e.attr("href"));
 		}
         try {
			obj.put("word",listOfWords);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}	 
         try {
			obj.put("link",listOfLinks);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}    
        String str = obj.toString();
        return str;
    }  
    
} 




