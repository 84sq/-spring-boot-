package org.sq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.sq.model.domain.SearchTrain;
import java.util.List;

@Mapper
public interface SearchTrainMapper {
    @Select("select * from search_train where id != '0'")
    public List<SearchTrain> getTrainCount();
}
