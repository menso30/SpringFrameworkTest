package test.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import test.dto.TestDto;

@Repository
public class TestDaoMyBatis extends SqlSessionDaoSupport implements TestDao {
	
	/**
	 * データ取得
	 */
	public List<TestDto> getTest() {
		return this.getSqlSession().selectList("getTest");
	}
	
	/**
	 * データ格納
	 */
	public Integer insertTest(String name) {
		return this.getSqlSession().insert("insertTest", name);
	}
}
