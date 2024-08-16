

function recommend() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/trial/recommend`,
        contentType: 'application/json',
        data: JSON.stringify({trialId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('추천 성공하였습니다.')
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

function report() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/trial/report`,
        contentType: 'application/json',
        data: JSON.stringify({trialId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('신고 완료하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}


function start() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/trial/start`,
        contentType: 'application/json',
        data: JSON.stringify({trialId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('트라이얼 재판을 시작하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}

function end() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/trial/end`,
        contentType: 'application/json',
        data: JSON.stringify({trialId}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('트라이얼 재판을 종료하였습니다.')
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}


function vote(precedent) {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: 'POST',
        url: `${contextPath}/trial/vote`,
        contentType: 'application/json',
        data: JSON.stringify({trialId, precedent}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            if (precedent === 0) {
                alert('원고를 투표하였습니다.')
            } else if (precedent === 1) {
                alert('피고를 투표하였습니다.')
            }
            window.location.reload()
        },
        error: function (error) {
            if (error.status === 500) {
                alert(error.responseJSON.resultMsg)
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}