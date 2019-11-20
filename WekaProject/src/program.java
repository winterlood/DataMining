import java.util.*;

public class program {
	
	// 
	// 본 프로그램은 가톨릭 대학교 2019 데이터 마이닝 과제 제출을 위한 목적으로 제작되었습니다.
	// 제작자 : 이정환
	// 학번 : 201521107
	//
	
	// 개발 환경 == 
	// OS : Window 10
	// Tool : Eclipse
	// Language : JAVA
	// API : Weka 3-8 Jar
		
	
	// 
	// 프로그램 Class 분류
	// 1. Program.java : 본 프로그램의 Main Class이다. Program의 Main Flow를 제어한다.
	// 2. PrintManager.java : 본 프로그램의 출력을 모두 이 클래스에서 담당한다.
	// 3. WekaController.java : 본 프로그램에서 사용하는 Machine Learning Tool인 Weka의 API를 다루는 Class이다.
	// 4. RuleManager.java : 본 프로그램에서 WekaController가 Classify나, Associate를 수행 할 때, 사용되는 Machine Learning Rules를 객체화 한 Class이다.
	//
	
	public static PrintManager printManager = new PrintManager();
	public static WekaController wekaController = new WekaController();
	public static Scanner scanner = new Scanner(System.in);
	public static String weather_training_data_path;
	public static String lens_training_data_path;
	public static String inputPath;
	public static int inputCidx;
	public static String inputChk;
	public static boolean isClassfierset = false;
	
	
	
	// Program 진입점
	
	public static void main(String[] args) {
		
		// #########################
		// # 1. Call Program Start #
		// #########################
		// 본 Program은 시작 시, DataSet을 선택할 수 있는 선택지를 준다.
		// 위 선택지에서는, Default로 프로그램 자체 내장되어있는
		// Weather_nominal.arff, 
		// Weather_numeric.arff,
		// Contatc_lens.arff
		// 를 특별한 경로 입력 없이 사용할 수 있고,
		// 자체 상대경로 or Linux환경에서는 절대경로를 입력하여, 
		// Custom DataSet을 Load 할 수도 있다.
		// Program 자체 내장 DataSet은 프로그램이 설치된 폴더의 ./DataSet 내에 존재해야 한다.
		// 그렇지 않으면 Load가 불가능하다.
		printManager.printProgramStart();
		programStart();
	
		
		MainPanel();
		
	}
	
	public static void programStart() {
		// ####################
		// # 1. Program Start #
		// ####################
		// 본 Program은 시작 시, DataSet을 선택할 수 있는 선택지를 준다.
		// 위 선택지에서는, Default로 프로그램 자체 내장되어있는
		// Weather_nominal.arff, 
		// Weather_numeric.arff,
		// Contatc_lens.arff
		// 를 특별한 경로 입력 없이 사용할 수 있고,
		// 자체 상대경로 or Linux환경에서는 절대경로를 입력하여, 
		// Custom DataSet을 Load 할 수도 있다.
		// Program 자체 내장 DataSet은 프로그램이 설치된 폴더의 ./DataSet 내에 존재해야 한다.
		// 그렇지 않으면 Load가 불가능하다.
		while(true) {
			printManager.printDataSetSelectRequest();
			int inputNum = scanner.nextInt();
			scanner.nextLine();
			if(inputNum == 1) {
				// Default DataSet인 Weather.nominal.arff를 선택 하였을 때의 경우이다.
				inputPath = ".\\DataSet\\weather.nominal.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// 본 DataSet의 Load가 성공 했을 경우, 다음 Step으로 진입하고,
					// 실패 했을 경우, 다시 입력하게 한다.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 2) {
				// Default DataSet인 Weather.numeric.arff를 선택 하였을 때의 경우이다.
				inputPath = ".\\DataSet\\weather.numeric.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// 본 DataSet의 Load가 성공 했을 경우, 다음 Step으로 진입하고,
					// 실패 했을 경우, 다시 입력하게 한다.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 3) {
				// Default DataSet인 Contact-lenses.arff를 선택 하였을 때의 경우이다.
				inputPath = ".\\DataSet\\contact-lenses.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// 본 DataSet의 Load가 성공 했을 경우, 다음 Step으로 진입하고,
					// 실패 했을 경우, 다시 입력하게 한다.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 4) {
				// Custom DataSet을 선택 하였을 때의 경우이다.
				DataSetSelect();
				break;
			}
			else {
				printManager.printReInput();
			}
		}
	}
	
