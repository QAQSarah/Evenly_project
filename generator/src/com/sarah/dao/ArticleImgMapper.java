package com.sarah.dao;

import com.sarah.model.ArticleImg;

public interface ArticleImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleImg record);

    int insertSelective(ArticleImg record);

    ArticleImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleImg record);

    int updateByPrimaryKey(ArticleImg record);
}