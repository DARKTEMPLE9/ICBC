package com.icbc.common.system_menu.menuMapper;

import com.icbc.entity.mapper.menu.TreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    List<TreeNode> getTreeList();

    List<TreeNode> getTreeParent(@Param("i") int i);

    TreeNode getParent(@Param("id") String id);

    List<TreeNode> getChildList(@Param("id") String id);
}

