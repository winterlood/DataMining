import java.awt.List;
import java.util.ArrayList;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumCrawler selTest = new SeleniumCrawler();
		// getSearchResult �� �����̸��� ������ ������
		// ���� ���� �˻� ���, ����+�� ���� �˻� ���, ����+�� ��� ��� ������ ArrayList�� ��ȯ��
	    ArrayList<String> testArray = selTest.getSearchResult("������", "love poem");
	     for (int i=0; i<testArray.size(); i++) {
	    	 System.out.println(testArray.get(i));
	     }
	}

}
