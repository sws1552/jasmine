package js.bs.delivery.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import js.bs.delivery.vo.DeliveryVO;


@Service
public class DeliveryAPI {
	public List DeliveryInfo(String t_key,String t_code,String t_invoice) {
		List<DeliveryVO> Delivery = new ArrayList<DeliveryVO>();
		try {
			URL obj = new URL("https://info.sweettracker.co.kr/api/v1/trackingInfo?t_key="+t_key+"&t_code="+t_code+"&t_invoice="+t_invoice);
			HttpURLConnection conn = (HttpURLConnection)obj.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			String resultLine ="";
			while ((line=in.readLine())!=null) {
				resultLine += line;
			}
			in.close();
			System.out.println(resultLine);
			if (resultLine != null) {
				
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(resultLine);
				
				String itemName = element.getAsJsonObject().get("itemName").getAsString();
				JsonArray trackingDetails = element.getAsJsonObject().get("trackingDetails").getAsJsonArray();
				int size = trackingDetails.size();
				
				for (int i = 0; i < size; i++) {
					DeliveryVO deliVO = new DeliveryVO();	
					String timeString = trackingDetails.get(i).getAsJsonObject().get("timeString").getAsString();
					String where = trackingDetails.get(i).getAsJsonObject().get("where").getAsString();
					String telno = trackingDetails.get(i).getAsJsonObject().get("telno").getAsString();
					String kind = trackingDetails.get(i).getAsJsonObject().get("kind").getAsString();
					System.out.println("timeString >> " + timeString);
					System.out.println("where >> " + where);
					System.out.println("telno >> " + telno);
					System.out.println("kind >> " + kind);
					deliVO.setTimeString(timeString);
					deliVO.setWhere(where);
					deliVO.setTelno(telno);
					deliVO.setKind(kind);
					Delivery.add(deliVO);
				}
				System.out.println("itemName >> "+itemName);
			}
			return Delivery;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Delivery;
	}
}
