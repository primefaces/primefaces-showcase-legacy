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
package org.primefaces.showcase.push.checkin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@RequestScoped
public class CheckinView {
    
    private Checkin checkin;

    @PostConstruct
    public void init() {
        checkin = new Checkin();
    }
    
    public Checkin getCheckin() {
        return checkin;
    }
    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }
    
    public void publish() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/checkin", checkin);
    }
    
}
