function f1(){
    $.ajax({
        url:"../resource/json/projects.json",
        dataType:"json",
        success:function(data){
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr>" +
                    "<td>" + data[i].title + "</td>"+
                    "<td>" + data[i].summary + "</td>"+
                    "<td>" + data[i].description + "</td>"+
                    "<td>" + data[i].type + "</td>"+
                    "<td>" + data[i].language + "</td>"
                    +"</tr>"
            }
            $('#content').html(html)
        }
    });
}
