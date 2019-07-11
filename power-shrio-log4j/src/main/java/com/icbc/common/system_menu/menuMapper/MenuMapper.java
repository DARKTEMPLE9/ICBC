package com.icbc.common.system_menu.menuMapper;

import com.icbc.entity.mapper.menu.TreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    List<TreeNode> getTreeList();
}
