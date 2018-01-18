package testUser;

import org.junit.Test;

import cn.tedu.cloudnote.entity.User;

public class TestCaseService extends TestBase {
	/**
	 * 测试用户名错误--預期
	 */
	@Test
	public void test1() {
		System.out.println(userService.login("demo1", "55587a910882016321201e6ebbc9f595"));
	}

	/**
	 * 测试密码错误--預期
	 */
	@Test
	public void test2() {
		userService.login("demo", "123");
	}

	/**
	 * 测试登录成功——预期
	 */
	@Test
	public void test3() {
		System.out.println(userService.login("demo", "123456"));
	}

	/**
	 * 测试注册成功
	 */
	@Test
	public void test4() {
		userService.regist("Rose", "123456", null);
	}

	/**
	 * 测试注册用户名存在
	 */
	@Test
	public void test5() {
		userService.regist("Rose", "123", "123");
	}

	@Test
	public void test6() {
		User user = userService.changePass("94d42a62-fdbb-4e8d-94a7-bba33f3860bb", "654321", "123456");
		System.out.println(user);
	}
}
