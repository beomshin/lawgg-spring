function checkId() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    const loginId = document.getElementById('loginId').value;
    $.ajax({
        type: 'GET',
        url: `${contextPath}/check/id`,
        // contentType: '',
        data: {loginId},
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            document.getElementById('loginId').disabled = true;
            document.getElementById('overlapMsg').style.display = 'block';
            document.querySelector('#overlapMsg p').innerHTML = '사용가능한 아이디입니다.'
            document.querySelector('#overlapMsg p').style.color = 'green'
            document.getElementById('overlapBtn').style.display = 'none';
            document.getElementById('retryBtn').style.display = 'block';
            document.getElementById('verifyId').value = true;
        },
        error: function (error) {
            if (error.status === 500) {
                document.getElementById('overlapMsg').style.display = 'block';
                document.querySelector('#overlapMsg p').innerHTML =  error.responseJSON.resultMsg
                document.querySelector('#overlapMsg p').style.color = 'red'
            } else {
                alert('일시적인 문제가 발생되었습니다. 잠시후 다시 시도해주세요.')
            }
        }
    })
}

function retry() {
    document.getElementById('retryBtn').style.display = 'none';
    document.getElementById('overlapMsg').style.display = 'none';
    document.getElementById('overlapBtn').style.display = 'block';
    document.getElementById('loginId').disabled = false;
    document.getElementById('verifyId').value = false;
}
