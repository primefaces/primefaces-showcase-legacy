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
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DefaultEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

@ManagedBean(name = "diagramEditableView")
@RequestScoped
public class EditableView {
    
    private DiagramModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        
        model.setDefaultConnector(new StraightConnector());
        
        Element elementA = new Element("A", "20em", "6em");
        EndPoint endPointA = new DefaultEndPoint(EndPointAnchor.BOTTOM);
        endPointA.setSource(true);
        elementA.addEndPoint(endPointA);
        
        Element elementB = new Element("B", "10em", "18em");
        EndPoint endPointB1 = new DefaultEndPoint(EndPointAnchor.RIGHT);
        endPointB1.setTarget(true);
        elementB.addEndPoint(endPointB1);
        
        EndPoint endPointB2 = new DefaultEndPoint(EndPointAnchor.LEFT);
        endPointB2.setSource(true);
        elementB.addEndPoint(endPointB2);
        
        Element elementC = new Element("C", "40em", "18em");
        EndPoint endPointC = new DefaultEndPoint(EndPointAnchor.LEFT);
        endPointC.setSource(true);
        endPointC.setTarget(true);
        elementC.addEndPoint(endPointC);
                        
        model.addElement(elementA);
        model.addElement(elementB);
        model.addElement(elementC);
    }
    
    public DiagramModel getModel() {
        return model;
    }
}
