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
		//AccessToken 얻는 메서드
		
		
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			/*
				클래스 URL은 World Wide Web의 "자원"에 대한 포인터 인 Uniform Resource Locator를 나타냅니다. 
				리소스는 파일이나 디렉토리와 같은 단순한 것일 수도 있고 데이터베이스 또는 
				검색 엔진에 대한 쿼리와 같이 더 복잡한 개체에 대한 참조 일 수도 있습니다. 
			 */
			// String 표현에서 URL 객체를 생성합니다.(String에 대한 URL객체 생성)
			URL url = new URL(reqURL);
			
			
			/*
			  	구조
			   java.lang.Object
					- java.net.URLConnection
						- java.net.HttpURLConnection
			   	추상 클래스 URLConnection은 애플리케이션과 URL 간의 통신 링크를 나타내는 모든 클래스의 수퍼 클래스입니다.
				이 클래스의 인스턴스는 URL에서 참조하는 리소스를 읽고 쓰는 데 모두 사용할 수 있습니다.
			 	일반적으로 URL에 대한 연결을 만드는 것은 여러 단계의 프로세스입니다
			 */
			
			/*
				각 HttpURLConnection 인스턴스는 단일 요청을 만드는 데 사용되지만 HTTP 서버에 대한 기본 네트워크 연결은 다른 인스턴스에서 투명하게 공유 될 수 있습니다. 
				요청 후 HttpURLConnection의 InputStream 또는 OutputStream에서 close () 메서드를 호출하면이 인스턴스와 연결된 네트워크 리소스가 해제 될 수 있지만 공유 영구 연결에는 영향을주지 않습니다. 
				disconnect () 메서드를 호출하면 해당 시간에 영구 연결이 유휴 상태 인 경우 기본 소켓이 닫힐 수 있습니다.
			 */
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			// url 요청 방법 설정
			conn.setRequestMethod("POST");
			
			/*URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. 
			URL 연결은 입출력에 사용될 수 있다. 
			URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 
			그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.*/
			conn.setDoOutput(true);
			
			// 1바이트씩 출력하기 위한 버퍼 writer 객체 생성
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			// 토큰을 받기위해 요청시 요구하는 파라미터를 전송
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=0d2976f52e2191eed8909314c41a95f8");
			sb.append("&redirect_uri=http://192.168.1.108:8088/jasmine/member/memberForm.do");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode >> " + responseCode);
			
			// 요청을 통해 얻은 JSON 타입의 Response 메시지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body > " + result);
			
			// GSON 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
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
		 // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			
			// 요청에 필요한 Header에 포함될 내용
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
			// properties와 kakao_account는 '값'이 json객체 타입이라서 값을 다시 jsonobject로 가져옴
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			// id는 '값'이 json객체 타입이 아니기 때문에 getasString으로 바로 값 가져옴.
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
