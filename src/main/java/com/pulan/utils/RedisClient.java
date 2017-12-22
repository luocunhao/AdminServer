package com.pulan.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
@Component
public class RedisClient {
	
	public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = RedisConfig.jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            //返还到连接池
            if (jedis != null) jedis.close();
        }
    }
    //取出指定Key的所有消息
    public String get(int data_base,String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = RedisConfig.jedisPool.getResource();
            jedis.select(data_base);
            String ret = jedis.get(key);
            if (null == ret) ret = "";
            return ret;
        } finally {
            //返还到连接池
        	if (jedis != null) jedis.close();
        }
    }
    //写入消息队列
    public void lpush(String key, String value) throws Exception {
    	Jedis jedis = null;
    	try {
    		jedis = RedisConfig.jedisPool.getResource();
    		jedis.lpush(key, value);
    	} finally {
			if (jedis != null) jedis.close();
		}
    }
    //删除消息队列
    public void del(int data_base,String key) throws Exception {
        Jedis jedis = null;
        try {  
            jedis = RedisConfig.jedisPool.getResource();
            jedis.select(data_base);
            jedis.del(key);
        } finally {
            //返还到连接池
        	if (jedis != null) jedis.close();
        }
    }
    //从队列取出消息,移出并获取列表的最后一个元素
	public List<String> brpop(String key, int timeout) throws Exception {
        Jedis jedis = null;
        try {  
            jedis = RedisConfig.jedisPool.getResource();
            return jedis.brpop(timeout, key);
        } finally {
            //返还到连接池
        	if (jedis != null) jedis.close();
        }
    }
	//设置Key过期时间(自动删除)
	public void expire(String key, int seconds) throws Exception {
		Jedis jedis = null;
		try {
			jedis = RedisConfig.jedisPool.getResource();
			jedis.expire(key, seconds);
		} finally {
			if (jedis != null) jedis.close();
		}
	}
	//发布消息
	public void publish(String key, String msg) throws Exception {
		Jedis jedis = null;
		try {
			jedis = RedisConfig.jedisPool.getResource();
			jedis.publish(key, msg);
		} finally {
			if (jedis != null) jedis.close();
		}
	}
	//Set集合存储数据
	public void sadd(String key,String msg) throws Exception{
		Jedis jedis = null;
		try {
			jedis =RedisConfig.jedisPool.getResource();
			jedis.sadd(key,msg);
		}finally {
			if (jedis!=null)
				jedis.close();
		}
	}
	//从Set集合取出所有数据
	public Set<String> smembers(String key) throws Exception{
		Set<String> sets =null;
		Jedis jedis =null;
		try {
			jedis =RedisConfig.jedisPool.getResource();
			sets =jedis.smembers(key);
			if (sets.isEmpty()){
				sets =null;
			}
			return sets;
		}finally {
			if (jedis!=null)
				jedis.close();
		}
	}
	//获取Redis 模糊 Key
	public Set<String> muhuKey(int data_base,String key) throws Exception{
		Set<String> sets =null;
		Jedis jedis =null;
		try {
			jedis =RedisConfig.jedisPool.getResource();
			jedis.select(data_base);
			sets = jedis.keys(key);
			if (sets.isEmpty()){
				sets =null;
			}
			return sets;
		}finally {
			if (jedis!=null)
				jedis.close();
		}
	}
	//取出list集合中的所有值
	public List<String> lrange(int data_base,String key){
		List<String> list =new ArrayList<String>();
		Jedis jedis =null;
		try {
			jedis =RedisConfig.jedisPool.getResource();
			list = jedis.lrange(key,0,-1);
			if (list.isEmpty()){
				list = null;
			}
			return list;
		}finally {
			if (jedis!=null)
				jedis.close();
		}
	}
	//存储json对象字符串到Redis.
	public void saveSemanticModel(int data_base,String key,Object obj) throws Exception{
		Jedis jedis = null;
		String jsObj =JSON.toJSONString(obj);
		try {
			jedis =RedisConfig.jedisPool.getResource();
			jedis.select(data_base);
			jedis.set(key, jsObj);
		} finally {
			if (jedis !=null)
				jedis.close();
		}
	}


}
