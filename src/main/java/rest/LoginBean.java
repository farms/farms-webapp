package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Researcher;

import com.google.gson.Gson;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

	private String name;
	private String email;
	private String password;
	private String passwordAgain;
	private String response;
	
	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getPasswordAgain() {
		return passwordAgain;
	}




	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}



	public String getResponse() {
		return response;
	}




	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
	
	public String addResearcher() throws IOException {
		URL url = new URL("http://localhost:8182/login");
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		OutputStream os = urlConnection.getOutputStream();
		
		Researcher researcher = new Researcher();
		researcher.setName(this.name);
		researcher.setEmail(this.email);
		researcher.setPassword(this.password);
		Gson gson = new Gson();
		String json = gson.toJson(researcher);
        os.write(json.getBytes());
        os.flush();
        
        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
        	response = "<div class='alert alert-warning alert-dismissable'>" +
			    			"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
			                "<h4><i class='icon fa fa-warning'></i> Could not connect to the server</h4>" +
			                urlConnection.getResponseMessage() +
			    		"</div>";
            throw new RuntimeException("Failed : HTTP error code : "
                + urlConnection.getResponseCode());
        }
        else response = "<div class='alert alert-success alert-dismissable'>" +
							"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>" +
				            "<h4><i class='icon fa fa-check'></i> Success!</h4>" +
				            "The researcher was registred successfully, now you are good to go." +
						"</div>";

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (urlConnection.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        urlConnection.disconnect();
		return output;
	}
}
