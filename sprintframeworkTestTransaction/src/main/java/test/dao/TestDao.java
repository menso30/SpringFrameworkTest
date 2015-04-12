package test.dao;

import java.util.List;

import test.dto.TestDto;

/**
 * テストデータDAO
 * @author yohei
 */
public interface TestDao {
	
	public List<TestDto> getTest();
	
	public Integer insertTest(String name);
}
