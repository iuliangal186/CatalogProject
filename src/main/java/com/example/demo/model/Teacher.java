package com.example.demo.model;

import com.example.demo.dto.SubjectDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

    @Column(name = "cnp")
    private String cnp;

    @Column(name = "salary")
    private Long salary;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "teacher_id")
    private List<Subject> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
