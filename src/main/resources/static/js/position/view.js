function recommend() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    alert(boardId)
    $.ajax({
        type: 'POST',
        url: `${contextPath}/api/v1/recommend/board`,
        contentType: 'application/json',
        data: JSON.stringify({id: boardId}),
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            console.log(response)
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function report() {
    alert(boardId)
}