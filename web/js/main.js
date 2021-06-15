// load the projects information from a json file
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
                    "<td>" + data[i].language + "</td>"+
                    "<td>" + "<button onclick='edit(this)'>edit</button>" + "</td>"
                    +"</tr>"
            }
            $('#content').html(html)
        }
    });
}

// edit projects (get the row of the clicked button)
function edit(val) {
    var value = $(val).parent().parent().find("td");
    var title = value.eq(0).text();
    var summary = value.eq(1).text();
    var description = value.eq(2).text();
    var type = value.eq(3).text();
    var language = value.eq(4).text();

    window.location.href = "edit.jsf?title="+title+"&summary="+summary+"&description="+description+"&type="+type+"&language="+language;
}