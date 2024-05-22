package com.duncancodes.librarymanagement.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotification implements Notification {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotification.class);

	@Override
	public void sendNotification(String message) {
		LOGGER.debug("Email Notification: " + message);
	}
}
