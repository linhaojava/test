package testJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
	@Test
	public void test1() throws JsonProcessingException {
		int[] ary = { 1, 2, 3 };
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(ary);
		System.out.println(json);
	}

	@Test
	public void test2() throws JsonProcessingException {
		List<String> list = new ArrayList<String>();
		list.add("佳木斯");
		list.add("廊坊");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
	}

	@Test
	public void test3() throws JsonProcessingException {
		Foo foo = new Foo(1, "Rose");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(foo);
		System.out.println(json);
		// mapper.readValues(json, foo);
	}

	@Test
	public void test4() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 5);
		map.put("name", "Rose");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		System.out.println(json);
	}

	@Test
	public void test5() throws JsonProcessingException {
		List<Foo> list = new ArrayList<Foo>();
		list.add(new Foo(2, "Tom"));
		list.add(new Foo(3, "Andy"));
		list.add(new Foo(4, "Jack"));
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
	}

	@Test
	public void test6() throws JsonProcessingException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		list.add(map);
		map.put("name", "Peter");
		list.add(map);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
	}
}

class Foo {
	int id;
	String name;

	@Override
	public String toString() {
		return "Foo [id=" + id + ", name=" + name + "]";
	}

	public Foo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}