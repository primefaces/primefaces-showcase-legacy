/*
 * Copyright 2009-2017 PrimeTek.
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
package org.primefaces.showcase.view.fa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.showcase.domain.FontAwesomeIcon;

@ManagedBean
@ApplicationScoped
public class FontAwesomeView {

    public static final String BASE_URL = "https://use.fontawesome.com/releases/v5.0.1/";
    public static final String CSS = BASE_URL + "css/all.css";
    public static final String BRAND_SVG = BASE_URL + "/webfonts/fa-brands-400.svg";
    public static final Pattern KEY_VALUE_PAIRS = Pattern.compile("glyph-name=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");

    private List<FontAwesomeIcon> icons = new ArrayList<FontAwesomeIcon>();

    @PostConstruct
    public void init() {
        try {
            // gather all brands from the SVG
            List<String> brands = new ArrayList<String>();
            String svg = readUrl(BRAND_SVG);
            Matcher m = KEY_VALUE_PAIRS.matcher(svg);
            while (m.find()) {
                brands.add("fa-" + StringUtils.strip(m.group(1), "\""));
            }

            // load the CSS and find all icons marking either FAS or FAB
            String css = readUrl(CSS);
            boolean foundStart = false;
            String[] tokens = StringUtils.split(css, '{');
            for (String token : tokens) {
                String icon = StringUtils.trim(StringUtils.substringBetween(token, ".", ":"));
                if (!foundStart && StringUtils.equalsIgnoreCase(icon, "fa-500px")) {
                    foundStart = true;
                }

                if (!foundStart || !StringUtils.startsWithIgnoreCase(icon, "fa-")) {
                    continue;
                }

                String prefix = "fas";
                if (brands.contains(icon)) {
                    prefix = "fab";
                }

                FontAwesomeIcon faIcon = new FontAwesomeIcon();
                faIcon.setStylePrefix(prefix);
                faIcon.setIcon(icon);
                icons.add(faIcon);
            }
        }
        catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please try again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<FontAwesomeIcon> getIcons() {
        return icons;
    }

    public void setIcons(List<FontAwesomeIcon> icons) {
        this.icons = icons;
    }
    
    private String readUrl(String resourceUrl) throws IOException {
        StringBuilder builder = new StringBuilder();
        URL url = new URL(resourceUrl);
        // open the url stream, wrap it an a few "readers"
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append(System.lineSeparator());
        }
        // close our reader
        reader.close();
        return builder.toString();
    }

}
