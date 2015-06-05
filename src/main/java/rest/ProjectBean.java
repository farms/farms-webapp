package rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProjectBean {
	public String getProject() throws IOException {
		URL url = new URL("http://localhost:8182/project");
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n;
		while ((n = is.read(buf)) != -1) {
			bos.write(buf, 0, n);
		}
		return new String(bos.toByteArray(), StandardCharsets.UTF_8);
	}
}
