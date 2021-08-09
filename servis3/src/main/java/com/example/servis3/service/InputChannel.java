package com.example.servis3.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {

    String CHANNEL_NAME = "simple_channel";

    @Input(CHANNEL_NAME)
    SubscribableChannel input();
}
