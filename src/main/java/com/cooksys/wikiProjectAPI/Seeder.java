package com.cooksys.wikiProjectAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cooksys.wikiProjectAPI.embeddables.Credentials;
import com.cooksys.wikiProjectAPI.embeddables.Profile;
import com.cooksys.wikiProjectAPI.entities.Announcement;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.Project;
import com.cooksys.wikiProjectAPI.entities.Team;
import com.cooksys.wikiProjectAPI.entities.User;
import com.cooksys.wikiProjectAPI.repositories.AnnouncementRepository;
import com.cooksys.wikiProjectAPI.repositories.CompanyRepository;
import com.cooksys.wikiProjectAPI.repositories.ProjectRepository;
import com.cooksys.wikiProjectAPI.repositories.TeamRepository;
import com.cooksys.wikiProjectAPI.repositories.UserRepository;
@Component
public class Seeder implements CommandLineRunner {
  private CompanyRepository companyRepository;
  private UserRepository userRepository;
  private AnnouncementRepository announcementRepository;
  private TeamRepository teamRepository;
  private ProjectRepository projectRepository;

  public Seeder(CompanyRepository companyRepository, UserRepository userRepository, 
                AnnouncementRepository announcementRepository, TeamRepository teamRepository,
                ProjectRepository projectRepository) {
    this.companyRepository = companyRepository;
    this.userRepository = userRepository;
    this.announcementRepository = announcementRepository;
    this.teamRepository = teamRepository;
    this.projectRepository = projectRepository;
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
    admin.getCompanies().add(company1);
    admin.getCompanies().add(company2);
    admin.getCompanies().add(company3);
    company1.getUsers().add(admin);
    company2.getUsers().add(admin);
    company3.getUsers().add(admin);
    companyRepository.saveAndFlush(company1);
    companyRepository.saveAndFlush(company2);
    companyRepository.saveAndFlush(company3);
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

    Team team1 = new Team();
    team1.setName("Development Team");
    team1.setDescription("This is the development team.");
    team1.setCompany(company1);
    team1.getUsers().add(worker);
    team1.getUsers().add(workerJane);
    company1.getTeams().add(team1);
    worker.getTeams().add(team1);
    workerJane.getTeams().add(team1);
    teamRepository.saveAndFlush(team1);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(worker);
    userRepository.saveAndFlush(workerJane);

    Team team2 = new Team();
    team2.setName("Design Team");
    team2.setDescription("This is the design team.");
    team2.setCompany(company1);
    team2.getUsers().add(worker);
    team2.getUsers().add(workerJane);
    company1.getTeams().add(team2);
    worker.getTeams().add(team2);
    workerJane.getTeams().add(team2);
    teamRepository.saveAndFlush(team2);
    companyRepository.saveAndFlush(company1);
    userRepository.saveAndFlush(worker);
    userRepository.saveAndFlush(workerJane);

    Project project1 = new Project();
    project1.setName("Project Alpha");
    project1.setDescription("This is the first project.");
    project1.setActive(true);
    project1.setTeam(team1);
    team1.getProjects().add(project1);
    projectRepository.saveAndFlush(project1);
    teamRepository.saveAndFlush(team1);

    Project project2 = new Project();
    project2.setName("Project Beta");
    project2.setDescription("This is the second project.");
    project2.setActive(true);
    project2.setTeam(team1);
    team1.getProjects().add(project2);
    projectRepository.saveAndFlush(project2);
    teamRepository.saveAndFlush(team1);
   
    Project project3 = new Project();
    project3.setName("Project Gamma");
    project3.setDescription("This is the third project.");
    project3.setActive(true);
    project3.setTeam(team2);
    team2.getProjects().add(project3);
    projectRepository.saveAndFlush(project3);
    teamRepository.saveAndFlush(team2);
  
    Project project4 = new Project();
    project4.setName("Project Delta");
    project4.setDescription("This is the fourth project.");
    project4.setActive(true);
    project4.setTeam(team1);
    team1.getProjects().add(project4);
    projectRepository.saveAndFlush(project4);
    teamRepository.saveAndFlush(team1);
    
    System.out.println("Database seeded with initial data.");
  }
}
