package com.example.invproject.Dto;

import java.util.List;
import java.util.Map;

public class DashboardReportDTO {

    private long totalItems;
    private long totalQuantity;
    private Map<String, Long> itemsByStatus;
    private Map<String, Long> itemsByCategory;

    private long totalUsers;
    private long totalSubjects;
    private List<SchoolInfo> schools;

    // getters
    public long getTotalItems() {
        return totalItems;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public Map<String, Long> getItemsByStatus() {
        return itemsByStatus;
    }

    public Map<String, Long> getItemsByCategory() {
        return itemsByCategory;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalSubjects() {
        return totalSubjects;
    }

    public List<SchoolInfo> getSchools() {
        return schools;
    }

    // setters
    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setItemsByStatus(Map<String, Long> itemsByStatus) {
        this.itemsByStatus = itemsByStatus;
    }

    public void setItemsByCategory(Map<String, Long> itemsByCategory) {
        this.itemsByCategory = itemsByCategory;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public void setTotalSubjects(long totalSubjects) {
        this.totalSubjects = totalSubjects;
    }

    public void setSchools(List<SchoolInfo> schools) {
        this.schools = schools;
    }

    // Inner DTO class
    public static class SchoolInfo {
        private String schoolName;
        private String address;

        public SchoolInfo(String schoolName, String address) {
            this.schoolName = schoolName;
            this.address = address;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
