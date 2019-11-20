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
		
		// �־��� String Type�� Path�� �����ϴ� DataSet�� Load�ϴ� Operation�̴�.
		
		// �߻� �� ���ִ� Exception���δ�, ���� ū ���ɼ�����, FileNotFoundException�� �ִµ�,
		
		// �� ���, �ٽ� �Է��ϵ��� ���´�.
		
		// Weka.jar�� ����ϴ� �κ��̹Ƿ� �ڼ��ϰ� �ٷ絵�� �ϰڴ�.
		
		try {
			this.dataSource = new DataSource(path);
			// DataSource�� ���� �Ķ���ͷ� �Ѿ�� path�� �����Ѵ�.
			
			printManager.printLoadTrainingDataSet(path);
			// path�� ������ arff format file read
			
			this.training_data = dataSource.getDataSet();
			// arff�� �о�, Instances�� ���·� �����.
			
			this.training_data.setClassIndex(training_data.numAttributes()-1);
			// �� DataSet�� Class �� ������ Attribute�� �����Ѵ�.
			
			printManager.printLoadComplete();
			// �� Step ���� ���ܰ� �߻����� �ʾҴٸ�, DataSet�� ���������� Load�� ���̴�.
			// ���������� Load�ߴٰ� ����ϵ��� �Ѵ�.
			
			printManager.printLoadInstanceRequest();
			String chk = scanner.nextLine();
			// ����ڿ���, �ε�� Instances�� Ȯ�� �ϰڴ��� ���θ� ���´�.
			
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
		
		// Classifier�� ���� �ϴ� ����� �����ϴ� Operation�̴�.
		
		// ������� �Է¿� ���� Classifier�� ���� Classifier�� �����Ѵ�.
		
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
		
		// �� ���α׷����� �����ϰ� �����ϴ� Associate Rule�� Apriori �̴�.
		
		// Assocaiate Tab�� �����ߴٸ� �ڵ����� Apriori�� ���� AssociateRule�� �����Ѵ�.
		
		this.apriori = this.ruleManager.getApriori(this.training_data);
		return true;
	}
	
	public boolean MakeNewInstance(){

		// New Data �� �Է¹޾�,
		
		// New Data �� ���� Recommendation �� ��ȯ�ϴ� Operation�̴�.
		
		System.out.println();
		System.out.println("########################################################################################");
		System.out.println("################ Making New Instance Now ###############################################");
		System.out.println("########################################################################################");

		
		// New Data�� ���� ������ �ʰ�, 
		// ���� Load�� Instance�� 0��° Instance�� Attribute Value���� �ٲ� Class Value�� Recommendation �޵��� �Ѵ�.
		
		newInstance = training_data.instance(0);
		Instances td = training_data;

		for (int i = 0; i < td.numAttributes()-1; i++) {
			// ���� DataSet�� Attribute�� ��-1 ��ŭ Loop�� �����ϸ�,
			// Class�� ������ ��� Attribute�� ����ڿ��� �����ϵ��� �Ѵ�.
			
			if(td.attribute(i).isNumeric()) {
				
				// i��° Attribute�� Numeric�� ����̴�.
				// maxN�� minN�� �ش� Numeric Attribute�� �ּҰ��� �ִ밪�̴�.
				// ����ڴ� �� ������ ���� �Է��ϰ� �Ѵ�.
				
				double maxN = td.attributeStats(i).numericStats.max;
				double minN = td.attributeStats(i).numericStats.min;
				while (true) {
					
					// ���� ���� ���� �Է��Ͽ��� ��, �ٽ� �Է��ϵ��� �Ѵ�.
					
					System.out.println();
					System.out.println("## Now Attribute is : " + td.attribute(i));
					System.out.printf("# Min : %d \n", (int) minN);
					System.out.printf("# Max : %d \n", (int) maxN);
					System.out.print("> ");
					double input = scanner.nextDouble();
					// User�� ������ Value�� �ش� Attribute Value�� Set�Ѵ�.
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
				
				// i��° Attribute�� Nominal �� ����̴�.
				
				// Nominal�� ���, ���� Attribute Value�� ������ Indexing �Ͽ�, ����ڷα�, ��ȣ�� �Է��Ͽ� ���� �� ���� 
				
				// �Է��ϵ��� ���´�.
				
				while (true) {
					
					// ���� ���� ���� �Է��Ͽ��� ��, �ٽ� �Է��ϵ��� �Ѵ�.
					
					System.out.println();
					System.out.println("## Now Attribute is : " + td.attribute(i));
					for (int j = 0; j < td.attribute(i).numValues(); j++) {
						
						// i ��° Attribute�� Value �� ������ŭ Loop�� ����,
						// �ش� Value�� �̸��� �ο��� Index Number�� ����ڿ��� �˷��ش�.
						
						System.out.printf("# %d :  %s \n", j, td.attribute(i).value(j));
					}
					System.out.print("> ");
					int inputInt = scanner.nextInt();
					scanner.nextLine();
					if (0 <= inputInt && inputInt <= td.attribute(i).numValues()) {
						double WekaIn = inputInt;
						// User�� ������ Value�� �ش� Attribute Value�� Set�Ѵ�.
						newInstance.setValue(i, WekaIn);
						break;
					}
					else {
						printManager.printReInput();
					}
				}
			}
		}
		
		// ������� �Է��� �ٽ� �ѹ� ���������� Ȯ�ν����ش�.
		
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
		
		// �� ���� ����ڰ� ���� �Է��� New Instance�� 
		
		// this.newInstance ��ü�� ����Ǿ������Ƿ�,
		
		// �� �ν��Ͻ��� New Data�� �Ͽ� Recommendation�� �޾Ƴ� ���̴�.
		
		try {
			System.out.println();
			double result = this.classifier.classifyInstance(this.newInstance);
			System.out.println("# Recommendation is  : " +
			this.newInstance.classAttribute().value((int)result));
			// Weka������ �⺻������ ��� Data�� Double������ �����Ѵٰ� �Ѵ�. (Reference�� �ǰ�)
			// �׷��Ƿ� Double Type���� New Data�� ���� Class �з� ����� ���޹޾�,
			// �ش� Double Type Number�� String���� ��� Value�� Indexing �ϴ��� ã�Ƴ���,
			// ����ڿ��Դ� String Type�� Class Value�� Recommendation �Ѵ�.
			
			System.out.println();
			double[] prediction = this.classifier.distributionForInstance(
					this.newInstance);
			// ����������, ���� ����, Double Type���� �޾� �ش�.
			
			System.out.println("## Probability of each class ###################");
			for(int i=0; i<prediction.length; i++) {
				
				// Class�� Value�� ������ ���� �� �����Ƿ�, Loop�� ���Ͽ�
				
				// �ش� Value�� ���ɼ��� �ۼ�Ʈ�� ��� ǥ�����ش�.
				
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
			// Associator Output �� ����ϴ� ����� ����Ѵ�.
			// ������ DataSet���� �н��Ͽ� ���� ����� ����Ѵ�.
			// �� ����� ���������� Associate Rule�� ���Ͽ� �߰��� Best Rules�� ������ ���Եȴ�.
			System.out.println(this.apriori);
		} catch (Exception e) {
			 
		}
		
	}
	
	public void getClassifierOutput() {
		
		// Classifier Output�� ����ϴ� ����� �Ѵ�.
		
		 try {
			while (true) {
				printManager.printFoldNumRequset();
				int numFold = scanner.nextInt();
				scanner.nextLine();
				// Fold Number�� �Է¹޴´�.
				
				if (2 <= numFold && numFold < this.training_data.numInstances()) {
					// Fold Number�� 2ȸ �̻�, Instance ���� �̸����� �Է¹޴´�.
					
					Evaluation eval = new Evaluation(this.training_data);
					eval.crossValidateModel(this.classifier, this.training_data, numFold, new Random(1));
					// �־��� DataSet�� ������ Classifier Rule��,  Fold Number ��ŭ CrossValidation�� �����Ѵ�.
					
					double accurancy = eval.pctCorrect();
					// ��Ȯ���� ��ȯ�޴´�.
										
					String strSummary = eval.toSummaryString();
					// Classifier Model�� Summary�� ��ȯ�޴´�.
					
					String cMatrix = eval.toMatrixString();
					// String Type�� Confusion Matrix�� ��ȯ �޴´�.

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
