package com.tfb.aibank.biz.systemconfig.services.tasktime;

import java.util.List;
import java.util.Optional;

import com.tfb.aibank.biz.systemconfig.repository.TaskTimeRepository;
import com.tfb.aibank.biz.systemconfig.repository.entities.TaskTimeEntity;
import com.tfb.aibank.biz.systemconfig.services.tasktime.model.TaskTimeModel;

public class TaskTimeService {

    private TaskTimeRepository taskTimeRepository;

    public TaskTimeService(TaskTimeRepository taskTimeRepository) {
        this.taskTimeRepository = taskTimeRepository;
    }

    public TaskTimeModel fingTaskTimeById(String taskId) {
        Optional<TaskTimeEntity> entityOpt = this.taskTimeRepository.findById(taskId);
        if (entityOpt.isPresent()) {
            return convertToModel(entityOpt.get());
        }
        return null;
    }

    public TaskTimeModel convertToModel(TaskTimeEntity ent) {
        TaskTimeModel model = new TaskTimeModel();
        model.setTaskId(ent.getTaskId());
        model.setGeneralStartTime(ent.getGeneralStartTime());
        model.setGeneralEndTime(ent.getGeneralEndTime());
        model.setFxmlStartTime(ent.getFxmlStartTime());
        model.setFxmlEndTime(ent.getFxmlEndTime());
        model.setRemitStartTime(ent.getRemitStartTime());
        model.setRemitEndTime(ent.getRemitEndTime());
        model.setChannelFlag(ent.getChannelFlag());
        model.setReserveDays(ent.getReserveDays());
        return model;
    }

    public List<TaskTimeModel> getAllData() {
        return this.taskTimeRepository.findAll().stream().map(this::convertToModel).toList();
    }
}
