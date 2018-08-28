package com.coffee.service;

import com.coffee.dao.CommonDAO;
import com.coffee.dao.impl.CommonDAOImpl;
import com.coffee.entity.Sort;

import java.util.List;

public class CommonService {
    CommonDAO commonDAO = new CommonDAOImpl();

    public CommonService(){

    }

    public double findPriceBySortName(String sortName){
        return commonDAO.findPriceBySortName(sortName);
    }

    public List<Sort> findAllSortNames(){
        return commonDAO.findAllSortNames();
    }
}
