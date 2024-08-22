function handleFileSelect(e) { // 파일 등록
    const fileContainer = document.querySelector('.bw-file');
    uploadFile(e, fileContainer);
}

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    const id = document.getElementsByName("id")[0].value;
    const password = document.getElementsByName("password")[0].value;
    const title = document.getElementsByName("title")[0].value;
    const content = document.getElementsByName("content")[0].value;

    if (!id) {
        alert('아이디를 입력해주세요.')
        return;
    } else if (!password) {
        alert('비밀번호를 입력해주세요.')
        return;
    } else if (!title || title.length < 5) {
        alert('제목을 5자 이상 입력해주세요.')
        return;
    } else if (!content || content.length < 30) {
        alert('내용을 30자 이상 입력해주세요.')
        return;
    }

    const form = this;
    const dataTranster = new DataTransfer();
    Array.from(filesList).forEach(file => { dataTranster.items.add(file); }); // 파일리스트 생성
    document.querySelector('#files').files = dataTranster.files; // 파일 저장

    form.submit(); // 제출
});