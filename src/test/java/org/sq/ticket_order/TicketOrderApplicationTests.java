package org.sq.ticket_order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sq.mapper.SearchTrainMapper;
import org.sq.mapper.TrainMapper;
import org.sq.mapper.UserMapper;

@SpringBootTest
class TicketOrderApplicationTests {

    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private SearchTrainMapper searchTrainMapper;

    @Test
    void contextLoads() {
        System.out.println(trainMapper.getTrainMessageList().size());
        System.out.println("1111111111111111111111111111111");
        System.out.println(searchTrainMapper.getTrainCount().size());
    }

}
