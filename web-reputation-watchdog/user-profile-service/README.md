Micro-Service Name- User-Profile-Service

Description-
	1.	User-profile-service is taking details of users (firstName, lastName, emailId, password and phone number) and it stores this data in mongodb.
	2.EmailId and password is send back to authenticationservice through rabbitmq topic exchange for authentication.
	3.The Profile gets updated according to the userdetails by their EmailIds.
	


