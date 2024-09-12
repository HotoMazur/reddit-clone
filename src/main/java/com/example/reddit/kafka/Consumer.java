package com.example.reddit.kafka;

import com.example.reddit.dto.UserDto;
import com.example.reddit.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Producer.class);

  private final UserService userService;

  @KafkaListener(topics = "users", groupId = "group_id")
  public void userConsume(ConsumerRecord<String, String > record) throws JsonProcessingException {
    String key = record.key();
    String message = record.value();

    ObjectMapper objectMapper = new ObjectMapper();
    UserDto user = objectMapper.readValue(message, UserDto.class);

    if (key.equals("CREATE_USER")) {
      userService.addUser(user);
    }

    if (key.equals("UPDATE_USER")){
      userService.updateUser(user, user.getId());
    }

    logger.info(String.format("#### -> Consumed message -> Key: %s, Value: %s", key, message));
  }
}
