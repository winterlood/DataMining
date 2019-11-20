import java.io.*;
import java.util.*;
import weka.associations.Apriori;
import weka.associations.FPGrowth;
import weka.classifiers.*;
import weka.classifiers.trees.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaController {
	RuleManager ruleManager = new RuleManager();
	PrintManager printManager = new PrintManager();
	Scanner scanner = new Scanner(System.in);
	DataSource dataSource;
	private Instances training_data;
	private Instances testing_data;
	private Classifier classifier; 
	private Apriori apriori;
	private Instance newInstance;
	private int Cidx;
	
	public boolean LoadTrainingDataSet(String path) {
		
		// 주어진 String Type의 Path에 존재하는 DataSet을 Load하는 Operation이다.
		
		// 발생 할 수있는 Exception으로는, 가장 큰 가능성으로, FileNotFoundException이 있는데,
		
		// 이 경우, 다시 입력하도록 돕는다.
		
		// Weka.jar를 사용하는 부분이므로 자세하게 다루도록 하겠다.
		
		try {
			this.dataSource = new DataSource(path);
			// DataSource로 현재 파라미터로 넘어온 path를 설정한다.
			
			printManager.printLoadTrainingDataSet(path);
			// path에 설정된 arff format file read
			
			this.training_data = dataSource.getDataSet();
			// arff를 읽어, Instances의 형태로 만든다.
			
			this.training_data.setClassIndex(training_data.numAttributes()-1);
			// 본 DataSet의 Class 를 마지막 Attribute로 설정한다.
			
			printManager.printLoadComplete();
			// 이 Step 까지 예외가 발생하지 않았다면, DataSet을 성공적으로 Load한 것이다.
			// 성공적으로 Load했다고 출력하도록 한다.
			
			printManager.printLoadInstanceRequest();
			String chk = scanner.nextLine();
			// 사용자에게, 로드된 Instances를 확인 하겠는지 여부를 묻는다.
			
			if(chk.equals("y") || chk.equals("Y")) {
				printManager.printLoadedInsatnce(training_data);
				printManager.printPressAnyKey();
				String tmp = scanner.nextLine();
			}
			
			return true;
		} catch (FileNotFoundException e) {
			 
			printManager.printException(e);
			return false;
		} catch (IOException e) {
			 
			printManager.printException(e);
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 
			printManager.printException(e);
			return false;
		}
	}
	
	
	public boolean SelectClassifier(int cidx) {
		
		// Classifier를 선택 하는 기능을 수행하는 Operation이다.
		
		// 사용자의 입력에 따른 Classifier를 현재 Classifier로 설정한다.
		
		if(cidx == 1) {
			// oneRule
			this.classifier = ruleManager.getOneRule(this.training_data);
		}
		else if(cidx == 2) {
			// Naive Bayes
			this.classifier = ruleManager.getNaiveBayes(this.training_data);
		}
		else if(cidx == 3) {
			// J48
			this.classifier = ruleManager.getJ48(this.training_data);
		}
		this.Cidx = cidx;
		return true;
	}
	
	public boolean SelectApriori() {
		
		// 본 프로그램에서 유일하게 지원하는 Associate Rule은 Apriori 이다.
		
		// Assocaiate Tab을 선택했다면 자동으로 Apriori를 현재 AssociateRule로 설정한다.
		
		this.apriori = this.ruleManager.getApriori(this.training_data);
		return true;
	}
	
	public boolean MakeNewInstance(){

		// New Data 를 입력받아,
		
		// New Data 에 대한 Recommendation 을 반환하는 Operation이다.
		
		System.out.println();
		System.out.println("########################################################################################");
		System.out.println("################ Making New Instance Now ###############################################");
		System.out.println("########################################################################################");

		
		// New Data를 새로 만들지 않고, 
		// 현재 Load된 Instance의 0번째 Instance의 Attribute Value들을 바꿔 Class Value를 Recommendation 받도록 한다.
		
		newInstance = training_data.instance(0);
		Instances td = training_data;

		for (int i = 0; i < td.numAttributes()-1; i++) {
			// 현재 DataSet의 Attribute의 수-1 만큼 Loop를 수행하며,
			// Class를 제외한 모든 Attribute를 사용자에게 선택하도록 한다.
			
			if(td.attribute(i).isNumeric()) {
				
				// i번째 Attribute가 Numeric일 경우이다.
				// maxN과 minN은 해당 Numeric Attribute의 최소값과 최대값이다.
				// 사용자는 이 사이의 값을 입력하게 한다.
				
				double maxN = td.attributeStats(i).numericStats.max;
				double minN = td.attributeStats(i).numericStats.min;
				while (true) {
					
					// 범위 밖의 값을 입력하였을 때, 다시 입력하도록 한다.
					
					System.out.println();
					System.out.println("## Now Attribute is : " + td.attribute(i));
					System.out.printf("# Min : %d \n", (int) minN);
					System.out.printf("# Max : %d \n", (int) maxN);
					System.out.print("> ");
					double input = scanner.nextDouble();
					// User가 선택한 Value를 해당 Attribute Value로 Set한다.
					newInstance.setValue(i, input);
					if(minN <= input && input <= maxN) {
						break;
					}
					else {
						printManager.printReInput();
					}
				}
			}
			else {
				
				// i번째 Attribute가 Nominal 일 경우이다.
				
				// Nominal일 경우, 현재 Attribute Value의 종류를 Indexing 하여, 사용자로금, 번호를 입력하여 조금 더 쉽게 
				
				// 입력하도록 돕는다.
				
				while (true) {
					
					// 범위 밖의 값을 입력하였을 때, 다시 입력하도록 한다.
					
					System.out.println();
					System.out.println("## Now Attribute is : " + td.attribute(i));
					for (int j = 0; j < td.attribute(i).numValues(); j++) {
						
						// i 번째 Attribute의 Value 의 개수만큼 Loop를 돌며,
						// 해당 Value의 이름과 부여된 Index Number를 사용자에게 알려준다.
						
						System.out.printf("# %d :  %s \n", j, td.attribute(i).value(j));
					}
					System.out.print("> ");
					int inputInt = scanner.nextInt();
					scanner.nextLine();
					if (0 <= inputInt && inputInt <= td.attribute(i).numValues()) {
						double WekaIn = inputInt;
						// User가 선택한 Value를 해당 Attribute Value로 Set한다.
						newInstance.setValue(i, WekaIn);
						break;
					}
					else {
						printManager.printReInput();
					}
				}
			}
		}
		
		// 사용자의 입력을 다시 한번 종합적으로 확인시켜준다.
		
		System.out.println();
		System.out.println("## Your Input is #################");
		for (int i = 0; i < newInstance.numAttributes()-1; i++) {
			if(td.attribute(i).isNumeric()) {
				System.out.print("# ");
				System.out.println(newInstance.value(i));
			}
			else {
				System.out.print("# ");
				System.out.println(newInstance.stringValue(i));
			}
		}
		return true;
	}
	
	
	public boolean getClassifyResult() {
		
		// 위 에서 사용자가 직접 입력한 New Instance는 
		
		// this.newInstance 객체에 저장되어있으므로,
		
		// 이 인스턴스를 New Data로 하여 Recommendation을 받아낼 것이다.
		
		try {
			System.out.println();
			double result = this.classifier.classifyInstance(this.newInstance);
			System.out.println("# Recommendation is  : " +
			this.newInstance.classAttribute().value((int)result));
			// Weka에서는 기본적으로 모든 Data를 Double형으로 관리한다고 한다. (Reference에 의거)
			// 그러므로 Double Type으로 New Data에 대한 Class 분류 결과를 전달받아,
			// 해당 Double Type Number가 String으로 어떠한 Value를 Indexing 하는지 찾아내어,
			// 사용자에게는 String Type의 Class Value를 Recommendation 한다.
			
			System.out.println();
			double[] prediction = this.classifier.distributionForInstance(
					this.newInstance);
			// 마찬가지로, 예측 율도, Double Type으로 받아 준다.
			
			System.out.println("## Probability of each class ###################");
			for(int i=0; i<prediction.length; i++) {
				
				// Class의 Value가 여러개 있을 수 있으므로, Loop를 통하여
				
				// 해당 Value의 가능성의 퍼센트를 모두 표시해준다.
				
				System.out.print("# " +this.training_data.classAttribute().value(i));
				System.out.println(" : " + prediction[i]);
			}
		} catch (Exception e) {
			 
			printManager.printException(e);
		}
		return true;
	}
	
	public void getAssociatorOutPut() {
		try {
			// Associator Output 을 출력하는 기능을 담당한다.
			// 설정된 DataSet으로 학습하여 얻은 결과를 출력한다.
			// 이 출력의 마지막에는 Associate Rule을 통하여 발견한 Best Rules의 정보도 포함된다.
			System.out.println(this.apriori);
		} catch (Exception e) {
			 
		}
		
	}
	
	public void getClassifierOutput() {
		
		// Classifier Output을 출력하는 기능을 한다.
		
		 try {
			while (true) {
				printManager.printFoldNumRequset();
				int numFold = scanner.nextInt();
				scanner.nextLine();
				// Fold Number를 입력받는다.
				
				if (2 <= numFold && numFold < this.training_data.numInstances()) {
					// Fold Number는 2회 이상, Instance 개수 미만으로 입력받는다.
					
					Evaluation eval = new Evaluation(this.training_data);
					eval.crossValidateModel(this.classifier, this.training_data, numFold, new Random(1));
					// 주어진 DataSet를 설정한 Classifier Rule로,  Fold Number 만큼 CrossValidation을 수행한다.
					
					double accurancy = eval.pctCorrect();
					// 정확도를 반환받는다.
										
					String strSummary = eval.toSummaryString();
					// Classifier Model의 Summary를 반환받는다.
					
					String cMatrix = eval.toMatrixString();
					// String Type의 Confusion Matrix를 반환 받는다.

					String model = eval.toClassDetailsString();
					System.out.println("=================== [Classifier Output] ===========================================================================================================================================================================");
					System.out.println("=== Classifier Model ===");
					System.out.println();
					System.out.println(this.classifier);
					System.out.println();
					System.out.println("=== Summary ====");
					System.out.println();
					System.out.println(strSummary);
					System.out.println();
					System.out.println("=== Accurancy ===");
					System.out.println();
					System.out.println(accurancy);
					System.out.println();
					System.out.println(model);
					System.out.println();
					System.out.println(cMatrix);
					System.out.println();
					System.out.println("==============================================================================================================================================================================================");

					break;
				}
				else {
					printManager.printReInput();
					System.out.print("> ");
					numFold = scanner.nextInt();
					scanner.nextLine();
				}
			}
			
		} catch (Exception e) {
			 
			printManager.printException(e);
		}
	}
}
