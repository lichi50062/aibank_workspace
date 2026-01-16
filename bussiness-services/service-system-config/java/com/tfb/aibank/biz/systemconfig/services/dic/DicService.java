package com.tfb.aibank.biz.systemconfig.services.dic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.tfb.aibank.biz.systemconfig.repository.DicRepository;
import com.tfb.aibank.biz.systemconfig.services.dic.model.DicModel;

public class DicService {

    private DicRepository dicRepository;

    public DicService(DicRepository dicRepository) {
        this.dicRepository = dicRepository;
    }

    /**
     * 取得 AIBank DIC 所有的資料
     * 
     * @return
     */
    public List<DicModel> getAllData() {
        return this.dicRepository.findAll(Sort.by(Direction.ASC, "category", "dicKey")).stream().map(DicModel::new).collect(Collectors.toList());
    }

}