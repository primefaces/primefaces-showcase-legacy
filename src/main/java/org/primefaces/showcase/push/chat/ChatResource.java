/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.push.chat;

import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;

@PushEndpoint("/{room}/{user}")
@Singleton
public class ChatResource {

    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    @PathParam("room")
    private String room;

    @PathParam("user")
    private String username;

    @Inject
    private ServletContext ctx;

    @Inject
    private EventBus eventBus;

    @Inject
    private RemoteEndpoint endpoint;

    @OnOpen
    public void onOpen() {
        logger.info("onOpen {} for room {} and user {}", new String[]{endpoint.toString(), room, username});

        eventBus.publish(room + "/*", new Message(String.format("%s has entered the room '%s'",  username, room), true));
    }

    @OnClose
    public void onClose() {

        logger.info("onClose {} for room {} and user {}", new String[]{endpoint.toString(), room, username});

        ChatUsers users= (ChatUsers) ctx.getAttribute("chatUsers");
        users.remove(username);
        
        eventBus.publish(room + "/*", new Message(String.format("%s has left the room", username), true));
    }

    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }

}


