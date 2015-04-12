package test.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.TestService;

/**
 * MainAPP
 * 宣言的トランザクションの実装方法の学習を目的としたプロジェクト
 * 
 * @author yohei
 */
public class TestApp {

	/**
	 * メインエントリ
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TestService service = (TestService) context.getBean(TestService.class);
		service.txInsert();
	}
}
