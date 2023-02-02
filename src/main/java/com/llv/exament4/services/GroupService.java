package com.llv.exament4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llv.exament4.models.Group;
import com.llv.exament4.repositories.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public Group getGroup(Long groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
