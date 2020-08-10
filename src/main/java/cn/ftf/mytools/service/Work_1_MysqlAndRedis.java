package cn.ftf.mytools.service;


import redis.clients.jedis.Jedis;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查Mysql和Redis，处理Mysql和Redis的数据
 */
public class Work_1_MysqlAndRedis {

    public static void main(String[] args) throws SQLException {
        //连接Redis
        Jedis jedis=null;
        try {
            jedis = new Jedis("39.101.191.209", 6378);
            jedis.auth("Ftf_666666");
            jedis.connect();//连接
        } catch (Exception e) {
            e.printStackTrace();
        }

        //注册MySQL驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        java.sql.Connection connection;
        //创建连接,执行sql
        try {
            connection = DriverManager.getConnection("jdbc:mysql://39.101.191.209/myblog?characterEncoding=UTF-8", "root", "Ftf_666666");
            Statement statement = connection.createStatement();
            String sql = "select * from t_blog";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String id = resultSet.getString("id");
                String views = resultSet.getString("views");
                System.out.println(id + title + views);
                jedis.zadd("blog",Integer.parseInt(views),id+"-"+title);
            }


            connection.close();
            jedis.disconnect();
        }catch (Exception  e){
            System.out.println(e);
        }
    }
}
