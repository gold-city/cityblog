/*回复评论并刷新*/

/*问题回复*/
function postReply() {
    var questionId = $("#questionId").val();
    var description = $("#description").val();
    comment(questionId, 1, description);
}

/*回复功能封装*/
function comment(targeId, type, content) {
    if (questionId == null || questionId == "" || description == null || description == "") {
        alert("回复内容不能为空或问题不存在！")
    } else {
        $.ajax({
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                "parentId": targeId,
                "content": content,
                "type": type
            }),
            success: function (data) {
                if (data.code == 200) {
                    window.location.reload();
                } else if (data.code == 2002) {
                    var b = confirm(data.message);
                    if (b) {
                        window.open("https://github.com/login/oauth/authorize?client_id=853b30b562f5685f5390&redirect_uri=http://localhost/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message);
                }
            },
            dataType: "json"
        });
    }
}

/*评论回复*/
function commentReply(commentId) {
    var content1 = $("#commentText" + commentId).val();
    comment(commentId, 2, content1);
}

/*展开二级评论*/
function offTwo(e) {
    var activeAttrib = e.getAttribute("data-id");
    var onn = e.getAttribute("onn");
    if (onn == "t") {
        $('#' + activeAttrib).css("background-color", "#499ef3");
        $('#' + activeAttrib).attr("onn", "f");
        $('#comment-' + activeAttrib).addClass("in");
        //展示数据
        //上级容器追加评论
        $.getJSON("/comment/" + activeAttrib, function (data) {
            var c = data.data.length;
            if (c==0){
                $('#commentboby-' + activeAttrib).append(
                    '<span>该评论暂无评论！</span>'
                )
            }
            for (var i = 0; i < c; i++) {
                var data1 = data.data[i];
                var commentData;
                var data2 = new Date(data1.gmtCreate);
                var ss = data2.getMonth() + 1;
                commentData = data2.getFullYear() + '-' + ss + '-' + data2.getDate() + '   ' + data2.getHours() + ':' + data2.getMinutes();
                $('#commentboby-' + activeAttrib).append(
                    '<div class="media" style="border-bottom: 1px solid #DFE6E9;padding: 15px 0px;margin-bottom: 10px">'
                    + '<div class="media-left media-middle" style="vertical-align:text-top;">'
                    + '<img class="media-object" style="width: 50px; height: 50px" src="' + data1.user.avatarUrl + '">'
                    + '</div>'
                    + '<div class="media-body" style="font-size: 20px;width: 100%;">'
                    + '<h5 class="media-heading"><span style="color: #0984e3">' + data1.user.name + '</span> · <span style="color: #636E72">' + commentData + '</span></h5>'
                    + '<span style="white-space:normal; word-break:break-all;overflow:hidden;"'
                    + '>' + data1.content + '</span>'
                    + '</div>'
                    + '</div>'
                )
            }
        });
    } else {
        $('#' + activeAttrib).css("background-color", "");
        $('#' + activeAttrib).attr("onn", "t");
        $('#commentboby-' + activeAttrib).children().remove();
        $('#comment-' + activeAttrib).removeClass("in");
    }
}
function toTag(val) {
    var attribute = val.getAttribute("data-tag");
    var tag = $("#tag").val();
    if (tag.indexOf(attribute)==-1) {
        if (tag){
            $("#tag").val(tag+','+attribute);
        }else{
            $("#tag").val(attribute);
        }
    }
}
function offTag() {
    $("#select-tag").show();
}