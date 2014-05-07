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
package org.primefaces.showcase.view.misc;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.showcase.domain.Theme;

@ManagedBean
public class ThemeSwitcherView {

    private List<Theme> themes;

    @PostConstruct
    public void init() {
        themes = new ArrayList<Theme>();
        themes.add(new Theme("Afterdark", "afterdark"));
        themes.add(new Theme("Afternoon", "afternoon"));
        themes.add(new Theme("Afterwork", "afterwork"));
        themes.add(new Theme("Aristo", "aristo"));
        themes.add(new Theme("Black-Tie", "black-tie"));
        themes.add(new Theme("Blitzer", "blitzer"));
        themes.add(new Theme("Bluesky", "bluesky"));
        themes.add(new Theme("Bootstrap", "bootstrap"));
        themes.add(new Theme("Casablanca", "casablanca"));
        themes.add(new Theme("Cupertino", "cupertino"));
        themes.add(new Theme("Cruze", "cruze"));
        themes.add(new Theme("Dark-Hive", "dark-hive"));
        themes.add(new Theme("Delta", "delta"));
        themes.add(new Theme("Dot-Luv", "dot-luv"));
        themes.add(new Theme("Eggplant", "eggplant"));
        themes.add(new Theme("Excite-Bike", "excite-bike"));
        themes.add(new Theme("Flick", "flick"));
        themes.add(new Theme("Glass-X", "glass-x"));
        themes.add(new Theme("Home", "home"));
        themes.add(new Theme("Hot-Sneaks", "hot-sneaks"));
        themes.add(new Theme("Humanity", "humanity"));
        themes.add(new Theme("Le-Frog", "le-frog"));
        themes.add(new Theme("Midnight", "midnight"));
        themes.add(new Theme("Mint-Choc", "mint-choc"));
        themes.add(new Theme("Overcast", "overcast"));
        themes.add(new Theme("Pepper-Grinder", "pepper-grinder"));
        themes.add(new Theme("Redmond", "redmond"));
        themes.add(new Theme("Rocket", "rocket"));
        themes.add(new Theme("Sam", "sam"));
        themes.add(new Theme("Smoothness", "smoothness"));
        themes.add(new Theme("South-Street", "south-street"));
        themes.add(new Theme("Start", "start"));
        themes.add(new Theme("Sunny", "sunny"));
        themes.add(new Theme("Swanky-Purse", "swanky-purse"));
        themes.add(new Theme("Trontastic", "trontastic"));
        themes.add(new Theme("UI-Darkness", "ui-darkness"));
        themes.add(new Theme("UI-Lightness", "ui-lightness"));
        themes.add(new Theme("Vader", "vader"));
    }
    
    public List<Theme> getThemes() {
        return themes;
    } 
}
