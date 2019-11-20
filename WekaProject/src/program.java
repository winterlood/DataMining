import java.util.*;

public class program {
	
	// 
	// �� ���α׷��� ���縯 ���б� 2019 ������ ���̴� ���� ������ ���� �������� ���۵Ǿ����ϴ�.
	// ������ : ����ȯ
	// �й� : 201521107
	//
	
	// ���� ȯ�� == 
	// OS : Window 10
	// Tool : Eclipse
	// Language : JAVA
	// API : Weka 3-8 Jar
		
	
	// 
	// ���α׷� Class �з�
	// 1. Program.java : �� ���α׷��� Main Class�̴�. Program�� Main Flow�� �����Ѵ�.
	// 2. PrintManager.java : �� ���α׷��� ����� ��� �� Ŭ�������� ����Ѵ�.
	// 3. WekaController.java : �� ���α׷����� ����ϴ� Machine Learning Tool�� Weka�� API�� �ٷ�� Class�̴�.
	// 4. RuleManager.java : �� ���α׷����� WekaController�� Classify��, Associate�� ���� �� ��, ���Ǵ� Machine Learning Rules�� ��üȭ �� Class�̴�.
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
	
	
	
	// Program ������
	
	public static void main(String[] args) {
		
		// #########################
		// # 1. Call Program Start #
		// #########################
		// �� Program�� ���� ��, DataSet�� ������ �� �ִ� �������� �ش�.
		// �� ������������, Default�� ���α׷� ��ü ����Ǿ��ִ�
		// Weather_nominal.arff, 
		// Weather_numeric.arff,
		// Contatc_lens.arff
		// �� Ư���� ��� �Է� ���� ����� �� �ְ�,
		// ��ü ����� or Linuxȯ�濡���� �����θ� �Է��Ͽ�, 
		// Custom DataSet�� Load �� ���� �ִ�.
		// Program ��ü ���� DataSet�� ���α׷��� ��ġ�� ������ ./DataSet ���� �����ؾ� �Ѵ�.
		// �׷��� ������ Load�� �Ұ����ϴ�.
		printManager.printProgramStart();
		programStart();
	
		
		MainPanel();
		
	}
	
	public static void programStart() {
		// ####################
		// # 1. Program Start #
		// ####################
		// �� Program�� ���� ��, DataSet�� ������ �� �ִ� �������� �ش�.
		// �� ������������, Default�� ���α׷� ��ü ����Ǿ��ִ�
		// Weather_nominal.arff, 
		// Weather_numeric.arff,
		// Contatc_lens.arff
		// �� Ư���� ��� �Է� ���� ����� �� �ְ�,
		// ��ü ����� or Linuxȯ�濡���� �����θ� �Է��Ͽ�, 
		// Custom DataSet�� Load �� ���� �ִ�.
		// Program ��ü ���� DataSet�� ���α׷��� ��ġ�� ������ ./DataSet ���� �����ؾ� �Ѵ�.
		// �׷��� ������ Load�� �Ұ����ϴ�.
		while(true) {
			printManager.printDataSetSelectRequest();
			int inputNum = scanner.nextInt();
			scanner.nextLine();
			if(inputNum == 1) {
				// Default DataSet�� Weather.nominal.arff�� ���� �Ͽ��� ���� ����̴�.
				inputPath = ".\\DataSet\\weather.nominal.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// �� DataSet�� Load�� ���� ���� ���, ���� Step���� �����ϰ�,
					// ���� ���� ���, �ٽ� �Է��ϰ� �Ѵ�.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 2) {
				// Default DataSet�� Weather.numeric.arff�� ���� �Ͽ��� ���� ����̴�.
				inputPath = ".\\DataSet\\weather.numeric.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// �� DataSet�� Load�� ���� ���� ���, ���� Step���� �����ϰ�,
					// ���� ���� ���, �ٽ� �Է��ϰ� �Ѵ�.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 3) {
				// Default DataSet�� Contact-lenses.arff�� ���� �Ͽ��� ���� ����̴�.
				inputPath = ".\\DataSet\\contact-lenses.arff";
				printManager.printPathCheck(inputPath);
				inputChk = scanner.nextLine();
				if (inputChk.equals("y") || inputChk.equals("Y")) {
					// �� DataSet�� Load�� ���� ���� ���, ���� Step���� �����ϰ�,
					// ���� ���� ���, �ٽ� �Է��ϰ� �Ѵ�.
					if(wekaController.LoadTrainingDataSet(inputPath))break;
					else printManager.printReInput();
				} 
				else {
					printManager.printReInput();
				}	
			}
			else if(inputNum == 4) {
				// Custom DataSet�� ���� �Ͽ��� ���� ����̴�.
				DataSetSelect();
				break;
			}
			else {
				printManager.printReInput();
			}
		}
	}
	
	public static void DataSetSelect() {
		// �� Operation������, Custom Path DataSet�� Load�� �����Ѵ�.
		// String Type���� ����� Ȥ�� �����θ� �Է¹޾�, 
		// �ش� ����� DataSet�� �ε��Ѵ�.
		// �� programStart Operation�� �����ϰ�, DataSet�� Load�ϴ� �Ϳ� �����Ͽ��� ���, �ٽ� �Է��ϰ� �Ѵ�.
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
		// Weka ���α׷��� Classify Tab�� ������ ����� �� �� �ֵ��� �ǵ��Ͽ���.
		//
		// �������δ�
		//
		// 1. Classify OutPut�� Ȯ�� �� �� �ִ�.
		//		���� Classifier�� ���õ��� �ʾҴٸ� ���� �����ϰ� �Ѵ�.
		//
		// 2. New Instance �� ���� ���� Knowledge Base�� ������� ��, Recommendation�� ���� ���ִ�
		// 		������� �Է����� New Instance�� �Է¹޴´�.
		//		���� Classifier�� ���õ��� �ʾҴٸ� ���� �����ϰ� �Ѵ�.
		//
		// 3. Classifier�� ����/��ü �� ���ִ�.
		//		���� ������ Classifier�δ�, OneRule, Naive Bayes, J48 �� �ִ�.
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
		// Weka ���α׷��� Assocatie Tab�� ������ ����� �� �� �ֵ��� �ǵ��Ͽ���.
		//
		// �������δ�
		//
		// 1. Associator OutPut�� Ȯ�� �� �� �ִ�.
		//		���� Classifier�� ���õ��� �ʾҴٸ� ���� �����ϰ� �Ѵ�.
		//
		// 2. �ڷ� ���ư���.
		//
		while(true) {
			printManager.printAssociatePanel();
			wekaController.SelectApriori();
			int inputNum = scanner.nextInt();
			scanner.nextLine();
			if(inputNum == 1) {
				// associator output Ȯ��
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
		// ���α׷��� Main Console Panel�̴�.
		//
		// �������δ�
		//
		// 1. DataSet�� ��ü�Ѵ�.
		//
		// 2. Classify Tab�� �����Ѵ�.
		//
		// 3. Associate Tab�� �����Ѵ�.
		//
		// 4. ���� Step���� ���ư���.
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
		// Classifier Rule�� ������ �� �ִ� Operation �̴�.
		// 
		// �������δ�
		//
		// 1. OneRule�� �����Ѵ�.
		//
		// 2. Naive Bayes�� �����Ѵ�.
		//
		// 3. J48�� �����Ѵ�.
		//
		// 4. ���� Step���� ���ư���.
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
