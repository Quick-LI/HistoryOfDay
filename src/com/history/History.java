package com.history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class History {
	BufferedReader in;
	BufferedWriter out;
	FileWriter fileWriter;
	public History() throws IOException {
		URL url = new URL("https://tool.lu/todayonhistory/");
		File file = new File("F:/123.txt");
		fileWriter = new FileWriter(file);
		in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
		out = new BufferedWriter(fileWriter);
	}

	//对网页源码进行解析，并将解析结果保存到文件中
	public void parsingStr() throws IOException {
		String text = getStr().toString();
		String pattern = "<li>(.*?)<";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		 while (m.find()) {
			 String text2 = m.group();
			 String temp = text2.substring(4,text2.lastIndexOf("<"));
			 if(!temp.isBlank()) {
			 	out.write(temp);
				out.newLine();
			 }
		 }
		 out.close();
	}
	//获取网页源码
	public StringBuffer getStr() throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		String line = in.readLine();
		while(line != null) {
			stringBuffer.append(line);
			line = in.readLine();
		}
		in.close();
		return stringBuffer;
	}

	//获取网页源码并打印在文件中
	public void getText() throws IOException {
		String line = in.readLine();
		while(line != null) {
			out.write(line);
			out.newLine();
			line = in.readLine();
		}
		in.close();
		out.close();
	}
}
