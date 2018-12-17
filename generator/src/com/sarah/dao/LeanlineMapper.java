package com.sarah.dao;

import com.sarah.model.Leanline;

public interface LeanlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leanline record);

    int insertSelective(Leanline record);

    Leanline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leanline record);

    int updateByPrimaryKey(Leanline record);
}