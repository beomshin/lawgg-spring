function sendEmail() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    const email = document.getElementById('email').value
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailRegex.test(email)) {
        alert('이메일을 알맞게 입력해주세요.')
        return;
    }

    $.ajax({
        type: 'POST',
        url: `${contextPath}/send/email `,
        contentType: 'application/json',
        data: JSON.stringify({email}),
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('인증번호를 전송하였습니다. 이메일을 확인해주세요.')
            document.getElementById('txId').value = response.txId;
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