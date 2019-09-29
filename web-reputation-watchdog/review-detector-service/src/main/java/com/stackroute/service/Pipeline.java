package com.stackroute.service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Properties;

/*  To Setup Pipeline & Load Properties
    Pipeline service will be called to create Pipeline for NLP
 */

/*  @Service annotation indicates that this class is a service
    which holds business logic in the Service layer
 */
@Service

//@NoArgsConstructor annotation is used to create No Argument Constructor using Lombok
@NoArgsConstructor

public class Pipeline {

    private static Properties properties;

    /*
     * "propertiesName" is for setting values for NLP.
     * "Tokenization" is the process of splitting a string into a list of tokens.
     * "ssplit" is used for splitting the sentence
     * "pos" describes words as parts of speech
     * "lemma" is to return the base or dictionary form of a word
     */
    private static String propertiesName = "tokenize, ssplit, pos, lemma";

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
