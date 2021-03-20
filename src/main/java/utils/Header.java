package utils;

import java.util.ArrayList;
import java.util.List;

public class Header {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;
	private String message;
	private Object data;

	public Header() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Header(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
}
