var menu1 = null;
var menu2 = null;
$(document).ready(function(){
  menu1 = parseInt($(".right").css("top").substring(0,$(".right").css("top").indexOf("px")));
  menu2 = $(window).height() - 96;
  
  $(window).scroll(function () { 
    var offset1 = menu1 + $(document).scrollTop() + "px";
    var offset2 = menu2 - $('.left .panel').height() + $(document).scrollTop() + "px";
    $('.right').animate({top:offset1},{duration:500,queue:false});
    $('.left').animate({top:offset2},{duration:1000,queue:false});
  });
  
  $('.panel ul li ul').hide();
  
    $('.panel ul li:has(ul) > a').click(function() {
        $(this).parent().find('ul').slideToggle();  
        return false;      
    });
   
    $('a.plus').click(function(){
       $(this).parent().next().find('ul li ul').slideUp('fast');
       return false; 
    });
    
    $('a.minus').click(function(){
       $(this).parent().next().find('ul li ul').slideDown('slow');
       return false; 
    });
    
});
/*function openImageWindow(src) {
    var image = new Image();
    image.src = src;
    var width = image.width;
    var height = image.height;
    window.open(src,"Image","width=" + width + ",height=" + height);
  }*/