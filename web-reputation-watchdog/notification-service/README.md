#notification-service

-> Consumes data like to whom notifications should be sent, from rabbitmq-queue named notification.

-> Notifies logged-in user that a new review is added when someone reviews entity posted by that user and discards them if user is not logged-in.

-> Implemented web-socket to achieve this service.
