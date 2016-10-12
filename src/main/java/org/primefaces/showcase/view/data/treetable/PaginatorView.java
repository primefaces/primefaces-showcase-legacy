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
package org.primefaces.showcase.view.data.treetable;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.showcase.domain.Document;

@ManagedBean(name="ttPaginatorView")
@ViewScoped
public class PaginatorView implements Serializable {
    
    private TreeNode root;
            
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode();
        
        for(int i = 0; i < 50; i++) {
            TreeNode node = new DefaultTreeNode(new Document("Node " + i, String.valueOf((int) (Math.random() * 1000)), "Document"), root);
            
            for(int j = 0; j < 5; j++) {
                new DefaultTreeNode(new Document("Node " + i + "." + j, String.valueOf((int) (Math.random() * 1000)), "Document"), node);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }
}
