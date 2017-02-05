package com.nlp.stanfordnlp;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class Annotate {
	
	Properties props = null;
	StanfordCoreNLP pipeline = null;
	public Annotate(){
		props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		
		pipeline = new StanfordCoreNLP(props);
	}
	
	@SuppressWarnings("deprecation")
	public void generateAnnotators(String content){
		@SuppressWarnings("deprecation")
		Annotation document = new Annotation(content);
		pipeline.annotate(document);
		
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		
		for(CoreMap sentence: sentences){
			
			
			for(CoreLabel token: sentence.get(TokensAnnotation.class)){
				
				String word = token.get(TextAnnotation.class);
				
				String pos = token.get(PartOfSpeechAnnotation.class);
				
				String ne = token.get(NamedEntityTagAnnotation.class);
								
			}
			
			
			Tree tree = sentence.get(TreeAnnotation.class);
			
			SemanticGraph dependencies = sentence.get(
					CollapsedCCProcessedDependenciesAnnotation.class);
			
			
		}
		
		Map<Integer, CorefChain> graph = document.get(CorefChainAnnotation.class);
		
	}

}
