package com.duncancodes.librarymanagement.notifications;

public class SmsNotification extends Notification {

	@Override
	public void sendNotification(String message) {
		System.out.println("SMS Message: " + message);
	}
}
