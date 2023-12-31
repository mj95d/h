package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OperationCompanyRepository extends JpaRepository<OperationCompany,Integer> {

    OperationCompany findOperationCompanyById(Integer id);
    OperationCompany findOperationCompanyByName(String name);

}