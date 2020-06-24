$(document).click(function(el){
    let obj=$("<div class='box1'></div>").appendTo("body");
    obj.css({left:parseInt (el.pageX)-obj.width()+"px",top:parseInt (el.pageY)-obj.height()+"px"});
    obj.animate({
        top:"-"+el.pageY/2+"px",
        opacity:0
    },1000,function(){
        $(this).remove();
    })
 })