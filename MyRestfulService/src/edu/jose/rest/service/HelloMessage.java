package edu.jose.rest.service;

public class HelloMessage {

	private String message;
	private int size;
	
	public String getMessage() {
		return message;
	}

	public int getSize() {
		return size;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HelloMessage() {		
	}
	
	public HelloMessage(String message, int size) {
		this.message = message;
		this.size = size;
	}
	
}
