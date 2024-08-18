function handleFileSelect(e) {
    const fileContainer = document.querySelector('.bw-file');
    uploadFile(e, fileContainer);
}

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    const form = this;
    const dataTranster = new DataTransfer();
    Array.from(filesList).forEach(file => { dataTranster.items.add(file); }); // 파일리스트 생성
    document.querySelector('#files').files = dataTranster.files; // 파일 저장

    form.submit(); // 제출
});