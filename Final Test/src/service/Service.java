package service;


import domain.Student;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }


public List<Student> getAllBacts() {
    return repo.get().stream()
            .sorted(Comparator.comparing((Student s) -> !s.getYear().equals("2025"))
                    .thenComparing(Student::getYear, Comparator.reverseOrder()))
            .collect(Collectors.toList());
}

    public List<Student> searchBacts(boolean checked, String nameDescription) {
        return repo.get().stream()
                .sorted(Comparator.comparing((Student s) -> !s.getYear().equals("2025"))
                        .thenComparing(Student::getYear, Comparator.reverseOrder()))
                .filter(r -> !checked || "No title".equals(r.getTitle()))
                .filter(r-> r.getName().contains(nameDescription) || r.getYear().contains(nameDescription))
                .toList();
    }

    public void update(String title,String percentage, Student student) {
        repo.update(title,percentage, student.getName());
    }


}
