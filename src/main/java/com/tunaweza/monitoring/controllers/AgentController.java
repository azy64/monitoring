package com.tunaweza.monitoring.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.AgentServiceInterface;
import com.tunaweza.monitoring.contract.CheckPointServiceInterface;
import com.tunaweza.monitoring.contract.ShiftServiceInterface;
import com.tunaweza.monitoring.dto.CheckPointDTO;
import com.tunaweza.monitoring.dto.ShiftDTO;
import com.tunaweza.monitoring.mapper.AgentMapper;
import com.tunaweza.monitoring.mapper.CheckPointMapper;
import com.tunaweza.monitoring.mapper.ShiftMapper;
import com.tunaweza.monitoring.model.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AgentController {
    private final AgentServiceInterface agentService;
    private final ShiftServiceInterface shiftService;
    private final CheckPointServiceInterface checkPointService;

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

    @DeleteMapping("/agent/{id}")
    public ResponseEntity<?>deleteAgent(@PathVariable UUID id){
        agentService.delete(id);
        return ResponseEntity.ok("Agent has been deleted");
    }
    
    @PutMapping("/agent/{id}")
    public ResponseEntity<?> updateAgent(@PathVariable UUID id, @RequestBody User agent){
        return ResponseEntity.ok(AgentMapper.mapToDto(agentService.update(id, agent)));
    }

    @GetMapping("/agent/{id}/shift")
    public ResponseEntity<?> getMyshifts(@PathVariable UUID id){
        User agent = agentService.findById(id);
        return ResponseEntity.ok(
            shiftService.findShiftByAgent(agent).stream().map(shift->ShiftMapper.mapToDto(shift)).toList()
        );
    }

    @GetMapping("/agent/{id}/shift/{shiftId}")
    public ResponseEntity<?> getMyshifts(@PathVariable UUID id, @PathVariable UUID shiftId){
        User agent = agentService.findById(id);
        List<ShiftDTO>shifts = shiftService.findShiftByAgent(agent).stream().map(shift->ShiftMapper.mapToDto(shift)).toList();

        return ResponseEntity.ok(
            shifts.stream().filter(shiftDto->shiftDto.getId()==shiftId)
        );
    }

    /*
     * getting the checkpoint
     */
    @GetMapping("/agent/{id}/check-point")
    public ResponseEntity<?> getMyCheckPoint(@PathVariable UUID id){
        User agent = agentService.findById(id);
        return ResponseEntity.ok(
            checkPointService.findByAgent_Id(agent.getId()).stream()
            .map(checkpoint->CheckPointMapper.mapToDto(checkpoint)).toList()
        );
    }

    @GetMapping("/agent/{id}/check-point/{CheckPointId}")
    public ResponseEntity<?> getMyCheckPoint(@PathVariable UUID id, @PathVariable UUID CheckPointId){
        User agent = agentService.findById(id);
        List<CheckPointDTO>checkPointDTO = checkPointService.findByAgent_Id(agent.getId()).stream()
        .map(checkpoint->CheckPointMapper.mapToDto(checkpoint)).toList();
        return ResponseEntity.ok(
            checkPointDTO.stream().filter(checkPointDto->checkPointDto.getId()==CheckPointId)
            .toList().get(0)
        );
    }
    
}
