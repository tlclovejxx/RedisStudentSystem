package util;

import redis.clients.jedis.Jedis;

public class JedisUtil {
	public static Jedis getJedis(){
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//jedis.auth("123456");
		jedis.select(3);
		return jedis;
	}
	public static void close(Jedis jedis){
		if (jedis != null) {
			jedis.close();
		}
	}
}
