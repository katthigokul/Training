package com.stackroute.service;

import com.stackroute.domain.AnalyzedReview;
import com.stackroute.dto.ReviewDto;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReviewDetectorServiceImpl implements ReviewDetectorService {
    private AnalyzedReview analyzedReview;

    /*    To get the review details from orchestration-service and to send it back with a tag
          genuine-true/false after analysis
    */
    @Override
    public AnalyzedReview getReviewAndSendAnalyzedReview(ReviewDto reviewDto) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

//        Initialization
        analyzedReview = new AnalyzedReview();

//        getting the review description for operation
        CoreDocument reviewDescription = new CoreDocument(reviewDto.getReviewDescription());
        stanfordCoreNLP.annotate(reviewDescription);

//        review description is tokenized i.e. spilt in to list of words
        List<CoreLabel> wordList = reviewDescription.tokens();

//        checking the number of words in the list
        int numberOfWords = wordList.size();

        /* Filtering the review description according to the number of words present.
         *If number of words in the review description is less than 3 or more than 100
         * then the review is fake.
         * If not then it will go into next loop for further verification
         */
        if (numberOfWords >= 3 && numberOfWords <= 100) {
            List<String> adjectiveAndAdverbList = new ArrayList<>();
            for (CoreLabel coreLabel : wordList) {
                String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                /* Checking if the word is an adjective or adverb
                 * "JJ" represents adjective.  "JJR" represents  Adjective, comparative
                 *"JJS" represents Adjective, superlative.  "RB" represents adverb
                 * "RBR" represents Adverb, comparative  "RBS" represents Adverb, superlative
                 */
                if (pos.equalsIgnoreCase("JJ") || pos.equalsIgnoreCase("JJR")
                        || pos.equalsIgnoreCase("JJS") || pos.equalsIgnoreCase("RB")
                        || pos.equalsIgnoreCase("RBR") || pos.equalsIgnoreCase("RBS")) {

                    /* Assigning the adjectives and adverbs to a variable called "adjectivesOrAdverbs"
                        and the adding those to a list named adjectiveAndAdverbList
                     */
                    String adjectivesOrAdverbs = coreLabel.originalText().toLowerCase();
                    adjectiveAndAdverbList.add(adjectivesOrAdverbs);
                }
            }

//            To find the number of adjectives and adverbs in the list
            int numberOfAdjectivesAndAdverbs = adjectiveAndAdverbList.size();

            /*  If the number of occurrence of adjectives and/or adverbs in review description
                is more than 6 then it is tagged as fake,
                otherwise it will go to the next loop for further verification
             */
            if (numberOfAdjectivesAndAdverbs <= 6) {
                List<String> nounList = new ArrayList<>();
                for (CoreLabel coreLabel : wordList) {

//                To get the part of speech of each word
                    String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                    /* Checking if the word is a noun or not
                     * "NN" represents Noun-Singular/Mass , "NNP" represents Proper-Noun-Singular
                     *"NNS" represents Noun-plural , "NNPS" represents Proper-Noun-Plural
                     */
                    if (pos.equalsIgnoreCase("NN") || pos.equalsIgnoreCase("NNP") ||
                            pos.equalsIgnoreCase("NNS") || pos.equalsIgnoreCase("NNPS")) {

                        /*  Assigning the nouns to a variable named "nouns" after converting it
                            into lowercase and then adding it to a array list named "nounList"
                        */
                        String nouns = coreLabel.originalText().toLowerCase();
                        nounList.add(nouns);

                        /*  Adding the elements of noun list to hash set named "setOfNouns"
                            to calculate the frequency of occurrence of each noun
                        */
                        Set<String> setOfNouns = new HashSet<String>(nounList);
                        for (String key : setOfNouns) {
                            int numberOfOccurrence = (Collections.frequency(nounList, key));

                            /*  If the number of occurrence of a noun is more than 3
                                then it is tagged as fake, Otherwise it will go out of this loop
                            */
                            if (numberOfOccurrence > 3) {
                                analyzedReview.setGenuine(false);
                                analyzedReview.setEntityTitle(reviewDto.getEntityTitle());
                                analyzedReview.setEntityReview(reviewDto.getEntityReview());
                                analyzedReview.setReviewTitle(reviewDto.getReviewTitle());
                                analyzedReview.setReviewDescription(reviewDto.getReviewDescription());
                                analyzedReview.setReviewedBy(reviewDto.getReviewedBy());
                                analyzedReview.setReviewerScore(reviewDto.getReviewerScore());
                                return analyzedReview;
                            } else
                                analyzedReview.setGenuine(true);
                            analyzedReview.setEntityTitle(reviewDto.getEntityTitle());
                            analyzedReview.setEntityReview(reviewDto.getEntityReview());
                            analyzedReview.setReviewTitle(reviewDto.getReviewTitle());
                            analyzedReview.setReviewDescription(reviewDto.getReviewDescription());
                            analyzedReview.setReviewedBy(reviewDto.getReviewedBy());
                            analyzedReview.setReviewerScore(reviewDto.getReviewerScore());
                        }
                    }
                }
            }
        } else
            analyzedReview.setGenuine(false);
        analyzedReview.setEntityTitle(reviewDto.getEntityTitle());
        analyzedReview.setEntityReview(reviewDto.getEntityReview());
        analyzedReview.setReviewTitle(reviewDto.getReviewTitle());
        analyzedReview.setReviewDescription(reviewDto.getReviewDescription());
        analyzedReview.setReviewedBy(reviewDto.getReviewedBy());
        analyzedReview.setReviewerScore(reviewDto.getReviewerScore());
        return analyzedReview;
    }
}
