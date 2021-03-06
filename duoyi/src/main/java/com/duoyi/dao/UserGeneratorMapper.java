package com.duoyi.dao;

import com.duoyi.model.po.UserGenerator;
import com.duoyi.model.po.UserGeneratorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserGeneratorMapper {
    long countByExample(UserGeneratorCriteria example);

    int deleteByExample(UserGeneratorCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGenerator record);

    int insertSelective(UserGenerator record);

    List<UserGenerator> selectByExample(UserGeneratorCriteria example);

    UserGenerator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGenerator record, @Param("example") UserGeneratorCriteria example);

    int updateByExample(@Param("record") UserGenerator record, @Param("example") UserGeneratorCriteria example);

    int updateByPrimaryKeySelective(UserGenerator record);

    int updateByPrimaryKey(UserGenerator record);
    
    String selectPassByUsername(@Param("username")String username);
    
    int getUserId(@Param("username")String username);
    
    UserGenerator getUser(@Param("id")Integer id);
    
    //根据用户名选择用户
    UserGenerator selectUserByUsername(@Param("username")String username);
    
    String selectNameById(@Param("id") Integer id);
    
    void updateImg(@Param("img")String img,@Param("id")int id);
   
    String getuserImg(@Param("id")int id);

    int updateOrderForMoneyIn(@Param("id") int id,@Param("price") int price);
    
    int updateOrderForMoneyOut(@Param("id") int id,@Param("price") int price);
    
}