document.getElementById('chk1').addEventListener('click', function (e) {
    const flag = e.target.checked;
    e.target.checked = flag;
    document.getElementById('chk2').checked = flag;
    document.getElementById('chk3').checked = flag;
})

document.getElementById('chk2').addEventListener('click', function (e) {
    const flag = e.target.checked;
    if (flag && document.getElementById('chk3').checked) {
        document.getElementById('chk1').checked = true;
    } else {
        document.getElementById('chk1').checked = false;
    }
})

document.getElementById('chk3').addEventListener('click', function (e) {
    const flag = e.target.checked;
    if (flag && document.getElementById('chk3').checked) {
        document.getElementById('chk1').checked = true;
    } else {
        document.getElementById('chk1').checked = false;
    }
})

document.getElementById('agree').addEventListener('click', function () {
    const flag1 = document.getElementById('chk1').checked;
    const flag2 = document.getElementById('chk2').checked;
    const flag3 = document.getElementById('chk3').checked;
    if (flag1 && flag2 && flag3) {
        document.getElementById('accept').value = true;
        document.getElementById('register').submit();
    } else {
        alert('약관에 동의해주세요.')
    }
})