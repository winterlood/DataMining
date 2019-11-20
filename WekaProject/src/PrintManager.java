import java.io.*;
import java.util.*;
import weka.classifiers.*;
import weka.classifiers.trees.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class PrintManager {
	public void printProgramStart() {
		System.out.println("########################################## ################### ##############################################");
		System.out.println("########################################## ################### ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("########################################## Weka 3.8 Classifier ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("##########################################       Made By       ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("##########################################      201521107      ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("##########################################        ����ȯ               ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("########################################## ################### ##############################################");
		System.out.println("########################################## ################### ##############################################");
	}
	

	public void printDataPathRequest() {
		System.out.println();
		System.out.println("����� Data File�� ��ġ�� �Է��Ͻÿ�.");
		System.out.print("> ");
	}
	
	public void printPathCheck(String inputPath) {
		System.out.println();
		System.out.println("����� Training Data Path : " + inputPath);
		System.out.println("������ ��ġ�մϱ�? [y|n]");
		System.out.print("> ");
	}
	
	public void printReInput() {
		System.out.println();
		System.out.println("�ٽ� �Է��� �ֽʽÿ�");
	}
	
	public void printLoadTrainingDataSet(String inputPath) {
		System.out.println();
		System.out.println("������ �ε� �մϴ�. ");
		System.out.println("��� : "+inputPath);
	}
	public void printLoadComplete() {
		System.out.println();
		System.out.println("�ε�Ϸ� ");
	}
	
	public void printClassifierRequest() {

		System.out.println("����� Classifier�� �����Ͻʽÿ�");
		System.out.println("  1. OneRule");
		System.out.println("  2. NaiveBayes");
		System.out.println("  3. J48");
		System.out.print("> ");
	}
	
	public void printException(Exception e) {
		System.out.println("�˼��մϴ� ���α׷��� Exception �� �߻��Ͽ����ϴ�.");
	}
	
	public void printLoadInstanceRequest() {
		System.out.println();
		System.out.println("�ε�� �ν��Ͻ��� Ȯ���Ͻðڽ��ϱ�? [y|n]");
		System.out.print("> ");
	}
	
	public void printPressAnyKey() {
		System.out.println("��� �Ͻ÷��� �ƹ�Ű�� �������� ");
	}
	
	public void printClassifierChk(String cl) {
		System.out.println();
		System.out.println("���� �Է��Ͻ� Classifier : " + cl);
		System.out.println("������ ��ġ�մϱ�? [y|n]");
		System.out.print("> ");
	}
	
	public void printLoadedInsatnce(Instances instances) {
		System.out.println();
		System.out.println(" Your Attributes Info ###############################################");
		for (int i = 0; i < instances.numAttributes(); i++) {
			System.out.println(instances.attribute(i));
		}
		System.out.println();

		System.out.println("This DataSet's Class ###############################################");
		System.out.println(instances.classAttribute());
		System.out.println();

		System.out.println("Instances ###############################################");
		System.out.println("Total (" + instances.numInstances() + ") instances ###################################");
		for (int i = 0; i < instances.numInstances(); i++) {
			System.out.println(instances.instance(i));
		}
	}
	
	public void printMainConsole() {
		System.out.println();
		System.out.println("Main Panel === ");
		System.out.println("   1. DataSet Change");
		System.out.println("   2. Classify");
		System.out.println("   3. Associate");
		System.out.println("   4. Quit");
		System.out.print("> ");
	}
	
	public void printDataSetSelectRequest() {
		System.out.println();
		System.out.println("Dataset Select Panel === ");
		System.out.println("   1. Weather_nominal [Default]");
		System.out.println("   2. Weather_numeric [Default]");
		System.out.println("   3. Contact_lens [Default]");
		System.out.println("   4. User Custom DataSet");
		System.out.print("> ");
	}
	
	public void printNextRequest() {
		System.out.println();
		System.out.println("Classify Panel === ");
		System.out.println("Classifier�� ���� �� 1,2�� ������ �ֽñ� �ٶ��ϴ�.");
		System.out.println("   1. Classifier Output Ȯ��");
		System.out.println("   2. New Instance Prediction");
		System.out.println("   3. Classifier ����/��ü");
		System.out.println("   4. Back");
		System.out.print("> ");
	}
	
	public void printAssociatePanel() {
		System.out.println();
		System.out.println("Associate Panel === ");
		System.out.println("Default Rule : [Apriori]");
		System.out.println("   1. Associator Output Ȯ��");
		System.out.println("   2. Back");
		System.out.print("> ");
	}
	
	public void printFoldNumRequset() {
		System.out.println();
		System.out.println("Fold ȸ���� �Է��Ͻʽÿ� ");
		System.out.print("> ");
	}
}
