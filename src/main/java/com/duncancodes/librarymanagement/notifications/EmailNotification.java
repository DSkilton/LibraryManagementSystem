package com.duncancodes.librarymanagement.notifications;

public class EmailNotification extends Notification {

	@Override
	public void sendNotification(String message) {
		System.out.println("Email Notificate: " + message);
	}
}
