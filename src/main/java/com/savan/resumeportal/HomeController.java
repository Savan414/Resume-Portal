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
        e1.setCollege("Awesome College");
        e1.setQualification("Useless Degree");
        e1.setSummary("Studied a lot");
        e1.setStartDate(LocalDate.of(2019, 5, 1));
        e1.setEndDate(LocalDate.of(2020, 1, 1));
        userProfileRepository.save(profile1);

        Education e2 = new Education();
        e2.setCollege("Awesome College");
        e2.setQualification("Useless Degree");
        e2.setSummary("Studied a lot");
        e2.setStartDate(LocalDate.of(2019, 5, 1));
        e2.setEndDate(LocalDate.of(2020, 1, 1));

        profile1.getEducations().clear();
        profile1.getEducations().add(e1);
        profile1.getEducations().add(e2);

        userProfileRepository.save(profile1);

        return "Profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "edit page";
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
