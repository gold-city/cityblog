$(function(){
    $(".qq-icon").hover(function(){
        $(".qq-icon").css("background-position","0px -74px");
    },function(){
        $(".qq-icon").css("background-position","0px 0px");
    });
    $(".wx-icon").hover(function(){
        $(".wx-icon").css("background-position","-74px -74px");
    },function(){
        $(".wx-icon").css("background-position","-74px 0px");
    });
    $(".wb-icon").hover(function(){
        $(".wb-icon").css("background-position","-148px -74px");
    },function(){
        $(".wb-icon").css("background-position","-148px 0px");
    });
})
