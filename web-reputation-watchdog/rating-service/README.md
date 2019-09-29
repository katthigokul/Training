User Story: The system should have a rating service that computes sentimental analysis score based on aspects
Check List:

1. POC for Sentiment Analysis
2. Rating Service: Sentiment Analysis and ABSA
3. Weightage for reviewer, fields for rating service
4. Write down the rules in wiki based on which  rating will be calculated
5. After sentimental analysis is done calculate the rating for the review
6. Dockerize Rating Service

Calculation of Reviewer Weightage
To get Reviewer Weightage, We will divide the reviewer score according to rules given below:
Rules
When Reviewer Score is between 0-250, we will assign them 1 Reviewer Weightage
When Reviewer Score is between 251-500, we will assign them 2 Reviewer Weightage
When Reviewer Score is between 501-750, we will assign them 3 Reviewer Weightage
When Reviewer Score is more than 750, we will assign them 4 Reviewer Weightage
Calculation of Final Weightage
When Sentiment Analysis Score is 4 which means it is very positive we will assign .2
When Sentiment Analysis Score is 3 which means it is positive we will assign .1
When Sentiment Analysis Score is 2  which means it is neutral we will assign 0
When Sentiment Analysis Score is 1  which mean it is negative we will assign -.1
When Sentiment Analysis Score is 0 which mean it is very negative we will assign -.2
We will then multiply this with the reviewer Score. After that we will add 3 to that. This score will be added to the Aspect Score We received and its average will be sent back.

