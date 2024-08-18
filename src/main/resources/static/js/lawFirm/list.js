$(document).ready(function () {

    document.getElementById('submitButton').addEventListener('click', function() { // 검색하기
        document.getElementById('page').value = 0;
        document.getElementById('searchForm').submit();
    });

})

function changeTopic(e, topic) { // 토빅 변경 (전체, 핫포지션)
    if (e.target.classList.contains('active')){
        return
    }
    document.getElementById('keyword').value = null;
    document.getElementById('page').value = 0;
    document.getElementById('topic').value = topic;
    document.getElementById('searchForm').submit();
}

function apply() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/law-firm/apply`,
        contentType: 'application/json',
        data: JSON.stringify({lawfirmId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('로펌 지원에 성공하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 403) {
                alert('로그인을 해주세요.')
            } else if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}

function cancelApply() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/law-firm/cancel/apply`,
        contentType: 'application/json',
        data: JSON.stringify({lawfirmId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('로펌 지원 취소를 성공하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 403) {
                alert('로그인을 해주세요.')
            } else if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}

function quit() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/law-firm/quit`,
        contentType: 'application/json',
        data: JSON.stringify({lawfirmId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('로펌 탈퇴하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 403) {
                alert('로그인을 해주세요.')
            } else if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}