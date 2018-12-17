package com.sarah.dao;

import com.sarah.model.LeanlineCategory;

public interface LeanlineCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeanlineCategory record);

    int insertSelective(LeanlineCategory record);

    LeanlineCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeanlineCategory record);

    int updateByPrimaryKey(LeanlineCategory record);
}