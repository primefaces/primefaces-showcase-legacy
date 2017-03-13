package org.primefaces.showcase.view.input;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
public class ChipsView {

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }

    public void onItemUnSelect(UnselectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Removed", event.getObject().toString()));
    }

}
