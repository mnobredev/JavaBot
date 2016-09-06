package botPackage;

import java.io.*;
import java.net.*;

public class botactions {
	
	static Thread t1;
	static URL obj;
	static HttpURLConnection con;
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void sendPost(double time, double clMin, double clMax, double phMin, double phMax, int id) throws Exception {

		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true)
				{
					String url = "http://localhost:80/pool/listener.php";

					try {
						obj = new URL(url);
					} catch (MalformedURLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
						con = (HttpURLConnection) obj.openConnection();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						con.setRequestMethod("GET");
					} catch (ProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					con.setRequestProperty("User-Agent", USER_AGENT);
					con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

					double resultclr = Math.random() * (clMax - clMin) + clMin;
					double resultph = Math.random() * (phMax - phMin) + phMin;
					String clr = String.format("%.2f", resultclr);
					clr.replace(",", ".");
					String ph = String.format("%.2f", resultph);
					ph.replace(",", ".");
					String urlParameters = "clr="+clr+"&ph="+ph+"&mac="+id;

					// Send post request
					con.setDoOutput(true);
					try {
						DataOutputStream wr = new DataOutputStream(con.getOutputStream());
						wr.writeBytes(urlParameters);
						wr.flush();
						wr.close();
						int responseCode = con.getResponseCode();
						System.out.println("\nSending 'POST' request to URL : " + url);
						System.out.println("Post parameters : " + urlParameters);
						System.out.println("Response Code : " + responseCode);

						BufferedReader in = new BufferedReader(
								new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();

						//print result
						System.out.println(response.toString());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						Thread.sleep((long) time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}



		});
		t1.start();
	}

}
