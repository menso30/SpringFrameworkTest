package test.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.dao.TestDao;
import test.dto.TestDto;

@Service
public class TestService {
	@Resource
	private TestDao testDao;
	
	/**
	 * インサート処理
	 */
	public int txInsert(boolean doThrowException) {
		return this.execute(doThrowException);
	}
	
	/**
	 * インサート処理実行
	 */
	private int execute(boolean doThrowException) {
		int result = this.testDao.insertTest("name : " + new Date().getTime());
		
		if (doThrowException) {
			// ロールバック確認用
			throw new RuntimeException();
		}
		// インサート結果表示
		this.printResult();
		
		return result;
	}
	
	/**
	 * インサート結果表示
	 */
	private void printResult() {
		List<TestDto> list = this.testDao.getTest();
		if (!list.isEmpty()) {
			for (TestDto dto : list) {
				System.out.println("-----");
				System.out.println("ID : " + dto.getId());
				System.out.println("NAME : " + dto.getName());
				System.out.println("CREATE DATE : " + dto.getCreateDate());
			}
		}
		else {
			System.out.println("test data was emptied.");
		}
	}
}
