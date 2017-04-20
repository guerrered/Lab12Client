import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
	static DataOutputStream out;
	static HttpURLConnection conn;
	List <Player> playerList = new ArrayList<>();
	
	public void add(String bib, String time){
		time = timeFormat(Long.parseLong(time));
		System.out.println(time);
		Player p = new Player(bib, time);
		playerList.add(p);
	}
	
	public static String timeFormat( long duration ) {
	    final TimeUnit scale = TimeUnit.MILLISECONDS;
	    
	    long days = scale.toDays(duration);
	    duration -= TimeUnit.HOURS.toMillis(days);
	    long hours = scale.toHours( duration );
	    duration -= TimeUnit.HOURS.toMillis( hours );
	    long minutes = scale.toMinutes( duration );
	    duration -= TimeUnit.MINUTES.toMillis( minutes );
	    long seconds = scale.toSeconds( duration );
	    duration -= TimeUnit.SECONDS.toMillis( seconds );
	    long millis = scale.toMillis( duration );
	    
	    return String.format("%d:%02d:%02d:%03d",hours, minutes, seconds, millis);
 }
	
	public void send(){
		URL site = null;
		try {
			site = new URL("http://localhost:8000/sendresults");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = (HttpURLConnection) site.openConnection();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// now create a POST request
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn.setDoOutput(true);
		conn.setDoInput(true);
		//DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		try {
			out = new DataOutputStream(conn.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//send info
		try{
		
		
			
		
		InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

		// string to hold the result of reading in the response
		StringBuilder sb = new StringBuilder();

		// read the characters from the request byte by byte and build up
		// the Response
		int nextChar;
		while ((nextChar = inputStr.read()) > -1) {
			sb = sb.append((char) nextChar);
		}
		System.out.println("Return String: " + sb);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		conn.disconnect();
	}
}
