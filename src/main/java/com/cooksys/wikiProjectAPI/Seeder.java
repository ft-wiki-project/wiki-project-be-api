package com.cooksys.wikiProjectAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cooksys.wikiProjectAPI.embeddables.Credentials;
import com.cooksys.wikiProjectAPI.embeddables.Profile;
import com.cooksys.wikiProjectAPI.entities.Announcement;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.repositories.AnnouncementRepository;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
@Component
public class Seeder implements CommandLineRunner {
  private CompanyRepository companyRepository;
  private UserRepository userRepository;
  private AnnouncementRepository announcementRepository;
  public Seeder(CompanyRepository companyRepository, UserRepository userRepository, 
                AnnouncementRepository announcementRepository) {
    this.companyRepository = companyRepository;
    this.userRepository = userRepository;
    this.announcementRepository = announcementRepository;
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
    worker.getProfile().setFirst("WorkerJoe");
    worker.getProfile().setLast("Workersson");
    worker.getProfile().setEmail("joe@worker.com");
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
    
    User workerJane = new User();
    workerJane.setProfile(new Profile()); 
    workerJane.getProfile().setFirst("WorkerJane");
    workerJane.getProfile().setLast("Workersson");
    workerJane.getProfile().setEmail("jane@worker.com");
    workerJane.getProfile().setPhone("000-000-0000");
    workerJane.setCredentials(new Credentials());
    workerJane.getCredentials().setUsername("jane");
    workerJane.getCredentials().setPassword("password");
    workerJane.setActive(true);
    workerJane.setAdmin(false);
    workerJane.setStatus("ACTIVE");
    workerJane.getCompanies().add(company1);
    company1.getUsers().add(workerJane);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(workerJane);
    
    User admin = new User();
    admin.setProfile(new Profile());
    admin.getProfile().setFirst("AdminJoe");
    admin.getProfile().setLast("Adminson");
    admin.getProfile().setEmail("admin@admin.com");
    admin.getProfile().setPhone("000-000-0000");
    admin.setCredentials(new Credentials());
    admin.getCredentials().setUsername("admin");
    admin.getCredentials().setPassword("password");
    admin.setActive(true);
    admin.setAdmin(true);
    admin.setStatus("ACTIVE");
    userRepository.saveAndFlush(admin);

    Announcement announcement1 = new Announcement();
    announcement1.setTitle("Welcome to the company!");
    announcement1.setMessage("We are excited to have you on board.");
    announcement1.setCompany(company1);
    announcement1.setAuthor(admin);
    company1.getAnnouncements().add(announcement1);
    admin.getAnnouncements().add(announcement1);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(admin);
    announcementRepository.saveAndFlush(announcement1);

    Announcement announcement2 = new Announcement();
    announcement2.setTitle("Company Meeting");
    announcement2.setMessage("There will be a company meeting next week.");
    announcement2.setCompany(company1);
    announcement2.setAuthor(admin);
    company1.getAnnouncements().add(announcement2);
    admin.getAnnouncements().add(announcement2);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(admin);
    announcementRepository.saveAndFlush(announcement2);

    Announcement announcement3 = new Announcement();
    announcement3.setTitle("Holiday Schedule");
    announcement3.setMessage("The holiday schedule has been released.");
    announcement3.setCompany(company1);
    announcement3.setAuthor(admin);
    company1.getAnnouncements().add(announcement3);
    admin.getAnnouncements().add(announcement3);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(admin);
    announcementRepository.saveAndFlush(announcement3);
    
    System.out.println("Database seeded with initial data.");
  }}
