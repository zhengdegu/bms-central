package com.gu.business.user.service.impl;

import com.gu.business.user.domain.Menu;
import com.gu.business.user.service.MenuService;
import com.gu.common.domain.dto.MenuDto;
import com.gu.common.domain.dto.MenuQueryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public List<MenuDto> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception {
        return null;
    }

    @Override
    public MenuDto findById(long id) {
        return null;
    }

    @Override
    public void create(Menu resources) {

    }

    @Override
    public void update(Menu resources) {

    }

    @Override
    public Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet) {
        return null;
    }

    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtos) {
        return null;
    }

    @Override
    public Object buildMenus(List<MenuDto> menuDtos) {
        return null;
    }

    @Override
    public Menu findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Set<Menu> menuSet) {

    }

    @Override
    public void download(List<MenuDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<MenuDto> getMenus(Long pid) {
        return null;
    }

    @Override
    public List<MenuDto> getSuperior(MenuDto menuDto, List<Menu> objects) {
        return null;
    }

    @Override
    public List<MenuDto> findByUser(Long currentUserId) {
        return null;
    }
}
