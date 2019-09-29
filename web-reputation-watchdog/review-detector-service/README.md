Micro-Service Name- review-detector-service

Description- 
		
		1. The purpose of this service is to detect fake and genuine reviews.

		2. This micro-service is taking review details, those are "entityTitle","entityReview", "reviewTitle", "reviewDescription",
		"reviewedBy" and "reviewerScore" from orchestration-service and returning to orchestration-service all the mentioned details 
		with tag "genuine:true/false" after processing the "reviewDescription".

		3. The "reviewDescription" is first spilt into tokens/words.

		4. There are 3 rules to detect fake/genuine review-

			(i)   Number of tokens/words in the reviewDescription is more than 3 and less than 100. Otherwise it returns 
				"genuine: false"
			(ii)  If the above condition is true, the tokens are then catagorized on the basis of parts of speech. If the number 					of adjectives and adverbs is more than 6 (as the product is having maximum of 5 aspects per entity) then the 					service returns "genuine: false". Otherwise it returns "genuine: true".
			(iii) If in above condition the number of adjectives and adverbs is less than 6, then it goes to next loop where it 					counts the number of nouns. If the number of nouns is more than 3 the service returns "genuine: false". 				Otherwise it returns "genuine: true". 



