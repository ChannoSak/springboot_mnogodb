package com.javaspring.javaspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class JavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"CANADA",
					"SR",
					"1290"
			);
			String email = "dongdara@gmail.com";
			Student student = new Student(
					"Dara",
					"Dong",
					email,
					Gender.MALE,
					address,
					List.of("Computer Science", "Computer Fundamental"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			repository.findStudentByEmail(email)
					.ifPresentOrElse(s->{
						System.out.println(s + "already exists");
					}, ()->{
						System.out.println("Inserting student" + student);
						repository.insert(student);
					});
			//__________________Query without customize using repository___________
//			Query query = new Query();
//			query.addCriteria(Criteria.where("email").is(email));
//			List<Student> students = mongoTemplate.find(query, Student.class);
//			if(students.size()>1){
//				throw new IllegalStateException("found many student with email"+ email);
//			}
//			if(students.isEmpty()) {
//				System.out.println("Inserting student" + student);
//				repository.insert(student);
//			}else {
//				System.out.println(student + "already exist");
//			}
			//_____________________________________________________________________________
		};


	}

}
