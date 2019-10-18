package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;
    @Autowired
    private DentistService dentistService;

//    private static final Logger logger = LoggerFactory.getLogger(DentistAppController.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        // Adding the dentists since we're only using an in-memory database
        dentistService.addDentistsIfNone();
        // adding it to the modelattribute would be usually covered by the @ModelAttribute but since it's fired before we add fields to the database
        model.addAttribute("dentists", dentistService.getDentists());

        return "form";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addDentistVisit(dentistService.getDentistById(dentistVisitDTO.getDentistId()),
                dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime());
        // TODO: maybe it's possible to pass the dentist object to DTO in which case the extra query to find dentist by id won't be needed?
        System.out.println(dentistVisitService.getDentistVisits().size());
        return "redirect:/results";
    }

    @ModelAttribute("dentists")
    public List<DentistEntity> dentists() {
        return dentistService.getDentists();
    }
}
