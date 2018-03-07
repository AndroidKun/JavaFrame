package com.kennor.demo.service;

import com.kennor.demo.common.PageResult;
import com.kennor.demo.dao.BaseDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T> implements InitializingBean {
    protected BaseDao<T> baseDao;

    public abstract void initDao();
    @Override
    public void afterPropertiesSet() throws Exception {
        initDao();
    }

    @Transactional(readOnly=false)
    public T saveOrUpdate(T t){
        return baseDao.save(t);
    }
    @Transactional(readOnly=true)
    public Long count(T t){
        if(t == null){
            return baseDao.count();
        }else{
            Example<T> example = Example.of(t);
            return baseDao.count(example);
        }
    }
    @Transactional(readOnly=true)
    public  List<T> findAll(T t){
        if(t == null){
            return baseDao.findAll();
        }else {
            Example<T> example = Example.of(t);
            return baseDao.findAll(example);
        }
    }
    @Transactional(readOnly=true)
    public  PageResult<T> findByPage(T t,Integer page,Integer size){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setSize(size);
        if(t == null){
            PageRequest pageRequest = new PageRequest(page,size);
            Page<T> all = baseDao.findAll(pageRequest);
            pageResult.setData(all.getContent());
            pageResult.setTotalPage(all.getTotalPages());
        }else {
            Example<T> example = Example.of(t);
            PageRequest pageRequest = new PageRequest(page, size);
            Page<T> all = baseDao.findAll(example, pageRequest);
            pageResult.setData(all.getContent());
            pageResult.setTotalPage(all.getTotalPages());
        }
        return pageResult;
    }

    @Transactional(readOnly=true)
    public  PageResult<T> findByPage(T t,Integer page,Integer size,Sort sort){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setSize(size);
        if(t == null){
            PageRequest pageRequest = new PageRequest(page,size,sort);
            Page<T> all = baseDao.findAll(pageRequest);
            pageResult.setData(all.getContent());
            pageResult.setTotalPage(all.getTotalPages());
        }else {
            Example<T> example = Example.of(t);
            PageRequest pageRequest = new PageRequest(page, size, sort);
            Page<T> all = baseDao.findAll(example, pageRequest);
            pageResult.setData(all.getContent());
            pageResult.setTotalPage(all.getTotalPages());
        }
        return pageResult;
    }

}
