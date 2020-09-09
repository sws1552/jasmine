package js.bs.common.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoAPI {
	public String getAccessToken(String authorize_code) {
		//AccessToken ��� �޼���
		
		
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			/*
				Ŭ���� URL�� World Wide Web�� "�ڿ�"�� ���� ������ �� Uniform Resource Locator�� ��Ÿ���ϴ�. 
				���ҽ��� �����̳� ���丮�� ���� �ܼ��� ���� ���� �ְ� �����ͺ��̽� �Ǵ� 
				�˻� ������ ���� ������ ���� �� ������ ��ü�� ���� ���� �� ���� �ֽ��ϴ�. 
			 */
			// String ǥ������ URL ��ü�� �����մϴ�.(String�� ���� URL��ü ����)
			URL url = new URL(reqURL);
			
			
			/*
			  	����
			   java.lang.Object
					- java.net.URLConnection
						- java.net.HttpURLConnection
			   	�߻� Ŭ���� URLConnection�� ���ø����̼ǰ� URL ���� ��� ��ũ�� ��Ÿ���� ��� Ŭ������ ���� Ŭ�����Դϴ�.
				�� Ŭ������ �ν��Ͻ��� URL���� �����ϴ� ���ҽ��� �а� ���� �� ��� ����� �� �ֽ��ϴ�.
			 	�Ϲ������� URL�� ���� ������ ����� ���� ���� �ܰ��� ���μ����Դϴ�
			 */
			
			/*
				�� HttpURLConnection �ν��Ͻ��� ���� ��û�� ����� �� �������� HTTP ������ ���� �⺻ ��Ʈ��ũ ������ �ٸ� �ν��Ͻ����� �����ϰ� ���� �� �� �ֽ��ϴ�. 
				��û �� HttpURLConnection�� InputStream �Ǵ� OutputStream���� close () �޼��带 ȣ���ϸ��� �ν��Ͻ��� ����� ��Ʈ��ũ ���ҽ��� ���� �� �� ������ ���� ���� ���ῡ�� ���������� �ʽ��ϴ�. 
				disconnect () �޼��带 ȣ���ϸ� �ش� �ð��� ���� ������ ���� ���� �� ��� �⺻ ������ ���� �� �ֽ��ϴ�.
			 */
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			// url ��û ��� ����
			conn.setRequestMethod("POST");
			
			/*URLConnection�� ���� doOutput �ʵ尪�� ������ ������ �����Ѵ�. 
			URL ������ ����¿� ���� �� �ִ�. 
			URL ������ ��¿����� ����Ϸ��� ��� DoOutput �÷��׸� true�� �����ϰ�, 
			�׷��� ���� ���� false�� �����ؾ� �Ѵ�. �⺻���� false�̴�.*/
			conn.setDoOutput(true);
			
			// 1����Ʈ�� ����ϱ� ���� ���� writer ��ü ����
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			// ��ū�� �ޱ����� ��û�� �䱸�ϴ� �Ķ���͸� ����
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=0d2976f52e2191eed8909314c41a95f8");
			sb.append("&redirect_uri=http://192.168.1.108:8088/jasmine/member/memberForm.do");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			// ��� �ڵ尡 200�̶�� ����
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode >> " + responseCode);
			
			// ��û�� ���� ���� JSON Ÿ���� Response �޽��� �о����
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body > " + result);
			
			// GSON ���̺귯���� ���Ե� Ŭ������ JSON �Ľ� ��ü ����
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token );
			System.out.println("refresh_Token : " + refresh_Token);
			
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}
	
	public HashMap<String, Object> getUserInfo(String access_Token){
		 // ��û�ϴ� Ŭ���̾�Ʈ���� ���� ������ �ٸ� �� �ֱ⿡ HashMapŸ������ ����
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			
			// ��û�� �ʿ��� Header�� ���Ե� ����
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode  :  " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			
			String line = "";
			String result = "";
			
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body" + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			System.out.println("element > " + element);
			// properties�� kakao_account�� '��'�� json��ü Ÿ���̶� ���� �ٽ� jsonobject�� ������
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			// id�� '��'�� json��ü Ÿ���� �ƴϱ� ������ getasString���� �ٷ� �� ������.
			String kakao_id = element.getAsJsonObject().get("id").getAsString();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakao_account.getAsJsonObject().get("email").getAsString();
	        String age_range = kakao_account.getAsJsonObject().get("age_range").getAsString();
	        System.out.println("age_range >> " + age_range);
	        
	        System.out.println("email > " + email);
	        String memail[] = email.split("@");
	        System.out.println("email1 >" + memail[0]);
	        System.out.println("email1 >" + memail[1]);
	        String email1 = memail[0];
	        String email2 = memail[1];
	        
	        String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
	        String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
	        String birthday_type  = kakao_account.getAsJsonObject().get("birthday_type").getAsString();
	        // System.out.println("kakao_id > " + kakao_id);
	        System.out.println("gender" + gender);
	        System.out.println("birthday" + birthday);
	        String birth_m = birthday.substring(0, 2);
	        String birth_d = birthday.substring(2);
	        
	        System.out.println("birth_m >> " + birth_m);
	        System.out.println("birth_d" + birth_d);
	        System.out.println("birthday_type" + birthday_type);
	        userInfo.put("age_range", age_range);
	        userInfo.put("kakao_id", kakao_id);
	        userInfo.put("nickname", nickname);
	        userInfo.put("email", email);
	        userInfo.put("email1", email1);
	        userInfo.put("email2", email2);
	        userInfo.put("gender", gender);
	        userInfo.put("birthday", birthday);
	        userInfo.put("birth_m", birth_m);
	        userInfo.put("birth_d", birth_d);
	        userInfo.put("birthday_type", birthday_type);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
}
