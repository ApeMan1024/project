$(function(){
    $(document).click(function(e){
        var x=e.pageX
        var y=e.pageY;
        $("<div class='box'></div>").appendTo("body");  
        $(".box").css({left:(x-$(".box").width())+"px",top:(y-$(".box").height())+"px"}); 
        $(".box").animate({top:"-="+$(".box").position().top/2+"px",opacity:0},1000,function(){
            $(this).remove();
        });
    });
    $("<audio id='audio'></audio>").prop("src","http://antiserver.kuwo.cn/anti.s?rid=MUSIC_13791491&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3").prop({"autoplay":"autoplay"}).appendTo("body");
    $("#audio").on('ended',function(){
        $(this).remove();
    });
    var text="";
    text+=$(".blo_left .browse .browse_2 h1").text();
    $(".blo_left .browse .browse_2 span").each(function(){
        text+=$(this).text();
    });
    text+=$(".blo_left .browse .browse_4").text();
    var url="http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=4&text="+text;
    $("<audio id='audio1'></audio>").prop("src",url).appendTo(".blo_left .browse .bo");
    $(".blo_left .browse .bo").click(function(){
        $("#audio")?$("#audio").remove():"";
        var audio1=$("#audio1")[0];
        if(audio1.paused){
            audio1.play(); //播放
        }else{
            audio1.currentTime=0;
            audio1.pause();/*暂停*/
        }
    });
    var arr=["rgba(3, 101, 100, 1)","rgba(235, 104, 65, 1)","rgba(63, 184, 175, 1)",
                "rgba(254, 67, 101, 1)","rgba(252, 157, 154, 1)","rgba(237, 201, 81, 1)",
                "rgba(200, 200, 169, 1)","rgba(131, 175, 155, 1)","rgba(138, 155, 15, 1)",
                "rgba(50, 153, 187, 1)","rgba(254, 67, 101, 1)","rgba(235, 104, 65, 1)",
                "rgba(237, 201, 81, 1)","rgba(3, 101, 100, 1)","rgba(252, 157, 154, 1)",
            ]
    $(".blo_right .lbox_2 .bo_2 span").each(function(index){
        $(this).css("background",arr[index]);
    })
})