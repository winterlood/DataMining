import java.awt.List;
import java.util.ArrayList;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumCrawler selTest = new SeleniumCrawler();
		// getSearchResult 에 가수이름과 곡제목 넣으면
		// 가수 구글 검색 결과, 가수+곡 구글 검색 결과, 가수+곡 기사 결과 순으로 ArrayList로 반환함
	    ArrayList<String> testArray = selTest.getSearchResult("아이유", "love poem");
	     for (int i=0; i<testArray.size(); i++) {
	    	 System.out.println(testArray.get(i));
	     }
	}

}
