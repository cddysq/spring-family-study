package com.spring.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2019/12/10 20:11
 * @Description: 父接口
 */
@NoRepositoryBean
public interface BaseRepository<T,Long> extends PagingAndSortingRepository<T,Long> {
    /**
     * 根据订单和更新时间查找前3名
     * @return 前三名数据列表
     */
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
