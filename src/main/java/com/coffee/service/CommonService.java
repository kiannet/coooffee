package com.coffee.service;

import com.coffee.dao.CommonDAO;
import com.coffee.dao.impl.CommonDAOImpl;

import java.util.List;

public class CommonService {
    CommonDAO commonDAO = new CommonDAOImpl();

    public CommonService(){

    }

    public double findPriceBySortName(String sortName){
        return commonDAO.findPriceBySortName(sortName);
    }

    public List<String> findAllSortNames(){
        return commonDAO.findAllSortNames();
    }
}
