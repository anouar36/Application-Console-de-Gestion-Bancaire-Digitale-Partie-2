package org.example.Repository;

import org.example.Modle.Rapport;
import org.example.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RapportRepository {
    public void save(Rapport rapport) {
        String sql = "INSERT INTO reports (report_name, author_name, report_date, credit_status, description, report_text) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rapport.getReportName());
            ps.setString(2, rapport.getAuthorName());
            ps.setDate(3, java.sql.Date.valueOf(rapport.getReportDate()));
            ps.setString(4, rapport.getCreditStatus());
            ps.setString(5, rapport.getDescription());
            ps.setString(6, rapport.getReportText());
            ps.executeUpdate();
            System.out.println("✅ Report saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
