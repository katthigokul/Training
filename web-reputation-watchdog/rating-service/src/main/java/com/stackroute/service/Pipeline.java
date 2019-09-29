package com.stackroute.service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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
public class Pipeline {

    private static Properties properties;
    /**
     * propertiesName is setting values for NLP. ssplit is used for splitting the
     * sentence,pos will describe words as parts of speech, sentiment will do
     * sentiment analysis according to Stanford DB
     */
    private static String propertiesName = "tokenize,ssplit,pos,parse,sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
