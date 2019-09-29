Micro-Service Name- Authentication-Service

Description-
	1.	Authentication-service is taking details of users (firstName, lastName, emailId, password and phone number) from 			registration-service through rabbitMQ and storing user's emailId and password in the MySQL database.

	2.	Then it is generating a jwt-token and pass it as part of the API request, and the server will know that it is that specific 			client because the request is signed with its unique identifier.

	3.	JWT is an encoded string, which is URL safe, that can contain an unlimited amount of data. 
	


