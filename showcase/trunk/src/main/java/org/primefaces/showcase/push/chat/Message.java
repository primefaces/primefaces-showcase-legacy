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

public class Message {
    
    private String text;
    private String user;
    private boolean updateList;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }
    
    public Message(String text, boolean updateList) {
        this.text = text;
        this.updateList = updateList;
    }

    public Message(String user, String text, boolean updateList) {
        this.text = text;
        this.user = user;
        this.updateList = updateList;
    }
    
    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Message setUser(String user) {
        this.user = user;
        return this;
    }

    public boolean isUpdateList() {
        return updateList;
    }

    public void setUpdateList(boolean updateList) {
        this.updateList = updateList;
    }
}

