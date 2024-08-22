function checkId() {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    const loginId = document.getElementById('loginId').value;
    const idRegex = /^[A-Za-z\d]{6,20}$/;
    if (!idRegex.test(loginId)) {
        alert('아이디를 알맞게 입력해주세요.')
        return;
    }

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
            document.getElementById('loginId').readOnly = true;
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
    document.getElementById('loginId').readOnly = false;
    document.getElementById('verifyId').value = false;
}

function matchPassword() {
    const password = document.getElementById('password').value;
    const rePassword = document.getElementById('rePassword').value;
    if (!password || !rePassword) {
        document.getElementById('matchMsg').style.display = 'none';
    } else if (password === rePassword) {
        document.getElementById('matchMsg').style.display = 'block';
        document.querySelector('#matchMsg p').innerHTML = '비밀번호가 일치합니다.'
        document.querySelector('#matchMsg p').style.color = 'green'
    } else {
        document.getElementById('matchMsg').style.display = 'block';
        document.querySelector('#matchMsg p').innerHTML = '비밀번호가 일치하지않습니다.'
        document.querySelector('#matchMsg p').style.color = 'red'
    }
}

document.getElementById('enrollForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    const loginId = document.getElementsByName("loginId")[0].value;
    const verifyId = document.getElementsByName("verifyId")[0].value;
    const password = document.getElementsByName("password")[0].value;
    const rePassword = document.getElementsByName("rePassword")[0].value;
    const nickName = document.getElementsByName("nickName")[0].value;
    const email = document.getElementsByName("email")[0].value;

    const idRegex = /^[A-Za-z\d]{6,20}$/;
    const pwRegex = /^(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,15}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!idRegex.test(loginId)) {
        alert('아이디를 알맞게 입력해주세요.')
        return;
    } else if (verifyId === 'false') {
        alert('아이디 중복검사를 진행해주세요.')
        return;
    } else if (!pwRegex.test(password)) {
        alert('비밀번호를 알맞게 입력해주세요.')
        return;
    } else if (password !== rePassword) {
        alert('입력한 비밀번호가 일치하지 않습니다.')
        return;
    } else if (!nickName || nickName.length > 10) {
        alert('닉네임을 알맞게 입력해주세요.')
        return;
    } else if (!emailRegex.test(email)) {
        alert('이메일을 알맞게 입력해주세요.')
        return;
    }

    const form = this;
    form.submit(); // 제출
});