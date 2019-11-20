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
		System.out.println("##########################################        이정환               ##############################################");
		System.out.println("##########################################                     ##############################################");
		System.out.println("########################################## ################### ##############################################");
		System.out.println("########################################## ################### ##############################################");
	}
	

	public void printDataPathRequest() {
		System.out.println();
		System.out.println("당신의 Data File의 위치를 입력하시오.");
		System.out.print("> ");
	}
	
	public void printPathCheck(String inputPath) {
		System.out.println();
		System.out.println("당신의 Training Data Path : " + inputPath);
		System.out.println("정보가 일치합니까? [y|n]");
		System.out.print("> ");
	}
	
	public void printReInput() {
		System.out.println();
		System.out.println("다시 입력해 주십시오");
	}
	
	public void printLoadTrainingDataSet(String inputPath) {
		System.out.println();
		System.out.println("파일을 로드 합니다. ");
		System.out.println("경로 : "+inputPath);
	}
	public void printLoadComplete() {
		System.out.println();
		System.out.println("로드완료 ");
	}
	
	public void printClassifierRequest() {

		System.out.println("사용할 Classifier를 선택하십시오");
		System.out.println("  1. OneRule");
		System.out.println("  2. NaiveBayes");
		System.out.println("  3. J48");
		System.out.print("> ");
	}
	
	public void printException(Exception e) {
		System.out.println("죄송합니다 프로그램의 Exception 이 발생하였습니다.");
	}
	
	public void printLoadInstanceRequest() {
		System.out.println();
		System.out.println("로드된 인스턴스를 확인하시겠습니까? [y|n]");
		System.out.print("> ");
	}
	
	public void printPressAnyKey() {
		System.out.println("계속 하시려면 아무키나 누르세요 ");
	}
	
	public void printClassifierChk(String cl) {
		System.out.println();
		System.out.println("현재 입력하신 Classifier : " + cl);
		System.out.println("정보가 일치합니까? [y|n]");
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
		System.out.println("Classifier를 선택 후 1,2를 선택해 주시기 바랍니다.");
		System.out.println("   1. Classifier Output 확인");
		System.out.println("   2. New Instance Prediction");
		System.out.println("   3. Classifier 선택/교체");
		System.out.println("   4. Back");
		System.out.print("> ");
	}
	
	public void printAssociatePanel() {
		System.out.println();
		System.out.println("Associate Panel === ");
		System.out.println("Default Rule : [Apriori]");
		System.out.println("   1. Associator Output 확인");
		System.out.println("   2. Back");
		System.out.print("> ");
	}
	
	public void printFoldNumRequset() {
		System.out.println();
		System.out.println("Fold 회수를 입력하십시오 ");
		System.out.print("> ");
	}
}
