function handleFileUpload(event) {
    const fileInput = event.target;
    const fileList = document.getElementById('fileList');

    // 파일 정보 가져오기
    const file = fileInput.files[0];

    if (file) {

        fileList.innerHTML = '';

        // 새 리스트 아이템 생성
        const listItem = document.createElement('li');
        listItem.innerHTML = `
                <img src="${contextPath}/static/images/fileIcn/jpg.gif" alt="" title="" /> ${file.name}
                <button type="button" onclick="removeFile(this)">
                    <i class="uil uil-times"></i>
                </button>
            `;

        // 리스트에 아이템 추가
        fileList.appendChild(listItem);
    }
}

function removeFile(button) {
    // 리스트 아이템 삭제
    const listItem = button.parentElement;
    listItem.remove();

    const fileInput = document.getElementById('videoFileInput');
    fileInput.value = "";
}

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    const form = this;

    const selectedValue = document.querySelector('input[name="opt"]:checked').value;
    if (selectedValue === 'off') {
        alert('약관을 동의해주세요.')
        return;
    }

    form.submit(); // 제출
});