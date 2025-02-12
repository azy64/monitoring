package com.tunaweza.monitoring.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.AgentServiceInterface;
import com.tunaweza.monitoring.mapper.AgentMapper;
import com.tunaweza.monitoring.model.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AgentController {
    private final AgentServiceInterface agentService;

    @PostMapping("/agent")
    public ResponseEntity<?> createAgent(@RequestBody User agent){
        User agentChecked = agentService.findAgentByUsernameAndTypeUser(agent.getUsername(), agent.getTypeUser());
        if(agentChecked==null)ResponseEntity.ok(AgentMapper.mapToDto(agentService.save(agent)));
        return ResponseEntity.badRequest().body("The Agent exist already");
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<?> getAgent(@PathVariable UUID id){
        return ResponseEntity.ok(AgentMapper.mapToDto(agentService.findById(id)));
    }

    @GetMapping("/agent")
    public ResponseEntity<?>getAllAgents(){
        return ResponseEntity.ok(
            agentService.findAll().stream().map(agent->AgentMapper.mapToDto(agent)).toList()
        );
    }

    
}
