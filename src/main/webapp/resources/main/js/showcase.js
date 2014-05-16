var winW=$(window).width();
var winH=$(window).height();

$(document).ready(function() {
    
    var Showcase = {
    
        menu: $('#MENUSIDE'),
        
        content: $('#CONTENTSIDE'),
        
        categoryLinks: $(".MainLinkText"),
        
        menuVisible: false,
        
        hiddenLogo: $("#hiddenLogoTextEl"),
        
        logo: $('#LOGO'),
        
        hiddenCategoryIcons: $('.hiddenIcons'),
        
        submenus: $('.SubMenuLinkContainer'),
        
        searchInput: $('#menuSearch'),
        
        topLinksCover: $('#PFTopLinksCover'),
        
        contentSideIndent: $('#CONTENTSIDEindent'),
                
        desktopContainer: $(document.body).children('.PC'),
        
        mobileContainer: $(document.body).children('.MOBILE'),
        
        activeMenu: null,
        
        activeSubSubMenu: null,

        openMenu: function() {
            var $this = this;
            this.menuVisible = true;
            this.menu.clearQueue();
            this.categoryLinks.clearQueue();
            this.menu.animate({'width':450},500,"easeInOutQuint", function(){
                $this.menu.perfectScrollbar('update');
                $this.categoryLinks.animate({'opacity':1,'margin-left':50},100);
                $this.hiddenLogo.fadeIn(100);
                $this.searchInput.slideDown(200);
            });
            this.logo.animate({'width':440},700,"easeInOutQuint");
            this.content.animate({'opacity':0.6});
            this.hiddenCategoryIcons.animate({'opacity':1},1000);
        },

        hideMenu: function() {
            var $this = this;
            this.menuVisible = false;
            this.menu.clearQueue();
            this.categoryLinks.clearQueue();
            this.menu.clearQueue();
            this.menu.animate({'width':85},700,"easeInOutQuint", function(){
                $this.menu.perfectScrollbar('update');
            });
            this.logo.animate({'width':75},700,"easeInOutQuint");
            $this.hiddenLogo.fadeOut(100);
            $this.categoryLinks.animate({'opacity':0,'margin-left':60},100);
            this.content.animate({'opacity':1});
            this.hiddenCategoryIcons.animate({'opacity':0},1000);
            this.searchInput.slideUp(200);
            this.hideSubMenus();
        },
        
        onWinResize: function() {
            var win = $(window),
            winW = win.width(),
            winH = win.height();

            this.menu.height(winH);
            //this.topLinksCover.width(winW-120);
            //this.content.width(winW-86);
        },
        
        hideSubMenus: function() {
            if(this.activeMenu) {
                $(this.activeMenu).removeClass('MenuSideMainLinkDark').next().hide();
                this.activeMenu = null;
            }
            
            if(this.activeSubSubMenu) {
                $(this.activeSubSubMenu).removeClass('openSubMenuLink');
                this.activeSubSubMenu = null;
            }
        },
        
        openSubMenu: function(header) {
            var headerJQ = $(header);
            
            if(this.activeSubSubMenu) {
                $(this.activeSubSubMenu).removeClass("openSubMenuLink");
                this.activeSubSubMenu = null;
            }
            
            if(this.activeMenu) {
                if(this.activeMenu === header) {
                    headerJQ.removeClass('MenuSideMainLinkDark').next().slideUp(700,"easeInOutQuint");
                    this.activeMenu = null;
                }
                else {
                    $(this.activeMenu).removeClass('MenuSideMainLinkDark').next().slideUp(700,"easeInOutQuint");
                    headerJQ.addClass("MenuSideMainLinkDark").next().slideDown(700,"easeInOutQuint");
                    this.activeMenu = header;
                }
            }
            else {
                headerJQ.addClass("MenuSideMainLinkDark").next().slideDown(700,"easeInOutQuint");
                this.activeMenu = header;
            }
        },
        
        openSubSubMenu: function(submenuLink){
            if(this.activeSubSubMenu) {
                if(this.activeSubSubMenu !== submenuLink) {
                    $(this.activeSubSubMenu).removeClass("openSubMenuLink");
                    $(submenuLink).addClass("openSubMenuLink");
                    this.activeSubSubMenu = submenuLink;
                }
            }
            else {
                $(submenuLink).addClass("openSubMenuLink");
                this.activeSubSubMenu = submenuLink;
            }
        }
    };
	
	Showcase.onWinResize();
	$(window).on("resize", function() {
        Showcase.onWinResize();
    });
		
	Showcase.menu.perfectScrollbar({
		wheelSpeed: 40,
		suppressScrollX:true
	});
    	
	// menu mouseenter & mouseleave actions start ----------------------------------
	Showcase.menu.on("mouseenter", function() {
        clearTimeout(Showcase.menuHideTimeout);
        Showcase.menuShowTimeout = setTimeout(function() {
            if(!Showcase.menuVisible) {
                Showcase.openMenu();
            }
        }, 250);
	})
	.on("mouseleave", function() {
        clearTimeout(Showcase.menuShowTimeout);
        Showcase.menuHideTimeout = setTimeout(function() {
            if(Showcase.menuVisible) {
                Showcase.hideMenu();
            };
        }, 250);
	});
    
    // open theme switcher combo
    $("#themeSwitcher").on("click",function(){
		$("#GlobalThemeSwitcher").slideDown(500);
	})
    .on("mouseleave",function(){
		$("#GlobalThemeSwitcher").slideUp(1);
	});
    
    $("#GlobalThemeSwitcher > a").on("click", function(e) {
        var theme = $(this).data("theme");
        changeTheme([{name:'globaltheme', value:theme}]);
        PrimeFaces.changeTheme(theme);
        e.preventDefault();
    });
    
    // open theme switcher combo
    $("#pushNav").on("click",function(){
		$("#PushDemos").slideDown(500);
	})
    .on("mouseleave",function(){
		$("#PushDemos").slideUp(1);
	});
    
    var sourceTabview = $('#SourceContentSide > div > span > span > div.ui-tabs'),
    lastTabHeader = sourceTabview.find('> ul > li:last');
    if(lastTabHeader.hasClass('tab-doc')) {
        lastTabHeader.one('click.load', function() {
            var classes = lastTabHeader.attr('class').split(' '),
            slide = 0;

            for(var i = 0; i < classes.length; i++) {
                if(classes[i].indexOf('docslide-') === 0) {
                    slide = classes[i].split('-')[1];
                }
            }

            var content = sourceTabview.find('> .ui-tabs-panels > div:last ');
            content.html('<iframe frameborder="0" class="speakerdeck-iframe" style="border: 0px none; background: none repeat scroll 0% 0% transparent; margin: 0px; padding: 0px; width: 100%; height: 1440px;" src="http://speakerdeck.com/player/9d21c4d0bcc50131faee127b1bf32aaa?slide=' + slide +'"  allowfullscreen="true" mozallowfullscreen="true" webkitallowfullscreen="true"></iframe>');
        });

    }
    
    // Search ---------------------------------------
    Showcase.searchInput.on('keyup', function(e) {
        Showcase.hideSubMenus();
        
        if (e.keyCode === 32) {
            $(this).val($(this).val()+" ");
        }
        var searchValue = $(this).val().toLowerCase(),
        matchSub = false,
        matchSubSub = false, 
        matchMenuSide = false;

        $('.SubMenuLinkContainer').each(function() {
            var MenuSideValue = $(this).prev().children('span').text().trim().toLowerCase(),
            itemValue;
            
            if(MenuSideValue.search(searchValue) < 0 || searchValue.length === 0) {  
                var Sub = $(this).children(':not(span)'),
                SubSub = $(this).children('span');

                for(var i = 0; i < Sub.length; i++) {     //for SubMenu
                    itemValue = Sub.eq(i).text().trim().toLowerCase();
                    if(itemValue.search(searchValue) >= 0) {
                        Sub.eq(i).show();
                        matchSub = true;
                    }
                    else{
                        Sub.eq(i).hide();
                    }
                }
                
                for(var j = 0; j < SubSub.length; j++) {  //for SubsubMenu
                    var SubSuba = SubSub.eq(j).find('a');
                    for(var m = 0; m < SubSuba.length; m++) {
                        itemValue = SubSuba.eq(m).text().toLowerCase();
                        if(itemValue.search(searchValue) >= 0) {
                            SubSuba.eq(m).show();
                            matchSubSub = true; 
                            matchMenuSide = true;
                        }
                        else {
                            SubSuba.eq(m).hide();
                        }
                    }
                    
                    if(matchSubSub) {
                        SubSub.eq(j).show();
                        matchSubSub = false;
                    }
                    else {
                        SubSub.eq(j).hide();
                    }
                }
                
                if(matchSub || matchMenuSide ) {
                    $(this).prev().show();
                    matchSub = false;
                    matchMenuSide = false;
                }
                else {
                    $(this).prev().hide();
                }
            }
            else {
                $(this).children().show();
                $(this).prev().show();
            }
        });
   });
   
   window.Showcase = Showcase;
});