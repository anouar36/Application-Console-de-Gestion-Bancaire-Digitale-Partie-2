package org.example.Repository;

import org.example.Modle.FeeRule;
import org.example.Modle.TransactionType;
import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FeeRoleRepsitory {


    public FeeRule getFeeRole(String type){
        String sql = "SELECT * FROM fee_rule WHERE UPPER(operation_type) LIKE UPPER(?)";

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + type + "%");  // إضافة % لجعل البحث مشابه
            ResultSet rs = stmt.executeQuery();

            FeeRule feeRule = null;
            if (rs.next()) {
                feeRule = new FeeRule(
                        rs.getString("mode"),
                        rs.getString("operation_type"),
                        new BigDecimal(rs.getInt("value"))
                );
            }

            return feeRule;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
