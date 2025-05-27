package com.cooksys.wikiProjectAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cooksys.wikiProjectAPI.embeddables.Credentials;
import com.cooksys.wikiProjectAPI.embeddables.Profile;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
@Component
public class Seeder implements CommandLineRunner {
  private CompanyRepository companyRepository;
  private UserRepository userRepository;
  public Seeder(CompanyRepository companyRepository, UserRepository userRepository) {
    this.companyRepository = companyRepository;
    this.userRepository = userRepository;
  }
  @Override
  public void run(String... args) throws Exception {
    Company company1 = new Company();
    company1.setName("FedEx");
    company1.setDescription("Test company 1");
    companyRepository.saveAndFlush(company1);
    
    Company company2 = new Company();
    company2.setName("Cook Systems");
    company2.setDescription("Test company 2");
    companyRepository.saveAndFlush(company2);
    
    Company company3 = new Company();
    company3.setName("Google");
    company3.setDescription("Test company 3");
    companyRepository.saveAndFlush(company3);
    
    User worker = new User();
    worker.setProfile(new Profile()); 
    worker.getProfile().setFirst("Worker first");
    worker.getProfile().setLast("Worker last");
    worker.getProfile().setEmail("worker@worker.com");
    worker.getProfile().setPhone("000-000-0000");
    worker.setCredentials(new Credentials());
    worker.getCredentials().setUsername("test123");
    worker.getCredentials().setPassword("password123");
    worker.setActive(true);
    worker.setAdmin(false);
    worker.setStatus("ACTIVE");
    worker.getCompanies().add(company1);
    company1.getUsers().add(worker);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(worker);
    
    User admin = new User();
    admin.setProfile(new Profile());
    admin.getProfile().setFirst("Admin first");
    admin.getProfile().setLast("Admin last");
    admin.getProfile().setEmail("admin@admin.com");
    admin.getProfile().setPhone("000-000-0000");
    admin.setCredentials(new Credentials());
    admin.getCredentials().setUsername("admin");
    admin.getCredentials().setPassword("password");
    admin.setActive(true);
    admin.setAdmin(true);
    admin.setStatus("ACTIVE");
    userRepository.saveAndFlush(admin);
    
    System.out.println("Database seeded with initial data.");
  }}
