package org.sq.service;
import org.sq.model.domain.Train;
import java.util.List;

public interface ITrainService {
    public List<Train> getFindList();

    public List<Train> getTrainMessageList();

//    public Train getTrainListWithTrainNum(String trainNumber);

    // 根据车辆起始点，终点，日期查询车辆信息
    public List<Train> selectTrainWithTrainMessage(String start,String end,String date);

    // 根据车辆编号更新车辆信息
    public int updateTrain(String trainNumber, String start, String end, String date, String time, int prize);

    //  增加车辆信息
    public int insertTrain(String trainNumber, String start,String end,
                           String date, String time, int prize);

    // 根据车辆编号删除车辆信息
    public int deleteTrain(String TrainNumber);
}

