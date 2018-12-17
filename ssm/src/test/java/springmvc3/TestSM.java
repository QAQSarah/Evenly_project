package springmvc3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.test.model.User;
import com.test.service.UserService;
/*
 * spring自带的测试方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
@Transactional
public class TestSM {
	@Autowired
	private UserService userService;
	private final static Logger logger = LoggerFactory.getLogger(TestSM.class);

	@Test
	public void test1() {
		User u = userService.selectByPrimaryKey(1);
		/*
		 * logger 打印
		 * JSON转换为JSON对象输出
		 */
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-DD HH:mm:ss"));
		
	}
	/**
	 * junit测试
	 */
	/*@Test
	public void test2() {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-mybatis.xml");
		context.start();
		UserService demoService = (UserService) context.getBean("demoService"); // 获取bean demoService:Service的value值
		User u=demoService.selectByPrimaryKey(1);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-DD HH:mm:ss"));
		context.close();
		
	}*/
}
