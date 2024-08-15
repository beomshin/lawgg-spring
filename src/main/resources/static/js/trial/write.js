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