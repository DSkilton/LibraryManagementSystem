package com.duncancodes.librarymanagement.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsNotification implements Notification {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsNotification.class);

	@Override
	public void sendNotification(String message) {
		System.out.println("SMS Notification: " + message);
	}
}
