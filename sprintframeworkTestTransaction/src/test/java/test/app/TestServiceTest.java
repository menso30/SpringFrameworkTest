package test.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.dao.TestDao;
import test.service.TestService;

public class TestServiceTest {
	
	private TestService testSevice;
	
	private TestDao testDao;
	
	@SuppressWarnings("resource")
	@Before
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.testSevice = (TestService) context.getBean(TestService.class);
		this.testDao = (TestDao) context.getBean(TestDao.class);
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
}
