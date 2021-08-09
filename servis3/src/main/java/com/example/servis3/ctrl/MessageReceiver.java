package com.example.servis3.ctrl;

import com.example.servis3.dto.MsgDto;
import com.example.servis3.service.InputChannel;
import com.example.servis3.service.MsgHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@MessageEndpoint
@EnableBinding(InputChannel.class)
@RequiredArgsConstructor
public class MessageReceiver {

    private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private final MsgHandler msgHandler;



    @StreamListener(InputChannel.CHANNEL_NAME)
    public void receive(MsgDto msgDto) {
        msgHandler.doSomething(msgDto);
    }
}
