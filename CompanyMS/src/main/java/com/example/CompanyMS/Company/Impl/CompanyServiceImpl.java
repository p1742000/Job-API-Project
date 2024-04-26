package com.example.CompanyMS.Company.Impl;


import com.example.CompanyMS.Company.Company;
import com.example.CompanyMS.Company.CompanyRepository;
import com.example.CompanyMS.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

//    private List<Company> companies = new ArrayList<>();

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

//    private Long nextId = 1L;
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public String createCompany(Company company) {
        companyRepository.save(company);
//        company.setId(nextId++);
//        companies.add(company);
        return "Company created successfully. ";

    }

    @Override
    public Company getCompanyById(Long Id) {
        return companyRepository.findById(Id).orElse(null);
//        for (Company company: companies
//        ) {
//            if(company.getId().equals(Id)) return company;
//        }
//        return null;
    }

    @Override
    public Boolean deleteCompanyById(Long Id) {

        try{
            companyRepository.deleteById(Id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateCompanyById(Long Id, Company updatedCompany) {
        if(companyRepository.findById(Id).isPresent()){
            Company company = companyRepository.findById(Id).get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
