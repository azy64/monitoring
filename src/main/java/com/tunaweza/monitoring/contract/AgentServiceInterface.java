package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.Company;
import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.model.User;


public interface AgentServiceInterface {

    User save(User agent);
    void delete(UUID id);
    User update(UUID id, User agent);

    User findAgentByIdAndTypeUser(UUID id, TypeUser typeUser);
    User findAgentByUsernameAndTypeUser(String username,TypeUser typeUser);
    User findAgentByUsername(String username);

     List<User> findAllByTypUsers(TypeUser typeUser);
    //public List<Around> findAroundByShift(Shift shift);
    User findById(UUID id);
    List<User> findAll();
    List<User>findByEmployer(Company company);

}
