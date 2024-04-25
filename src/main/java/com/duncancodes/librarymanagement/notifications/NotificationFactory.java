package com.duncancodes.librarymanagement.notifications;

public class NotificationFactory {

	public static Notification createNotification (NotificationType type) {
		switch (type) {
			case SMS:
				return new SmsNotification();
			case EMAIL:
				return new EmailNotification();
			default:
				throw new IllegalArgumentException("Invalid notification type: " + type);
		}
	}
}
