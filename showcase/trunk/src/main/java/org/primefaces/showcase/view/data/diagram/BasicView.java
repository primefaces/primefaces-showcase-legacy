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
import org.primefaces.model.diagram.ElementConnection;
import org.primefaces.model.diagram.endpoint.DefaultEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

@ManagedBean(name = "diagramBasicView")
@RequestScoped
public class BasicView {
    
    private DiagramModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        
        Element elementA = new Element("A", "20em", "6em");
        elementA.addEndPoint(new DefaultEndPoint(EndPointAnchor.BOTTOM));
        
        Element elementB = new Element("B", "10em", "18em");
        elementB.addEndPoint(new DefaultEndPoint(EndPointAnchor.TOP));
        
        Element elementC = new Element("C", "40em", "18em");
        elementC.addEndPoint(new DefaultEndPoint(EndPointAnchor.TOP));
                        
        model.addElement(elementA);
        model.addElement(elementB);
        model.addElement(elementC);
        
        model.connect(new ElementConnection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));        
        model.connect(new ElementConnection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
    }
    
    public DiagramModel getModel() {
        return model;
    }
}
