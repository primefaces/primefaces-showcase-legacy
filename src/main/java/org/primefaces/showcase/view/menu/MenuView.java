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
package org.primefaces.showcase.view.menu;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuView {

    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                .withLabel("Dynamic Submenu")
                .build();

        DefaultMenuItem item = DefaultMenuItem.builder()
                .withValue("External")
                .withUrl("http://www.primefaces.org")
                .withIcon("ui-icon-home")
                .build();
        firstSubmenu.addElement(item);

        model.addElement(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
                .withLabel("Dynamic Actions")
                .build();

        item = DefaultMenuItem.builder()
                .withValue("Save")
                .withIcon("ui-icon-disk")
                .withCommand("#{menuView.save}")
                .withUpdate("messages")
                .build();
        secondSubmenu.addElement(item);

        item = DefaultMenuItem.builder()
                .withValue("Delete")
                .withIcon("ui-icon-close")
                .withCommand("#{menuView.delete}")
                .withAjax(false)
                .build();
        secondSubmenu.addElement(item);

        item = DefaultMenuItem.builder()
                .withValue("Redirect")
                .withIcon("ui-icon-search")
                .withCommand("#{menuView.redirect}")
                .build();
        secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);
    }

    public MenuModel getModel() {
        return model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
