package com.demo.dao;

import com.demo.entity.Function;
import com.demo.entity.vo.MenuVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

public interface IFunctionMapper {

    int deleteByPrimaryKey(Integer funcId);


    int insert(Function record);


    Function selectByPrimaryKey(Integer funcId);


    List<Function> selectAll();


    int updateByPrimaryKey(Function record);

	List<Function> findMenu(@Param("userId") int userId);

	Function findByFuncName(String funcName);


	List<Function> findByParentId(int parentId);


	List<Function> findTreeByParentId(@Param("parentId") String id);
}