	public static void DataSetSelect() {
		// 본 Operation에서는, Custom Path DataSet의 Load를 수행한다.
		// String Type으로 상대경로 혹은 절대경로를 입력받아, 
		// 해당 경로의 DataSet을 로드한다.
		// 위 programStart Operation과 동일하게, DataSet을 Load하는 것에 실패하였을 경우, 다시 입력하게 한다.
		while (true) {
			printManager.printDataPathRequest();
			inputPath = scanner.nextLine();
			printManager.printPathCheck(inputPath);
			inputChk = scanner.nextLine();
			if (inputChk.equals("y") || inputChk.equals("Y")) {
				if(wekaController.LoadTrainingDataSet(inputPath))break;
				else printManager.printReInput();
			} 
			else {
				printManager.printReInput();
			}
		}
	}
	
	public static void ClassifyPanel() {
		// Weka 프로그램의 Classify Tab과 유사한 기능을 할 수 있도록 의도하였다.
		//
		// 선택지로는
		//
		// 1. Classify OutPut을 확인 할 수 있다.
		//		만약 Classifier가 선택되지 않았다면 먼저 선택하게 한다.
		//
		// 2. New Instance 에 대해 현재 Knowledge Base를 기반으로 한, Recommendation을 받을 수있다
		// 		사용자의 입력으로 New Instance를 입력받는다.
		//		만약 Classifier가 선택되지 않았다면 먼저 선택하게 한다.
		//
		// 3. Classifier를 선택/교체 할 수있다.
		//		선택 가능한 Classifier로는, OneRule, Naive Bayes, J48 이 있다.
		//
		while (true) {
			printManager.printNextRequest();
			inputCidx = scanner.nextInt();
			if (inputCidx == 1) {
				if (isClassfierset) {
					wekaController.getClassifierOutput();
				}
				else {
					setClassifier();
					wekaController.getClassifierOutput();
				}
			}
			else if (inputCidx == 2) {
				if(isClassfierset) {
				wekaController.MakeNewInstance();
				wekaController.getClassifyResult();
				}
				else {
					setClassifier();
					wekaController.MakeNewInstance();
					wekaController.getClassifyResult();
				}
			}
			else if (inputCidx == 3)
				setClassifier();
			else if (inputCidx == 4)
				break;
			else 
				printManager.printReInput();
		}
	}
	
	public static void AssociatePanel() {
		//
		// Weka 프로그램의 Assocatie Tab과 유사한 기능을 할 수 있도록 의도하였다.
		//
		// 선택지로는
		//
		// 1. Associator OutPut을 확인 할 수 있다.
		//		만약 Classifier가 선택되지 않았다면 먼저 선택하게 한다.
		//
		// 2. 뒤로 돌아간다.
		//
		while(true) {
			printManager.printAssociatePanel();
			wekaController.SelectApriori();
			int inputNum = scanner.nextInt();
			scanner.nextLine();
			if(inputNum == 1) {
				// associator output 확인
				wekaController.getAssociatorOutPut();
			}
			else if(inputNum == 2) {
				break;
			}
			else {
				printManager.printReInput();
			}
		}
	}
	
	public static void MainPanel() {
		//
		// 프로그램의 Main Console Panel이다.
		//
		// 선택지로는
		//
		// 1. DataSet을 교체한다.
		//
		// 2. Classify Tab을 선택한다.
		//
		// 3. Associate Tab을 선택한다.
		//
		// 4. 이전 Step으로 돌아간다.
		//
		while(true) {
			printManager.printMainConsole();
			int inputNum = scanner.nextInt();
			scanner.nextLine();
			if(inputNum == 1) {
				programStart();
			}
			else if(inputNum == 2) {
				ClassifyPanel();
			}
			else if(inputNum == 3) {
				AssociatePanel();
			}
			else if(inputNum == 4) {
				break;
			}
			else {
				printManager.printReInput();
			}
		}
	}
	
	public static void setClassifier() {
		//
		// Classifier Rule을 결정할 수 있는 Operation 이다.
		// 
		// 선택지로는
		//
		// 1. OneRule을 선택한다.
		//
		// 2. Naive Bayes을 선택한다.
		//
		// 3. J48을 선택한다.
		//
		// 4. 이전 Step으로 돌아간다.
		//
		while (true) {
			printManager.printClassifierRequest();
			inputCidx = scanner.nextInt();
			if (inputCidx == 1)
				printManager.printClassifierChk("OneRule");
			else if (inputCidx == 2)
				printManager.printClassifierChk("Naive Bayes");
			else if (inputCidx == 3)
				printManager.printClassifierChk("J48");
			else if(inputCidx == 4){
				break;
			}
			else {
				printManager.printReInput();
				continue;
			}

			scanner.nextLine();
			String inputChk2 = scanner.nextLine();
			if (inputChk2.equals("y") || inputChk2.equals("Y")) {
				isClassfierset = true;
				break;
			} 
			else {
				printManager.printReInput();
			}
		}
		wekaController.SelectClassifier(inputCidx);
	}
}
