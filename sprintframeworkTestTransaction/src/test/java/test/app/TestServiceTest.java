package test.app;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.dao.TestDao;
import test.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class TestServiceTest extends DataSourceBasedDBTestCase  {
	
	@Resource
	private DataSource dataSource;
	
	@Resource
	private TestService testSevice;
	
	@Resource
	private TestDao testDao;
	
	@Before
	public void setUp() throws Exception {
		DatabaseOperation.TRUNCATE_TABLE.execute(this.getConnection(), this.getConnection().createDataSet());
	}
	/**
	 * 正常処理
	 */
	@Test
	public void txInsertNormal() {
		int beforeSize = this.getDataCount();
		
		int succeed = this.testSevice.txInsert(false);
		
		log(String.format("befroreSize : %d / afterSize : %d", beforeSize, this.getDataCount()));
		assertEquals(beforeSize + 1, beforeSize + succeed);
	}
	
	/**
	 * ロールバック確認
	 */
	@Test
	public void txInsertThrowException() {
		int fix = this.getDataCount();
		
		try {
			this.testSevice.txInsert(true);
		}
		catch (Exception e) {
		}
		
		log(String.format("befroreSize : %d / afterSize : %d", fix, this.getDataCount()));
		assertEquals(fix, this.getDataCount());
	}
	
	/**
	 * データ数取得
	 * @return
	 */
	private int getDataCount() {
		return this.testDao.getTest().size();
	}
	
	private void log(Object args) {
		System.out.println(args);
	}
	@Override
	protected DataSource getDataSource() {
		return this.dataSource;
	}
	@Override
	protected IDataSet getDataSet() throws Exception {
		return this.getConnection().createDataSet();
	}
}
