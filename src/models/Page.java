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
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL("http://wallpaperswide.com/");
		URLConnection hc = url.openConnection(proxy);
		hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
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
	
	public ArrayList<String> downloadImages(List<String> lsit) throws IOException{
		ArrayList<String> list =  new ArrayList<>();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		for (String string : lsit) {
			URL url = new URL(string);
			URLConnection hc = url.openConnection(proxy);
			Path path = Paths.get("src/datos/" + getNameImage(string));
			list.add(getNameImage(string));
			InputStream in = hc.getInputStream();
			Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
		}
		return list;
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
}
