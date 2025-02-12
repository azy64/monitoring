package com.tunaweza.monitoring.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tunaweza.monitoring.contract.AgentServiceInterface;
import com.tunaweza.monitoring.model.TypeUser;
import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class AgentService implements AgentServiceInterface{

    private final UserRepository userRepository;
    @Override
    public User save(User agent) {
        if(userRepository.findByUsername(agent.getUsername())==null)
            return userRepository.save(agent);
        throw new UnsupportedOperationException("This agent with this username:"+agent.getUsername()+" exist already.");
        
    }

    @Override
    public void delete(UUID id) {
        User agent = userRepository.findById(id).orElse(null);
        if(agent!=null) userRepository.delete(agent);
        throw new UnsupportedOperationException("The Agent does not exist'");
    }

    @Override
    public User update(UUID id, User agent) {
        User previousAgent = userRepository.findById(id).orElse(null);
        if(previousAgent!=null){
            previousAgent.setAddress(agent.getAddress());
            previousAgent.setNom(agent.getNom());
            previousAgent.setPrenom(agent.getPrenom());
            previousAgent.setBirth(agent.getBirth());
            previousAgent.setPassword(agent.getPassword());
            previousAgent.setUsername(agent.getUsername());
            previousAgent.setPhoneNumber(agent.getPhoneNumber());
            previousAgent.setIsUsingMfa(agent.getIsUsingMfa());
            previousAgent.setReferenceNumber(agent.getReferenceNumber());
            previousAgent.setActivated(agent.getActivated());
            previousAgent.setPictureUser(agent.getPictureUser());

            return userRepository.save(previousAgent);
        }
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public User findAgentByIdAndTypeUser(UUID id, TypeUser typeUser) {
        User agent = userRepository.findByIdAndTypeUser(id, typeUser);
        if(agent!=null)return agent;
        throw new UnsupportedOperationException("The agent does not exist'");
    }

    @Override
    public User findAgentByUsernameAndTypeUser(String username, TypeUser typeUser) {
        User agent = userRepository.findByUsernameAndTypeUser(username, typeUser);
        if(agent!=null) return agent;
        throw new UnsupportedOperationException("The agent does not exist");
    }

    @Override
    public List<User> findAllByTypUsers(TypeUser typeUser) {
        List<User> agents = userRepository.findAllByTypeUser(typeUser);
        if(agents!=null) return agents;
        throw new UnsupportedOperationException("Unimplemented method 'findByTypUsers'");
    }

}
