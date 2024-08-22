let filesList = []; // 파일을 저장할 배열
const contextPath = $('#contextPathHolder').attr('data-contextPath');// 현재 URL이 '/'인지 확인하는 스크립트

function uploadFile(inputElement, fileContainer) {
    const newFiles = Array.from(inputElement.files); // 새로 추가된 파일들
    filesList = filesList.concat(newFiles); // 새로운 파일들을 배열에 추가
    inputElement.value = ''; // 파일 input 초기화 (같은 파일을 다시 선택할 수 있도록)
    reloadFiles(fileContainer); // 첨부파일 UI 다시그리기
}

function removeFile(index, fileContainer) { // 파일 삭제
    filesList.splice(index, 1); // 해당 인덱스에서 파일 제거
    reloadFiles(fileContainer); // 첨부파일 UI 다시그리기
}

function reloadFiles(fileContainer) { // 첨부파일 UI 다시그리기
    fileContainer.innerHTML = ''; // 첨부파일 UI 초기화
    filesList.forEach((file, index) => {
        const li = document.createElement('li'); // li 요소 생성
        li.appendChild(getImgIcon(file.name)); // 이미지 아이콘 추가
        li.appendChild(document.createTextNode(' ' + file.name)); // 파일명 텍스트 추가
        li.appendChild(getRemoveButton(index, li, fileContainer)); // 삭제 버튼 추가
        fileContainer.appendChild(li); // 컨테이너에 li 요소 추가
    });
}

function getImgIcon(name) { // 첨부파일 아이콘 추가
    const img = document.createElement('img');
    let ext = 'jpg';
    if (name.includes("jpg")) ext = 'jpg'
    else if (name.includes("png")) ext = 'png'
    else if (name.includes("xls")) ext = 'xls'
    else if (name.includes("hwp")) ext = 'hwp'
    img.src = `${contextPath}/static/images/fileIcn/${ext}.gif`
    img.alt = '';
    img.title = '';
    return img;
}

function getRemoveButton(index, li, fileContainer) { // 첨부파일 삭제 버튼 추가
    const button = document.createElement('button'); // 삭제 버튼 추가
    button.innerHTML = '<i class="uil uil-times"></i>';
    button.onclick = function() {
        fileContainer.removeChild(li);
        removeFile(index, fileContainer); // 배열에서 파일 제거
    };
    return button;
}