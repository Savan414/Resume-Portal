package com.savan.resumeportal;

import com.savan.resumeportal.models.Education;
import com.savan.resumeportal.models.Job;
import com.savan.resumeportal.models.MyUserDetails;
import com.savan.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){


        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
        profileOptional.orElseThrow(() -> new RuntimeException("Not found: "));

        UserProfile profile1 = profileOptional.get();

        Job job1 = new Job();
        job1.setCompany("Company 1");
        job1.setDesignation("Designation");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        //job1.setEndDate(LocalDate.of(2020, 3, 1));
        job1.setCurrentJob(true);
        job1.getResponsibilities().add("Come up with the theory of relativity");
        job1.getResponsibilities().add("Advanced Quantum Mechanics");
        job1.getResponsibilities().add("Blow people's Brains out!");

        Job job2 = new Job();
        job2.setCompany("Company 2");
        job2.setDesignation("Designation");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2019, 5, 1));
        job2.setEndDate(LocalDate.of(2020, 1, 1));
        job2.getResponsibilities().add("Come up with the theory of relativity");
        job2.getResponsibilities().add("Advanced Quantum Mechanics");
        job2.getResponsibilities().add("Blow people's Brains out!");

        List<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        profile1.getJobs().clear();
        profile1.getJobs().add(job1);
        profile1.getJobs().add(job2);

        Education e1 = new Education();
        e1.setCollege("Federal Polytechnic School, Zurich, Switzerland");
        e1.setQualification("Federal Teaching Diploma");
        e1.setSummary("Got diploma for Maths and Physics");
        e1.setStartDate(LocalDate.of(1896, 9, 1));
        e1.setEndDate(LocalDate.of(1900, 1, 1));
        userProfileRepository.save(profile1);

        Education e2 = new Education();
        e2.setCollege("University of Zurich, Switzerland");
        e2.setQualification("Phd");
        e2.setSummary("Dissertation: A new Determination of Molecular Dimensions");
        e2.setStartDate(LocalDate.of(1900, 5, 1));
        e2.setEndDate(LocalDate.of(1905, 1, 1));

        profile1.getSkills().clear();
        profile1.getSkills().add("Quantum Physics");
        profile1.getSkills().add("Modern Physics");
        profile1.getSkills().add("Violin");
        profile1.getSkills().add("Philosophy");
        profile1.getEducations().clear();
        profile1.getEducations().add(e1);
        profile1.getEducations().add(e2);

        userProfileRepository.save(profile1);

        return "Profile";
    }

    @GetMapping("/edit")
    public String edit(Model model,Principal principal){
        String userId = principal.getName();
        model.addAttribute("userId", userId);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model,Principal principal){
        String userId = principal.getName();
       // Save the updated values in the form
        return "redirect:/view/" + userId;
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model){
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        System.out.println(userProfile.getJobs().toString());

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
