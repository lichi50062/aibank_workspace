package com.tfb.aibank.biz.systemconfig.services.roletask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.systemconfig.repository.RoleTaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.TaskPageRepository;
import com.tfb.aibank.biz.systemconfig.repository.TaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.dto.RoleTaskDto;
import com.tfb.aibank.biz.systemconfig.repository.entities.TaskEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.TaskPageEntity;
import com.tfb.aibank.biz.systemconfig.services.roletask.model.TaskModel;
import com.tfb.aibank.chl.component.task.TaskPage;

public class RoleTaskService {

    private TaskRepository taskRepository;

    private RoleTaskRepository roleTaskRepository;

    private TaskPageRepository taskPageRepository;

    public RoleTaskService(TaskRepository taskRepository, RoleTaskRepository roleTaskRepository, TaskPageRepository taskPageRepository) {
        this.taskRepository = taskRepository;
        this.roleTaskRepository = roleTaskRepository;
        this.taskPageRepository = taskPageRepository;
    }

    /**
     * 取得 role -> List<taskId:string> mapping
     * 
     * @return
     */
    public Map<String, List<String>> findRoleTaskMapping() {
        List<RoleTaskDto> roleTaskList = this.roleTaskRepository.fetchAllRoleTaskData();
        Map<String, List<String>> mapping = new LinkedHashMap<>();
        roleTaskList.forEach(dto -> {
            List<String> tasks = mapping.get(dto.getRoleName());
            if (tasks == null) {
                tasks = new ArrayList<>();
                mapping.put(dto.getRoleName(), tasks);
            }
            tasks.add(dto.getTaskId());
        });
        return mapping;
    }

    /**
     * 取得所有的 aibank task
     * 
     * @return
     */
    public List<TaskModel> getAllAiBankTasks() {
        return this.taskRepository.findAll(Sort.by(Direction.ASC, "taskId")).stream().filter(task -> StringUtils.equals(task.getBizId(), IBSystemId.AIBANK.getSystemId())).map(this::convertTask).toList();
    }

    private TaskModel convertTask(TaskEntity entity) {
        TaskModel model = new TaskModel();
        model.setAccessControlFlag(entity.getAccessControlFlag());
        model.setAppId(entity.getAppId());
        model.setBizId(entity.getBizId());
        model.setDisplayType(entity.getDisplayType());
        model.setRedirectUrl(entity.getRedirectUrl());
        model.setStatus(entity.getStatus());
        model.setSupportGuestOpFlag(entity.getSupportGuestOpFlag());
        model.setSuspendDesc(entity.getSuspendDesc());
        model.setSuspendEndTime(entity.getSuspendEndTime());
        model.setSuspendStartTime(entity.getSuspendStartTime());
        model.setTaskId(entity.getTaskId());
        model.setTaskTimeout(entity.getTaskTimeout());
        model.setSecurityTypes(entity.getSecurityTypes());
        model.setSecurityOtpTypes(entity.getSecurityOtpTypes());
        model.setIosMinVersion(entity.getIosMinVersion());
        model.setAndroidMinVersion(entity.getAndroidMinVersion());
        model.setCanBeRecord(entity.getCanBeRecord());
        return model;
    }

    public List<TaskPage> getAllAiBankTaskPages() {
        return this.taskPageRepository.findAll(Sort.by(Direction.ASC, "taskPageId"))
                .stream()
                .map(this::convertTaskPage)
                .collect(Collectors.toList());
    }

    private TaskPage convertTaskPage(TaskPageEntity entity) {
        TaskPage model = new TaskPage();
        model.setTaskPageName(entity.getTaskPageName());
        model.setTaskPageId(entity.getTaskPageId());
        model.setTaskId(entity.getTaskId());
        model.setSaveFlag(entity.getSaveFlag());
        return model;
    }
}
