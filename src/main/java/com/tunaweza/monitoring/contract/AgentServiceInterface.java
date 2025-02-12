package com.tunaweza.monitoring.contract;

import java.util.List;
import java.util.UUID;

import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.model.User;


public interface AgentServiceInterface {

    User save(User agent);
    void delete(UUID id);
    User update(UUID id, User agent);

    User findAgentByIdAndTypeUser(UUID id, TypeUser typeUser);
    User findAgentByUsernameAndTypeUser(String username,TypeUser typeUser);

     List<User> findAllByTypUsers(TypeUser typeUser);
    //public List<Around> findAroundByShift(Shift shift);

}
