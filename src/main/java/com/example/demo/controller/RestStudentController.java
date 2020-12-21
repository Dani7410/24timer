package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Superviser;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SuperviserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")

@RestController
//@RequestMapping("/app-api/students")
public class RestStudentController {


    StudentRepository studentRepository;
    SuperviserRepository superviserRepository;

    //Constructor injection
    public RestStudentController(StudentRepository studentRepository, SuperviserRepository superviserRepository) {
        this.studentRepository = studentRepository;
        this.superviserRepository = superviserRepository;
    }

    // finder alle students data
    @GetMapping("/student")
    public Iterable<Student> findAll(){
        return studentRepository.findAll();
    }

    //finder student via ID
    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> findByid(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
            if(student.isPresent()){
                return ResponseEntity.status(200).body(student);
            }else{
                return ResponseEntity.status(404).body(student);
        }
    }

    // create a new student
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/student", consumes = {"application/json"})
    public ResponseEntity<String> create(@RequestBody Student stu) {
        Student _student = new Student(stu.getName(), stu.getEmail(), stu.getSuperviser());

        studentRepository.save(_student);
        return ResponseEntity.status(201).header("Location", "/student/" + _student.getId()).body("{'Msg': 'post created'}");

    }

    //update target
    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student stu){
        //get student by id
        Optional<Student>optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){

            return ResponseEntity.status(404).body("{'msg':'Not found'");
        }
        studentRepository.save(stu);
        return ResponseEntity.status(204).body("{'msg':'Updated'}");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()){
            return ResponseEntity.status(404).body("{'msg':'Not found'"); // Not found
        }
        Student stu = student.get();
        //studentRepository.save(stu);
        studentRepository.deleteById(id);
        studentRepository.save(stu);
        return ResponseEntity.status(200).body("{'msg':'Deleted'}");
    }












}
