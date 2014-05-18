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
package org.primefaces.showcase.mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@ManagedBean
public class TranslateView {
    
    private final static String TRANSLATE_URL_KEY = "translate.url";
    private final static String BUNDLE_NAME = "translate";
    
    private String translateURL;
    private String from;
    private String to;
    private String text;
    private String result;
    private Map<String,String> languages;

    @PostConstruct
    public void init() {
        languages = new LinkedHashMap<String, String>();
        languages.put("English", "en");
        languages.put("Turkish", "tr");
        languages.put("Italian", "it");
        languages.put("Spanish", "es");
        languages.put("German", "de");
        languages.put("French", "fr");
        languages.put("Portuguese", "pt");
        
        Map<String,Object> app = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        translateURL = (String) app.get(TRANSLATE_URL_KEY);
        
        if(translateURL == null) {
            translateURL = ResourceBundle.getBundle(BUNDLE_NAME).getString(TRANSLATE_URL_KEY);
            app.put(TRANSLATE_URL_KEY, translateURL);
        }
    }
    
    public void translate() throws Exception {        
        String url = translateURL + "&lang=" + from + "-" + to + "&text=" + text;
        String response = getResponse(url);
        JSONObject json = new JSONObject(response);
        JSONArray jsonArray = json.getJSONArray("text");
        result = jsonArray.getString(0);
    }
    
    private String getResponse(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        return response.toString();
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
