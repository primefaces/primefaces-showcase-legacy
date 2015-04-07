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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ChatUsers implements Serializable {
    
    private List<String> users;
    
    @PostConstruct
    public void init() {
        users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }
    
    public void remove(String user) {
        this.users.remove(user);
    }
    
    public void add(String user) {
        this.users.add(user);
    }
        
    public boolean contains(String user) {
        return this.users.contains(user);
    }
}
