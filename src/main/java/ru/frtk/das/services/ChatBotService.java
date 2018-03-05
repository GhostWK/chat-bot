package ru.frtk.das.services;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static java.lang.String.format;

@Service
public class ChatBotService {
    private final Group groupChat;

    @Autowired
    public ChatBotService(Group groupChat) {
        this.groupChat = groupChat;
    }

    @PostConstruct
    public void registerMessageHandlers() {
        groupChat.onSimpleTextMessage(this::onTextMessage);
    }

    protected void onTextMessage(Message message) {
        new Message()
                .from(groupChat)
                .to(message.authorId())
                .text(format("Your message was: %s", message.getText()))
                .send();
    }
}
