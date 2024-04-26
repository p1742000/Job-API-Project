package com.example.CompanyMS.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    String createCompany(Company company);

    Company getCompanyById(Long Id);

    Boolean deleteCompanyById(Long Id);

    Boolean updateCompanyById(Long Id, Company updatedCompany);
}
