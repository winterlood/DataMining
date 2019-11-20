import java.io.*;
import java.util.*;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.classifiers.*;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.*;
import weka.core.Instances;
import weka.classifiers.bayes.*;
public class RuleManager {

	Classifier OneRule;
	Classifier NaiveBayes;
	Classifier j48;
	Apriori apiori;
	
	public RuleManager() {
		this.OneRule = new OneR();
		this.NaiveBayes = new NaiveBayes();
		this.j48 = new J48();
		this.apiori = new Apriori();
	}
	public Apriori getApriori(Instances training_data) {
		try {
			this.apiori.buildAssociations(training_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.apiori;
	}
	public Classifier getOneRule(Instances training_data) {
		try {
			this.OneRule.buildClassifier(training_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.OneRule;
	}
	public Classifier getNaiveBayes(Instances training_data) {
		try {
			this.NaiveBayes.buildClassifier(training_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.NaiveBayes;
	}
	public Classifier getJ48(Instances training_data) {
		try {
			this.j48.buildClassifier(training_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.j48;
	}
}
