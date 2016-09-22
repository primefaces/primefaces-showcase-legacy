/*
 * Copyright 2009-2016 PrimeTek.
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
package org.primefaces.showcase.view.data.timeline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.timeline.TimelineUpdater;
import org.primefaces.event.timeline.TimelineDragDropEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
import org.primefaces.showcase.domain.Event;

@ManagedBean(name = "dndTimelineView")
@ViewScoped
public class DndTimelineView implements Serializable {

    private TimelineModel model;

    private Date start;
    private Date end;
    
    private List<Event> events = new ArrayList<Event>();

    @PostConstruct
    public void init() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date now = new Date();

        cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);
        start = cal.getTime();

        cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 1000);
        end = cal.getTime();

        model = new TimelineModel();

        for (int i = 1; i <= 10; i++) {
            events.add(new Event("Event " + i));
        }

    }

    public void onDrop(TimelineDragDropEvent e) {
        // get dragged model object (event class) if draggable item is within a data iteration component,
        // update event's start and end dates.
        Event dndEvent = (Event) e.getData();
        dndEvent.setStart(e.getStartDate());
        dndEvent.setEnd(e.getEndDate());

        // create a timeline event (not editable)
        TimelineEvent event = new TimelineEvent(dndEvent, e.getStartDate(), e.getEndDate(), false, e.getGroup());

        // add a new event
        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance("timeline");
        model.add(event, timelineUpdater);

        // remove from the list of all events
        events.remove(dndEvent);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The " + dndEvent.getName() + " was added", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSwitchTimeZone(AjaxBehaviorEvent e) {
        model.clear();
    }

    public TimelineModel getModel() {
        return model;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
