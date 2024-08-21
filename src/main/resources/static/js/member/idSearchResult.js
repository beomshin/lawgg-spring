document.getElementById("resetPasswordBtn").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 동작 방지

    // 선택된 라디오 버튼의 값을 가져옴
    const selectedLoginId = document.querySelector('input[name="pwRadio"]:checked').value;

    // 쿼리스트링으로 값 추가하여 이동
    const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트

    window.location.replace(contextPath + `/pw/search?loginId=${encodeURIComponent(selectedLoginId)}`);
});