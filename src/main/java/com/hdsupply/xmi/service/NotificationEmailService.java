package com.hdsupply.xmi.service;

import com.hdsupply.xmi.domain.FilterNotification;

public interface NotificationEmailService {

	void emailNotifications(FilterNotification filter);

}