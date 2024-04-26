package com.example.CompanyMS.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping("/companies")
    public ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/companies")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully. ", HttpStatus.CREATED);
    }

    @GetMapping("/companies/{Id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long Id){
       Company response = companyService.getCompanyById(Id);

       if(response != null) return new ResponseEntity(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/companies/{Id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long Id){
        Boolean response = companyService.deleteCompanyById(Id);

        if(response) return new ResponseEntity<>("Company Deleted Successfully. ", HttpStatus.OK);
        return new ResponseEntity<>("Company Id that you are looking for is not found.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/companies/{Id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long Id, @RequestBody Company updatedCompany){
        Boolean response = companyService.updateCompanyById(Id, updatedCompany);
        if(response) return new ResponseEntity<>("Company Edited Successfully. ", HttpStatus.OK);
        return new ResponseEntity<>("Company Id that you are looking for is not found.", HttpStatus.NOT_FOUND);
    }

}
