// 모바일 사이드 오픈
document.getElementById('sideBarOpen').addEventListener('click', function () {
    open()
})

// 모바일 사이드바 종료 (배경화면 클릭)
document.getElementById('headerSideBgMobile').addEventListener('click', function () {
    close()
})

// 모바일 사이드바 종료 (종료 버튼 클릭)
document.getElementById('sideBarClose').addEventListener('click', function () {
    close()
})

// 알림창 클릭 드롭 다운
if (document.getElementById('alert')) {
    document.getElementById('alert').addEventListener('click', function () {
        dropDownOff('header-myinfo-dropdown')
        dropDownToggle('header-message-dropdown')
    })
}

// 나의 프로필 클릭 드롭 다운
if (document.getElementById("myInfo")) {
    document.getElementById('myInfo').addEventListener('click', function () {
        dropDownOff('header-message-dropdown')
        dropDownToggle('header-myinfo-dropdown')
    })
}

function dropDownToggle(className) {
    const dropdown = document.getElementsByClassName(className)[0];
    if (dropdown.style.display === 'block') {
        dropdown.style.display = 'none';  // 현재 보이는 상태이면 숨김
    } else {
        dropdown.style.display = 'block'; // 현재 숨겨진 상태이면 보이게 함
    }
}

function dropDownOff(className) {
    const dropdown = document.getElementsByClassName(className)[0];
    dropdown.style.display = 'none';  // 현재 보이는 상태이면 숨김
}

function open() {
    document.getElementById("headerSideMobile").style.left = "0";
    document.getElementById("headerSideBgMobile").style.display = "block";
    document.body.style.overflow = "hidden";
}

function close() {
    document.getElementById("headerSideMobile").style.left = "-305px";
    document.getElementById("headerSideBgMobile").style.display = "none";
    document.body.style.overflow = "visible";
}

$(document).ready(function () {
    document.querySelectorAll('.header a').forEach(function (link) {
        // 링크의 href 경로 가져오기
        var linkPath = link.getAttribute('href');

        if (linkPath === window.location.pathname) { // 현재 경로와 링크 경로가 동일하면 클릭 이벤트를 막음
            link.addEventListener('click', function (event) {
                event.preventDefault();  // 링크 동작을 차단
            });
        }
    });
})

function search() {
    let form = document.getElementById("searchTrialMobile");
    form.submit();
}
function search2() {
    let form = document.getElementById("searchTrial");
    form.submit();
}