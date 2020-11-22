package com.cg.onlineterminsurance.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineterminsurance.App.entity.Policy;

@Repository
public interface PolicyDaoImpl extends JpaRepository<Policy,Integer> {

}
