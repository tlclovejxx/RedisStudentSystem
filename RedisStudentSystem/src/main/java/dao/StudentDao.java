package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import entity.Student;
import util.*;

public class StudentDao {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public List<Student> findAll() throws ParseException{
		List<Student> students = new ArrayList<Student>();
		Jedis jedis = JedisUtil.getJedis();
		Set<String> ids= jedis.zrevrange("studentlist", 0, -1);
		for(String id:ids){
			Student student = new Student();
			student.setId(id);
			student.setName(jedis.hget(id, "name"));
			student.setBirthday(dateFormat.parse(jedis.hget(id, "birthday")));
			student.setDescription(jedis.hget(id, "description"));
			student.setAvagscore(Integer.valueOf(jedis.hget(id, "avagscore")));
			students.add(student);
		}
		return students;
	}
	public Student findStudent(String id) throws ParseException{
		Jedis jedis = JedisUtil.getJedis();
		Student student = new Student();
		student.setId(id);
		student.setName(jedis.hget(id, "name"));
		student.setBirthday(dateFormat.parse(jedis.hget(id, "birthday")));
		student.setDescription(jedis.hget(id, "description"));
		student.setAvagscore(Integer.valueOf(jedis.hget(id, "avagscore")));
		return student;
	}
	public void delete(Student student){
		Jedis jedis = JedisUtil.getJedis();
		String id = student.getId();
		jedis.zrem("studentlist", id);
		jedis.del(id);
	}
	public void save(Student student) throws Exception{
		Jedis jedis = JedisUtil.getJedis();
		String id = student.getId();
		Double flog = jedis.zscore("studentlist", student.getId());
		if (flog != null){
			//如果当前学生ID存在，抛出异常
			throw new Exception("ID being used");
		}else{
			jedis.zadd("studentlist",student.getAvagscore(),id);
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", student.getName());
			map.put("birthday", dateFormat.format(student.getBirthday()));
			map.put("description", student.getDescription());
			map.put("avagscore", String.valueOf(student.getAvagscore()));
			jedis.hmset(id, map);
		}
	}
	public void update(Student student){
		Jedis jedis = JedisUtil.getJedis();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", student.getName());
		map.put("birthday", dateFormat.format(student.getBirthday()));
		map.put("description", student.getDescription());
		map.put("avagscore", String.valueOf(student.getAvagscore()));
		jedis.hmset(student.getId(), map);
		jedis.zadd("studentlist", student.getAvagscore(),student.getId());
	}
}
