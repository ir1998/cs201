package server;

import java.io.IOException;
import java.io.InputStream;

public class HTTPRequest {
	InputStream input;
	String HTTPRequestString;
	StringBuffer request = new StringBuffer(2048);
	String requestMethod;
	String requestURI;
	String requestProtocol;
	String requestHostname;
	int errorcode = 0;

	public HTTPRequest(InputStream in) {
		this.input = in;
		getRequestFromStream();
	}
	
	private void getRequestFromStream() {
		int i;
		byte[] buffer = new byte[2048];

		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}

		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		
		HTTPRequestString = request.toString();
		// Write the request in its entire form to the output stream (here, the console).
		// If a log file were to be kept this code could be replaced with code to append
		// the request to a file. In that way a complete record of all requests made to
		// the server could be made (Apache does this). This record could then be used
		// in analysis of traffic or in the identification of problems.
		System.out.println(HTTPRequestString);
	}
	
	public int parseEntireRequest() {
		// Strip out the Request Method
		int index1, index2;
		// Locate the first Space in Request
		index1 = HTTPRequestString.indexOf(' ');
		if (index1 != -1 && index1 > 0) {
			// Extract substring containing request method, read up to first space
			setMethod(HTTPRequestString.substring(0, index1));
			// Locate second space in request, signifies the end of the requested resource
			index2 = HTTPRequestString.indexOf(' ', index1 + 1);
			if (index2 > index1 + 1) {
				// Extract substring containing URI
				setURI(HTTPRequestString.substring(index1 + 1, index2));
				index1 = index2 + 1;
				// Locate first CRLF
				index2 = HTTPRequestString.indexOf("\r\n", index1);
				if (index2 > index1) {
					// Extract substring containing Protocol
					setProtocol(HTTPRequestString.substring(index1, index2));
					index1 = index2 + 1;
					// Locate Second CRLF
					index2 = HTTPRequestString.indexOf("\r\n", index1 + 1);
					if (index2 > index1) {
						// Extract substring containing Hostname and Port
						setHost(HTTPRequestString.substring(index1 + 1, index2));
					} else {
						errorcode = 4; // Error in extracting Hostname and Port
					}
				} else {
					errorcode = 3; // Error in extracting Protocol Information
				}
			} else {
				errorcode = 2; // Error extracting URI from Request
			}
		} else {
			errorcode = 1; //Could not extract Request Method
		}
		return errorcode;
	}
	
	public String getErrorMessage() { 
		String errormessage;
		switch (errorcode) {
		case 1:
			errormessage = "Parse Error 01 - Could not extract request Method";
			break;
		case 2:
			errormessage = "Parse Error 02 - Error extracting URI from Request";
			break;
		case 3:
			errormessage = "Parse Error 03 - Error extracting Protocol from Request";
			break;
		case 4:
			errormessage = "Parse Error 04 - Error extracting hostname and port";
			break;
		default:
			errormessage = "Parse Error 00 - Unkn own Error";
			break;
		}
		return errormessage;
	}
	
	public String getRequest() {
		return request.toString();
	}
	private void setMethod(String method) {
		requestMethod = method;
	}
	public String getMethod() {
		return requestMethod;
	}
	private void setURI(String uriin) {
		requestURI = uriin;
	}
	public String getURI() {
		return requestURI;
	}
	private void setProtocol(String protin) {
		requestProtocol = protin;
	}
	public String getProtocol() {
		return requestProtocol;
	}
	private void setHost(String hostin) {
		requestHostname = hostin;
	}
	public String getHost() {
		return requestHostname;
	}
	public InputStream getInput() {
		return input;
	}
	public String getHTTPRequestString() {
		return HTTPRequestString;
	}
}

