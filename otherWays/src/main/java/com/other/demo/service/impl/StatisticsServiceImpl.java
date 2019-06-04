package com.other.demo.service.impl;

import com.other.demo.dao.StatisticsDao;
import com.other.demo.service.StatisticsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = Logger.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public int trainsNum() {
        return statisticsDao.trainsNum();
    }

    @Override
    public int userNum() {
        return statisticsDao.userNum();
    }

    @Override
    public int userTypeNum(int type) {
        return statisticsDao.userTypeNum(type);
    }

    @Override
    public int indentNum() {
        return statisticsDao.indentNum();
    }

    @Override
    public int indentTypeNumIsOk(int type) {
        return statisticsDao.indentTypeNum(type);
    }
}
