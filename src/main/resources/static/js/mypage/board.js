$(document).ready(function () {

    document.getElementById('submitButton').addEventListener('click', function() { // 검색하기
        document.getElementById('page').value = 0;
        document.getElementById('searchForm').submit();
    });

})