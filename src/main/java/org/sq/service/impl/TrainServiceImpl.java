package org.sq.service.impl;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sq.mapper.SearchTrainMapper;
import org.sq.mapper.TrainMapper;
import org.sq.model.domain.SearchTrain;
import org.sq.model.domain.Train;
import org.sq.service.ITrainService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements ITrainService {
    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private SearchTrainMapper searchTrainMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Train> getFindList() {
        List<SearchTrain> searchTrains = searchTrainMapper.getTrainCount();
        List<Train> trains = new ArrayList<>();
        for (int i = 0; i < searchTrains.size(); i++) {
            Train train = trainMapper.getTrainList(searchTrains.get(i).getId());
            train.setStart(searchTrains.get(i).getStart());
            train.setEnd(searchTrains.get(i).getEnd());
            train.setDate(searchTrains.get(i).getDate());
            trains.add(train);
        }
        return trains;
    }

    @Override
    public List<Train> getTrainMessageList() {
//        List<Train> train = null;
//        Object o = redisTemplate.opsForValue().get("trainMessage");
//        if (o != null) {
//            train = (List<Train>) o;
//        } else {
//            train = trainMapper.getTrainMessageList();
//            if (train != null) {
//                redisTemplate.opsForValue().set("trainMessage", train);
//            }
//        }
//        return train;
        List<Train> trainList = trainMapper.getTrainMessageList();
        return trainList;
    }

//    @Override
//    public Train getTrainListWithTrainNum(String trainNumber) {
//        Train train = null;
//        Object o = redisTemplate.opsForValue().get("trainNumber_" + trainNumber);
//        if (o != null) {
//            train = (Train) o;
//        } else {
//            train = trainMapper.getTrainListWithTrainNum(trainNumber);
//            if (train != null) {
//                redisTemplate.opsForValue().set("trainNumber_" + trainNumber, train);
//            }
//        }
//        return train;
//
//
////        Train trainListWithTrainNum = trainMapper.getTrainListWithTrainNum(trainNumber);
////        return trainListWithTrainNum;
//    }

    // 根据车辆起始点，终点，日期查询车辆信息
    @Override
    @Cacheable(cacheNames = "trainMessage")
    public List<Train> selectTrainWithTrainMessage(String start, String end, String date) {
        List<Train> trains = trainMapper.selectTrain(start, end, date);
        List<Train> trainList = new ArrayList<>();
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            trains.get(i).setTrainNumber(train.getTrainNumber());
            train.setStart(trains.get(i).getStart());
            train.setEnd(trains.get(i).getEnd());
            train.setDate(trains.get(i).getDate());
            train.setTime(trains.get(i).getTime());
            train.setPrize(trains.get(i).getPrize());
            trainList.add(train);
        }
        return trainList;
    }

    @Override
    @Cacheable(cacheNames = "trainUpdate")
    public int updateTrain(String trainNumber, String start, String end, String date, String time, int prize) {
        int train = trainMapper.updateTrain(trainNumber, start, end, date, time, prize);
        return train;
    }

    // 根据车辆编号更新车辆发车时间

    //    public int updateTrain(String trainNumber,String start,String end,
//                           String date, String time, int prize){
//        int train1 = trainMapper.updateTrain(trainNumber,start,end,date,time,prize);
//        return train1;
//
//    }

    // 增加车辆信息
    @Override
    @CachePut(cacheNames = "trainAdd")
    public int insertTrain(String trainNumber, String start, String end,
                           String date, String time, int prize) {
        int i = trainMapper.insertTrain(trainNumber, start, end, date, time, prize);
        return i;
    }

    // 根据车辆编号删除车辆信息
    @Override
//    @CacheEvict(cacheNames = "trainDelete")
    public int deleteTrain(String trainNumber) {
        int i = trainMapper.deleteTrain(trainNumber);
        redisTemplate.delete("train_" + trainNumber);
        return i;
    }
}
