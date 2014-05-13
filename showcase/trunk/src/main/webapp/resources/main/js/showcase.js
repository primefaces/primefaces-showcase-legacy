var winW=$(window).width();
var winH=$(window).height();
var whichMenuOpen="";

$(document).ready(function() {
	
	windowSizeChanged();
	
	// window resize detection
	$(window).bind("resize",windowSizeChanged);
		
	$("#MENUSIDE").perfectScrollbar({
		wheelSpeed: 40,
		suppressScrollX:true
	});
	/*$("#CONTENTSIDE").perfectScrollbar({
        wheelSpeed: 40,
        suppressScrollX: true
    });*/
	
	$(".SubMenuLinkContainer").slideUp(1).css({'visibility':'visible'});
	$(".mustClose").slideUp(1).css({'visibility':'visible'});
	
	// menu mouseenter & mouseleave actions start ----------------------------------
	$("#MENUSIDE").bind("mouseenter",function(){
		$(this).clearQueue();
        $(".MainLinkText").clearQueue();
		$(this).animate({'width':450},500,"easeInOutQuint",function(){$(".MainLinkText").animate({'opacity':1,'margin-left':50},100);});
		$("#LOGO").animate({'width':440},700,"easeInOutQuint");
        $(".hiddenLogoText").animate({'opacity':1},600);
		//$("#LOGOTEXTSIDE").animate({'margin-left':80},600,"easeInOutQuint");
		$("#CONTENTSIDE").animate({'opacity':0.6});
		$(".hiddenIcons").animate({'opacity':1},1000);
		
		$("#MENUSIDE").perfectScrollbar('update');
		$("#CONTENTSIDE").perfectScrollbar('update');
		$(".mustClose").slideDown(200);
	});
	$("#MENUSIDE").bind("mouseleave",function(){
		$(".MainLinkText").clearQueue();
		$(".MainLinkText").animate({'opacity':0,'margin-left':60},100);
		$(this).clearQueue();
		$(this).animate({'width':85},700,"easeInOutQuint",function(){$(".MainLinkText").animate({'opacity':0,'margin-left':60},300);});
		$("#LOGO").animate({'width':75},700,"easeInOutQuint");
        $(".hiddenLogoText").animate({'opacity':0},600);
		//$("#LOGOTEXTSIDE").animate({'margin-left':120},600,"easeInOutQuint");
		$("#CONTENTSIDE").animate({'opacity':1});
		$(".hiddenIcons").animate({'opacity':0},1000);
		
		$("#MENUSIDE").perfectScrollbar('update');
		$("#CONTENTSIDE").perfectScrollbar('update');
		
		$(".SubMenuLinkContainer").slideUp(700,"easeInOutQuint");
		$(".mustClose").slideUp(200);
	});
	// -------------------------------------------------------------
	
	$(window).bind('scroll', function () {
		num=90;
		if ($(window).scrollTop() > num) {
			$('.MOBILEHEADER').addClass('fixedTop');
		} else {
			$('.MOBILEHEADER').removeClass('fixedTop');
		}
	});
    
    // open theme switcher combo
    $("#themeSwitcher").on("click",function(){
		$(".themeSwitchList").slideDown(500);
	})
    .on("mouseleave",function(){
		$(".themeSwitchList").slideUp(1);
	});
    
    $("#GlobalThemeSwitcher > a").on("click", function(e) {
        var theme = $(this).data("theme");
        changeTheme([{name:'globaltheme', value:theme}]);
        PrimeFaces.changeTheme(theme);
        e.preventDefault();
    });
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

function windowSizeChanged(){
	winW=$(window).width();
	winH=$(window).height();
	
	$("#MENUSIDE").height(winH);
	$("#PFTopLinksCover").width(winW-120);
	$(".MOBILE #PFTopLinksCover").width(winW-48);
	//$("#CONTENTSIDE").height(winH);
	$("#CONTENTSIDE").width(winW-86);
	$("#SUBSUBMENU").height($("#CONTENTSIDEindent").scrollHeight);
	
	if(winW<850){
		$(".MOBILE").addClass("BeVisible");
		$(".PC").removeClass("BeVisible");
	}else{
		$(".MOBILE").removeClass("BeVisible");
		$(".PC").addClass("BeVisible");
	}
}

// open sub sub menu

function openSubSubMenu(whichSubMenu){
	$(".SubMenuLink").removeClass("openSubMenuLink");
	$(whichSubMenu).addClass("openSubMenuLink");
}