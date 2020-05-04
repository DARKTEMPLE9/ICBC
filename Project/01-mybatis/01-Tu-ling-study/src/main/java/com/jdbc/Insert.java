package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

    /**
     * 为什么使用线程池
     * 1、jdbc底层没有用线程池、操作数据库需要频繁的创建和关闭连接，消耗很大的资源
     * 2、不利于维护
     * 3、使用PreparedStatement预编译的话对变量进行设置123数字，序号不利于维护
     * 4、返回的result结果集也需要硬编码
     */

    public static void main(String[] args) {
        insert("mengxq", "test-jdbc");
    }

    private static void insert(String name, String msg) {
        String sql = "INSERT INTO jdbc ( name,msg ) VALUES (?,?)";
        Connection conn = DbUtil.open();
        try {
            PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, msg);
            pstm.executeUpdate();
            System.out.println("insert数据成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
    }

}
