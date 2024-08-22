function openProfile() {
    document.getElementById('profile').click()
}

function changeProfile(e) {
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    let formData = new FormData();
    formData.append("profile", e.target.files[0]);

    $.ajax({
        type: 'post',
        url: `${contextPath}/my/update/profile`,
        data: formData,
        contentType: false,
        processData: false,
        cache: false,
        xhrFields: {
            withCredentials: true  // 쿠키와 함께 인증 정보를 전송
        },
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            alert('프로필 변경에 성공하였습니다.')
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

document.getElementById('searchForm').addEventListener('submit', function (event){
    event.preventDefault();

    const oldPassword = document.getElementsByName("oldPassword")[0].value;
    const newPassword = document.getElementsByName("newPassword")[0].value;

    const regex = /^(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,15}$/;
    if (!regex.test(oldPassword)) {
        alert('비밀번호를 알맞게 입력해주시기 바랍니다.')
        return;
    } else if (oldPassword !== newPassword) {
        alert('입력한 비밀번호가 일치하지 않습니다.')
         return;
    }
    const form = this;
    form.submit();
})

function matchPassword() {
    const oldPassword = document.getElementsByName("oldPassword")[0].value;
    const newPassword = document.getElementsByName("newPassword")[0].value;
    if (!oldPassword || !newPassword) {
        document.getElementById('matchMsg').style.display = 'none';
    } else if (oldPassword === newPassword) {
        document.getElementById('matchMsg').style.display = 'block';
        document.querySelector('#matchMsg p').innerHTML = '비밀번호가 일치합니다.'
        document.querySelector('#matchMsg p').style.color = 'green'
    } else {
        document.getElementById('matchMsg').style.display = 'block';
        document.querySelector('#matchMsg p').innerHTML = '비밀번호가 일치하지않습니다.'
        document.querySelector('#matchMsg p').style.color = 'red'
    }
}