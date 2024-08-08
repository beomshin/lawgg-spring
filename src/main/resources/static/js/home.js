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