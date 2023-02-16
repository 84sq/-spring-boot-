package org.sq.mapper;

import org.apache.ibatis.annotations.*;
import org.sq.model.domain.Train;

import java.util.List;

@Mapper
public interface TrainMapper {
    @Select("select * from search_train where id = ${id}")
    public Train getTrainList(int id);

    @Select("select * from train_message")
    public List<Train> getTrainMessageList();

    @Select("select * from train_message where train_number = '${trainNumber}'")
    public Train getTrainListWithTrainNum(String trainNumber);

//    按起始地、终点和日期寻找车辆信息
    @Select("select * from train_message where start = '${start}' and end = '${end}' and date = '${date}' ")
    public List<Train> selectTrain(@Param("start") String start, @Param("end") String end, @Param("date") String date);

//    根据车辆编号更新车辆信息
    @Update("update train_message set start = '${start}',end = '${end}',date = '${date}',time = '${time}',prize = ${prize} " +
            "where train_number = '${trainNumber}'")
    public int updateTrain(@Param("trainNumber") String trainNumber,@Param("start") String start,@Param("end") String end,
                             @Param("date") String date,@Param("time") String time,@Param("prize") int prize);

//    增加车辆信息
    @Insert("insert into train_message (train_number,start,end,date,time,prize) values ('${trainNumber}','${start}','${end}','${date}','${time}',${prize})")
    public int insertTrain(@Param("trainNumber") String trainNumber,@Param("start") String start,@Param("end") String end,
                           @Param("date") String date,@Param("time") String time,@Param("prize") int prize);

//    根据车辆编号删除车辆信息
    @Delete("delete from train_message where train_number = '${trainNumber}'")
    public int deleteTrain(String trainNumber);

}
