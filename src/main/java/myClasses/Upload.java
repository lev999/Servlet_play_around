package myClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Upload extends HttpServlet {
	static final String SAVE_DIR = Paths.get("").toAbsolutePath().toString()+"\\";
	static final int BUFFER_SIZE = 4096;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set response content type
		resp.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + "My servlet works2" + "</h1>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Gets file name for HTTP header
		String fileName = request.getHeader("fileName");
		File saveFile = new File(SAVE_DIR + fileName);

		// prints out all header values
		System.out.println("===== Begin headers =====");
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = names.nextElement();
			System.out.println(headerName + " = "
					+ request.getHeader(headerName));
		}
		System.out.println("===== End headers =====\n");

		// opens input stream of the request for reading data
		InputStream inputStream = request.getInputStream();

		// opens an output stream for writing file
		FileOutputStream outputStream = new FileOutputStream(saveFile);

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		System.out.println("Receiving data...");

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		System.out.println("Data received.");
		outputStream.close();
		inputStream.close();

		System.out.println("File written to: " + saveFile.getAbsolutePath());

		// sends response to client
		response.getWriter().print("UPLOAD DONE");
	}
}
