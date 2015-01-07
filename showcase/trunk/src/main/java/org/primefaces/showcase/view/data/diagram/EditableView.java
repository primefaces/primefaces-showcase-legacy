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

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.diagram.ConnectEvent;
import org.primefaces.event.diagram.ConnectionChangeEvent;
import org.primefaces.event.diagram.DisconnectEvent;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.endpoint.RectangleEndPoint;
import org.primefaces.model.diagram.overlay.ArrowOverlay;

@ManagedBean(name = "diagramEditableView")
@ViewScoped
public class EditableView implements Serializable {
    
    private DefaultDiagramModel model;
    
    private boolean suspendEvent;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        
        model.getDefaultConnectionOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        model.setDefaultConnector(new StraightConnector());
        
        Element elementA = new Element("A", "25em", "6em");
        EndPoint endPointA = new RectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointA.setSource(true);
        elementA.addEndPoint(endPointA);
        
        Element elementB = new Element("B", "10em", "18em");
        EndPoint endPointB1 = new DotEndPoint(EndPointAnchor.LEFT);
        endPointB1.setTarget(true);
        elementB.addEndPoint(endPointB1);
        
        EndPoint endPointB2 = new RectangleEndPoint(EndPointAnchor.RIGHT);
        endPointB2.setSource(true);
        elementB.addEndPoint(endPointB2);
        
        Element elementC = new Element("C", "40em", "18em");
        EndPoint endPointC = new DotEndPoint(EndPointAnchor.LEFT);
        endPointC.setTarget(true);
        elementC.addEndPoint(endPointC);
                        
        model.addElement(elementA);
        model.addElement(elementB);
        model.addElement(elementC);
    }
    
    public DiagramModel getModel() {
        return model;
    }
    
    public void onConnect(ConnectEvent event) {
        if(!suspendEvent) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connected", 
                    "From " + event.getSourceElement().getData()+ " To " + event.getTargetElement().getData());
        
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
            RequestContext.getCurrentInstance().update("form:msgs");
        }
        else {
            suspendEvent = false;
        }
    }
    
    public void onDisconnect(DisconnectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Disconnected", 
                    "From " + event.getSourceElement().getData()+ " To " + event.getTargetElement().getData());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        RequestContext.getCurrentInstance().update("form:msgs");
    }
    
    public void onConnectionChange(ConnectionChangeEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connection Changed", 
                    "Original Source:" + event.getOriginalSourceElement().getData() + 
                    ", New Source: " + event.getNewSourceElement().getData() + 
                    ", Original Target: " + event.getOriginalTargetElement().getData() + 
                    ", New Target: " + event.getNewTargetElement().getData());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        RequestContext.getCurrentInstance().update("form:msgs");
        suspendEvent = true;
    }
}