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
package org.primefaces.showcase.view.data.diagram;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

@ManagedBean(name = "diagramStateMachineView")
@RequestScoped
public class StateMachineView {
    
    private DefaultDiagramModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        
        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);
        
        Element start = new Element(null, "15em", "5em");
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        start.setStyleClass("start-node");
        
        Element idle = new Element("Idle", "10em", "20em");
        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
        
        Element turnedOn = new Element("TurnedOn", "10em", "35em");
        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                
        Element activity = new Element("Activity", "45em", "35em");
        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP_RIGHT));
        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP_LEFT));
                                
        model.addElement(start);
        model.addElement(idle);
        model.addElement(turnedOn);
        model.addElement(activity);
                
        model.connect(createConnection(start.getEndPoints().get(0), idle.getEndPoints().get(0), null));
        model.connect(createConnection(idle.getEndPoints().get(1), turnedOn.getEndPoints().get(0), "Turn On"));
        model.connect(createConnection(turnedOn.getEndPoints().get(0), idle.getEndPoints().get(2), "Turn Off"));
        model.connect(createConnection(turnedOn.getEndPoints().get(1), activity.getEndPoints().get(0), null));
        model.connect(createConnection(activity.getEndPoints().get(1), turnedOn.getEndPoints().get(2), "Request Turn Off"));
        model.connect(createConnection(activity.getEndPoints().get(2), activity.getEndPoints().get(2), "Talk"));
        model.connect(createConnection(activity.getEndPoints().get(3), activity.getEndPoints().get(3), "Run"));
        model.connect(createConnection(activity.getEndPoints().get(4), activity.getEndPoints().get(4), "Walk"));
    }
    
    public DiagramModel getModel() {
        return model;
    }
    
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        
        return conn;
    }
}
