package com.stackroute.service;

import com.stackroute.domain.Review;
import com.stackroute.domain.ReviewRating;
import com.stackroute.dto.ReviewDTO;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
/**
 * @NoArgsConstructor is used to create No Argument Constructor using Lombok
 */
@NoArgsConstructor
/**
 * This service will be called to create Pipeline for NLP
 */
public class SentimentAnalyzer {
    static Properties properties;
    static StanfordCoreNLP pipeline;

    public void setPipeline() {
        properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit,parse,sentiment,pos,lemma,ner");
        pipeline = new StanfordCoreNLP(properties);
    }


    public SentimentReport getSentimentReport(String review1, String field, Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        List<ReviewDTO> list = new ArrayList<>();
        reviewDTO.setReviewTitle(review.getEntityTitle());
        Map<String, String> sentimentReportRating = review.getEntityReview();
        review.setReviewerScore(1000);
        double sentimentScore = 0;
        double reviewerScore = 0;
        double aspectScore = 0;
        aspectScore = Double.parseDouble(review.getEntityReview().get(field));
        SentimentReport sentimentReport = new SentimentReport();
        ReviewRating reviewRating = new ReviewRating();
        if (review1 != null && review1.length() > 0) {
            Annotation annotation = pipeline.process(review1);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)
            ) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
//                SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
//                String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                /*
                 * *Might be used for future functionality
                 */
//                reviewRating.setVeryPositive((double) Math.round(sm.get(4) * 100d));
//                reviewRating.setPositive((double) Math.round(sm.get(3) * 100d));
//                reviewRating.setNeutral((double) Math.round(sm.get(2) * 100d));
//                reviewRating.setNegative((double) Math.round(sm.get(1) * 100d));
//                reviewRating.setVeryNegative((double) Math.round(sm.get(0) * 100d));
//                reviewRating.setAvg((reviewRating.getVeryNegative() * 0 + reviewRating.getNegative() * 1 + reviewRating.getNeutral() * 2 + reviewRating.getPositive() * 3 + reviewRating.getVeryPositive() * 4) / 5);
//                sentimentReport.setSentimentType(sentimentType);
                reviewRating.setAspect(field);
                sentimentReport.setSentimentScore(RNNCoreAnnotations.getPredictedClass(tree));
                sentimentReport.setReviewRating(reviewRating);
                /*
                Giving sentimentScore according to the rules set
                 */
                switch ((int) sentimentReport.getSentimentScore()) {
                    case 0:
                        sentimentScore = -0.20;
                        break;
                    case 1:
                        sentimentScore = -0.10;
                        break;
                    case 2:
                        sentimentScore = 0;
                        break;
                    case 3:
                        sentimentScore = 0.10;
                        break;
                    case 4:
                        sentimentScore = 0.20;
                        break;
                }
                /*
                Giving reviewerScore according to the rules set
                 */
                if (review.getReviewerScore() < 250) {
                    reviewerScore = 1.00;
                    break;
                } else if (review.getReviewerScore() < 500) {
                    reviewerScore = 2.00;
                    break;
                } else if (review.getReviewerScore() < 750) {
                    reviewerScore = 3.00;
                } else {
                    reviewerScore = 4.00;
                }
            }
            sentimentReport.setSentimentScore((aspectScore + (aspectScore + (reviewerScore * sentimentScore))) / 2.00);
            sentimentReportRating.put(field, Double.toString(sentimentReport.getSentimentScore()));
//            review.setEntityReview(sentimentReportRating);
        }

//        reviewDTO.setReviewAspectScore((sentimentReportRating));
        sentimentReport.setReviewDTO(reviewDTO);
        return sentimentReport;
    }
}
