package com.example.invproject.service;

import com.example.invproject.Dto.DashboardReportDTO;
import com.example.invproject.Dto.DashboardReportDTO.SchoolInfo;
import com.example.invproject.model.Users;
import com.example.invproject.repository.ItemRepository;
import com.example.invproject.repository.SubjectRepository;
import com.example.invproject.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public DashboardService(ItemRepository itemRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    public DashboardReportDTO getDashboardReport() {
        List<?> allItems = itemRepository.findAll();

        long totalItems = allItems.size();
        long totalQuantity = allItems.stream()
            .mapToLong(item -> {
                try {
                    // Cast to Item to access getQuantity()
                    return (long) item.getClass().getMethod("getQuantity").invoke(item);
                } catch (Exception e) {
                    return 0L;
                }
            }).sum();

        Map<String, Long> itemsByStatus = allItems.stream()
            .collect(Collectors.groupingBy(item -> {
                try {
                    Object status = item.getClass().getMethod("getStatus").invoke(item);
                    return status != null ? status.toString() : "Unknown";
                } catch (Exception e) {
                    return "Unknown";
                }
            }, Collectors.counting()));

        Map<String, Long> itemsByCategory = allItems.stream()
            .collect(Collectors.groupingBy(item -> {
                try {
                    Object category = item.getClass().getMethod("getCategory").invoke(item);
                    if (category != null) {
                        Object categoryName = category.getClass().getMethod("getCategoryName").invoke(category);
                        return categoryName != null ? categoryName.toString() : "Uncategorized";
                    } else {
                        return "Uncategorized";
                    }
                } catch (Exception e) {
                    return "Uncategorized";
                }
            }, Collectors.counting()));

        long totalUsers = userRepository.count();

        long totalSubjects = subjectRepository.count();

        List<SchoolInfo> schools = userRepository.findAll().stream()
            .map((Users user) -> new SchoolInfo(
                user.getSchool() != null && user.getSchool().getSchoolName() != null
                    ? user.getSchool().getSchoolName()
                    : "Unknown School",
                user.getSchool() != null && user.getSchool().getAddress() != null
                    ? user.getSchool().getAddress()
                    : "Unknown Address"))
            .distinct()
            .collect(Collectors.toList());

        DashboardReportDTO report = new DashboardReportDTO();
        report.setTotalItems(totalItems);
        report.setTotalQuantity(totalQuantity);
        report.setItemsByStatus(itemsByStatus);
        report.setItemsByCategory(itemsByCategory);

        report.setTotalUsers(totalUsers);
        report.setTotalSubjects(totalSubjects);
        report.setSchools(schools);

        return report;
    }
}
