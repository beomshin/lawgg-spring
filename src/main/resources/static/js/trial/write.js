function handleFileUpload(event) {
    const file = event.target.files[0]; // 파일 정보 가져오기
    const fileList = document.getElementById('fileList');

    if (file) {
        fileList.innerHTML = ''; // 리스트 초기화
        const listItem = document.createElement('li'); // 새 리스트 아이템 생성
        listItem.innerHTML = `
                <img src="${contextPath}/static/images/fileIcn/jpg.gif" alt="" title="" /> ${file.name}
                <button type="button" onclick="removeFile(this)">
                    <i class="uil uil-times"></i>
                </button>
            `;
        fileList.appendChild(listItem); // 리스트에 아이템 추가
    }
}

function removeFile(button) {
    button.parentElement.remove(); // 리스트 아이템 삭제
    document.getElementById('videoFileInput').value = ""; // 등록 비디오 삭제
}

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    const form = this;

    const title = document.getElementsByName("title")[0].value; // 제목
    const plaintiff = document.getElementsByName("plaintiff")[0].value; // 원고명
    const defendant = document.getElementsByName("defendant")[0].value; // 피고명
    const video = document.getElementsByName("video")[0].value; // 비디오파일
    const subheading = document.getElementsByName("subheading")[0].value; // 소제목
    const plaintiffOpinion = document.getElementsByName("plaintiffOpinion")[0].value; // 원고의견
    const defendantOpinion = document.getElementsByName("defendantOpinion")[0].value; // 피고의견
    const content = document.getElementsByName("content")[0].value;
    const selectedValue = document.querySelector('input[name="opt"]:checked').value;

    if (!title || title.length < 5) {
        alert('제목을 5자 이상 입력해주세요.')
        return;
    } else if (!plaintiff) {
        alert('원고명을 입력해주세요.')
        return;
    } else if (!defendant) {
        alert('피고명을 입력해주세요.')
        return;
    } else if (!video) {
        alert('영상을 업로드해주세요.')
        return;
    } else if (!subheading || subheading.length < 5) {
        alert('소제목을 5자 이상 입력해주세요.')
        return;
    } else if (!plaintiffOpinion || plaintiffOpinion.length < 5) {
        alert('원고 의견을 5자 이상 입력해주세요.')
        return;
    } else if (!defendantOpinion || defendantOpinion.length < 5) {
        alert('피고 의견을 5자 이상 입력해주세요.')
        return;
    } else if (!content || content.length < 30) {
        alert('피고 의견을 30자 이상 입력해주세요.')
        return;
    } else if (selectedValue === 'off') {
        alert('약관을 동의해주세요.')
        return;
    }

    form.submit(); // 제출
});