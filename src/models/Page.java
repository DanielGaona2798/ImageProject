package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Page {

	public List<String> getImages() throws IOException{
		List<String> list = new ArrayList<>();
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL("https://unsplash.com/wallpaper");
		URLConnection hc = url.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(hc.getInputStream()));

		String inputLine;
		Pattern p = Pattern.compile("<img[^>]*.jpg");
		while ((inputLine = in.readLine()) != null){
			Matcher m = p.matcher(inputLine);
			if (m.find()) {
				String src = m.group();
				int startIndex = src.indexOf("src=") + 5;
				String srcTag = src.substring(startIndex, src.length());
				list.add(srcTag);
			}
		}
		in.close();
		return list;
	}
	
	public void downloadImages(List<String> lsit) throws IOException{
		for (String string : lsit) {
			URL website = new URL(string);
			Path path = Paths.get("src/datos/" + getNameImage(string));
			try (InputStream in = website.openStream()) {
				Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
	
	
	public String getNameImage(String a){
		String result = "";
		String init = "";
		for (int i = a.length()-1; i>=0; i--) {
			if (a.charAt(i) != '/') {
				init += a.charAt(i);
			}else{
				break;
			}
		}
		for (int i = init.length()-1; i>=0; i--) {
			result += init.charAt(i); 
		}
		return result;
	}
	
	public static void main(String[] args) {
		Page page = new Page();
		try {
			page.downloadImages(page.getImages());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
