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

import org.primefaces.PrimeFaces;
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
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#98AFC7', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#5C738B'}");
        model.setDefaultConnector(connector);
        
        Element computerA = new Element(new NetworkElement("Computer A", "computer-icon.png"), "10em", "6em");
        EndPoint endPointCA = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCA.setSource(true);
        computerA.addEndPoint(endPointCA);
        
        Element computerB = new Element(new NetworkElement("Computer B", "computer-icon.png"), "25em", "6em");
        EndPoint endPointCB = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCB.setSource(true);
        computerB.addEndPoint(endPointCB);
        
        Element computerC = new Element(new NetworkElement("Computer C", "computer-icon.png"), "40em", "6em");
        EndPoint endPointCC = createRectangleEndPoint(EndPointAnchor.BOTTOM);
        endPointCC.setSource(true);
        computerC.addEndPoint(endPointCC);
        
        Element serverA = new Element(new NetworkElement("Server A", "server-icon.png"), "15em", "24em");
        EndPoint endPointSA = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverA.setDraggable(false);
        endPointSA.setTarget(true);
        serverA.addEndPoint(endPointSA);
        
        Element serverB = new Element(new NetworkElement("Server B", "server-icon.png"), "35em", "24em");
        EndPoint endPointSB = createDotEndPoint(EndPointAnchor.AUTO_DEFAULT);
        serverB.setDraggable(false);
        endPointSB.setTarget(true);
        serverB.addEndPoint(endPointSB);
                        
        model.addElement(computerA);
        model.addElement(computerB);
        model.addElement(computerC);
        model.addElement(serverA);
        model.addElement(serverB);
    }
    
    public DiagramModel getModel() {
        return model;
    }
    
    public void onConnect(ConnectEvent event) {
        if(!suspendEvent) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connected", 
                    "From " + event.getSourceElement().getData()+ " To " + event.getTargetElement().getData());
        
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
            PrimeFaces.current().ajax().update("form:msgs");
        }
        else {
            suspendEvent = false;
        }
    }
    
    public void onDisconnect(DisconnectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Disconnected", 
                    "From " + event.getSourceElement().getData()+ " To " + event.getTargetElement().getData());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        PrimeFaces.current().ajax().update("form:msgs");
    }
    
    public void onConnectionChange(ConnectionChangeEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connection Changed", 
                    "Original Source:" + event.getOriginalSourceElement().getData() + 
                    ", New Source: " + event.getNewSourceElement().getData() + 
                    ", Original Target: " + event.getOriginalTargetElement().getData() + 
                    ", New Target: " + event.getNewTargetElement().getData());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        PrimeFaces.current().ajax().update("form:msgs");
        suspendEvent = true;
    }
    
    private EndPoint createDotEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setTarget(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");
        
        return endPoint;
    }
    
    private EndPoint createRectangleEndPoint(EndPointAnchor anchor) {
        RectangleEndPoint endPoint = new RectangleEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setSource(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");
        
        return endPoint;
    }
    
    public class NetworkElement implements Serializable {
        
        private String name;
        private String image;

        public NetworkElement() {
        }

        public NetworkElement(String name, String image) {
            this.name = name;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return name;
        }
        
    }
}