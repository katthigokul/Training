package com.stackroute.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Aspect;
import com.stackroute.domain.Review;
import com.stackroute.dto.EntityDTO;
import com.stackroute.dto.ReviewDTO;
import com.stackroute.repository.ReviewRepository;
import edu.emory.mathcs.backport.java.util.LinkedList;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class RatingService {

    SentimentReport sentimentReport;
    SentimentAnalyzer sentimentAnalyzer;
    ReviewRepository reviewRepository;
    Review reviewRatingService;

    /**
     * Constructor based Dependency injection to inject SentimentAnalyzer and SentimentReport here
     */
    @Autowired
    public RatingService(SentimentAnalyzer sentimentAnalyzer, SentimentReport sentimentReport, ReviewRepository reviewRepository) {
        this.sentimentAnalyzer = sentimentAnalyzer;
        this.sentimentReport = sentimentReport;
        this.reviewRepository = reviewRepository;
    }

    /**
     * getReviewAndSendRating function is used to get the review from Orchestration
     * service and then return the calculated aspect sentiment score.
     */
    public Review getReviewAndSendRating(Review review) throws IOException {
        EntityDTO entityDTO = new EntityDTO();
        ReviewDTO reviewDTO = new ReviewDTO();
        entityDTO.setEntityName(review.getEntityTitle());
        reviewDTO.setGenuine(review.isGenuine());
        reviewDTO.setReviewTitle(review.getReviewTitle());
        reviewDTO.setAspectReview(review.getEntityReview());

        SentimentReport sentimentResult = new SentimentReport();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(review.getReviewDescription());
        stanfordCoreNLP.annotate(coreDocument);
        Aspect aspectData = new Aspect();
        TypeReference<List<Aspect>> typeReference = new TypeReference<List<Aspect>>() {
        };
        ObjectMapper objectMapper = new ObjectMapper();
        /* Reading the Aspect List and Weightage. This is stored as JSON File */
        InputStream inputStream = new FileInputStream(new File("tag-weightage.json"));
        List<Aspect> aspects = objectMapper.readValue(inputStream, typeReference);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        sentimentAnalyzer.setPipeline();
        /* Reading the CoreLabelList to check */
        for (CoreLabel coreLabel : coreLabelList) {
            int index = coreLabelList.indexOf(coreLabel);
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            /*Checking if the word is a Noun */
            if (pos.equalsIgnoreCase("NN")) {
                for (int i = 0; i < aspects.size(); i++) {
                    /* Checking if the Noun is synonym of the aspects we have*/
                    if (aspects.get(i).getFields().contains(coreLabel.originalText())) {
                        for (CoreLabel coreLabel1 : coreLabelList.subList(index, coreLabelList.size())) {
                            String pos1 = coreLabel1.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                            /*Checking if the word is a Adjective */
                            if (pos1.equalsIgnoreCase("JJ")) {
                                sentimentResult = sentimentAnalyzer.getSentimentReport(coreLabel1.originalText(), aspects.get(i).getName(), review);
                                break;
                            }
                        }
                    }
                }
            }
        }
//        reviewRepository.save(sentimentResult.getReviewDTO());
        Optional<EntityDTO> entityDTO1 = reviewRepository.findById(review.getEntityTitle());
        if (entityDTO1.isEmpty()) {
            entityDTO.setEntityName(review.getEntityTitle());
            reviewDTO.setGenuine(review.isGenuine());
            reviewDTO.setReviewTitle(review.getReviewTitle());
            reviewDTO.setAspectReview(review.getEntityReview());
            LocalDateTime timeStamp = LocalDateTime.now();
            reviewDTO.setReviewedOn(timeStamp);
            List<ReviewDTO> reviewDTOS = new LinkedList();
            reviewDTOS.add(reviewDTO);
            entityDTO.setReviewDTOList(reviewDTOS);
            reviewRepository.save(entityDTO);
        } else {
            entityDTO = entityDTO1.get();
            List<ReviewDTO> reviewDTOS = entityDTO.getReviewDTOList();
            reviewDTO.setGenuine(review.isGenuine());
            reviewDTO.setReviewTitle(review.getReviewTitle());
            reviewDTO.setAspectReview(review.getEntityReview());
            LocalDateTime timeStamp = LocalDateTime.now();
            reviewDTO.setReviewedOn(timeStamp);
            reviewDTOS.add(reviewDTO);
            entityDTO.setReviewDTOList(reviewDTOS);
            reviewRepository.save(entityDTO);

        }
        System.out.println(review);
        return review;
    }

    public Optional<EntityDTO> getReviewAspectScore(String entityName) {
        return reviewRepository.findById(entityName);
    }
}
