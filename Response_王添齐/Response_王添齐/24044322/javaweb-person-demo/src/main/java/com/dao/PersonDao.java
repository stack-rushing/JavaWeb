package com.dao;

import com.entity.Person;
import com.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public int addPerson(Person p) {
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO person(name,age,gender,phone) VALUES(?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getAge());
            pstmt.setString(3, p.getGender());
            pstmt.setString(4, p.getPhone());
            return pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM person";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setPhone(rs.getString("phone"));
                list.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
