var winW=$(window).width();
var winH=$(window).height();
var whichMenuOpen="";

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
            this.submenus.slideUp(700,"easeInOutQuint");
            this.searchInput.slideUp(200);
        },
        
        onWinResize: function() {
            var win = $(window),
            winW = win.width(),
            winH = win.height();

            this.menu.height(winH);
            this.topLinksCover.width(winW-120);
            this.content.width(winW-86);
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
});

// Open Submenu
function openSubMenu(whichBtn){	
    $(".MenuSideMainLink").removeClass("MenuSideMainLinkDark");
    $(whichBtn).addClass("MenuSideMainLinkDark");

    if(whichMenuOpen==whichBtn){
        $(".SubMenuLinkContainer").slideUp(700,"easeInOutQuint");
        $(whichBtn).next().slideUp(700,"easeInOutQuint");
        whichMenuOpen="";
    }else{
        $(".SubMenuLinkContainer").slideUp(700,"easeInOutQuint");
        $(whichBtn).next().slideDown(700,"easeInOutQuint");
        whichMenuOpen=whichBtn;
    }
}

// open sub sub menu

function openSubSubMenu(whichSubMenu){
	$(".SubMenuLink").removeClass("openSubMenuLink");
	$(whichSubMenu).addClass("openSubMenuLink");
}