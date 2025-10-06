package org.example.Modle;

import java.time.LocalDate;
import java.util.List;

public class Rapport {
    private int id;
    private String reportName;
    private String authorName;
    private LocalDate reportDate;
    private String creditStatus;
    private String description;
    private String reportText;

    // Constructor
    public Rapport(String reportName, String authorName, LocalDate reportDate, String creditStatus, String description, String reportText) {
        this.reportName = reportName;
        this.authorName = authorName;
        this.reportDate = reportDate;
        this.creditStatus = creditStatus;
        this.description = description;
        this.reportText = reportText;
    }

    // Getters and setters
    public String getReportText() {
        return reportText;
    }

    public int getId() {
        return id;
    }

    public String getReportName() {
        return reportName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public String getDescription() {
        return description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    // toString
    @Override
    public String toString() {
        return "Report{" +
                "reportName='" + reportName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", reportDate=" + reportDate +
                ", creditStatus='" + creditStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
