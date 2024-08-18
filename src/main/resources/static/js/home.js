
$(document).ready(function () {
    const filterButtons = document.querySelectorAll('.filter-button');
    const swiperSlides = document.querySelectorAll('.swiper-slide');

    function filterSlides(linetype) { // 포지션 게시판 태그 변경에 따른 라인타입 display 처리
        swiperSlides.forEach(slide => {
            if (slide.getAttribute('data-linetype') === linetype || linetype === '') {
                slide.style.display = 'block';
            } else {
                slide.style.display = 'none';
            }
        });
    }

    filterButtons.forEach(button => { // 포지션 게시판 태그 변경
        button.addEventListener('click', () => {
            const linetype = button.getAttribute('data-linetype');

            filterButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            filterSlides(linetype);
        });
    });

    const defaultButton = document.querySelector('.filter-button.active'); // 포지션 게시판 초기 태그 값 조회
    if (defaultButton) {
        const defaultLinetype = defaultButton.getAttribute('data-linetype');
        filterSlides(defaultLinetype); // 초기 세팅
    }

    new Swiper('.swiper', {
        aslidesPerView: 3,
        //centeredSlides: true,
        spaceBetween: '3.3%',
        grabCursor: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true
        },
        breakpoints: {
            300: {
                slidesPerView: 1.5
            },
            480: {
                slidesPerView: 1.5
            },
            768: {
                slidesPerView: 2.5
            },
            992: {
                slidesPerView: 3
            },
            1200: {
                slidesPerView: 3
            }
        }
    })
})
