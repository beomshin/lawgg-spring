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
