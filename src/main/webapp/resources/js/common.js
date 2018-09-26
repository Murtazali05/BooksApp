$(document).ready(function() {

/*
    $(".reviews .green_button").click(function(){
        var $button = $(this);
        var bookId = this.id; // обрежем id чтобы получить число

        $.ajax({
            type: "POST",
            url: "/book/saveComment/" + bookId,
            data: $button.parent().serialize()
        });
		$(".listcomment").html(html);
        return false;
    });
*/
    $(".link .blue").click(function() {
        if ($(".link ul").hasClass("active")){
            $(".link ul").removeClass("active");
        } else {
            $(".link ul").addClass("active");
        }
    });

    $("body").click(function(e) {
        if ($(e.target).closest(".link .blue").length===0)
		$(".link ul").removeClass("active");
    });

	$('.auth_magnific').magnificPopup({
		type: 'inline',
		midClick: true
	});

    $(".content form.registration #check input[type='checkbox']").click(function (){
        if (!$("#check input[type='checkbox']").is(':checked'))
            $('#toRegistorButton').attr('disabled', true);
        else
            $('#toRegistorButton').removeAttr('disabled');
    });

    // Появление кнопки "Наверх" при скролле
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            if ($('.upstairs').is(':hidden')) {
                $('.upstairs').css({opacity : 1}).fadeIn('slow');
            }
        } else { $('.upstairs').stop(true, false).fadeOut('fast'); }
    });

 	//Кнопка "Наверх"
	//Документация:
	//http://api.jquery.com/scrolltop/
	//http://api.jquery.com/animate/
	$(".upstairs").click(function () {
		$("body, html").animate({
			scrollTop: 0
		}, 800);
		return false;
	});

});

// Адаптивные скрипты, которые срабатывают только при определенном разрешении экрана
// Документация: https://github.com/maciej-gurban/responsive-bootstrap-toolkit
// (function($, document, window, viewport) {
// 	function resizeWindow() {
// 		// $("a").click(function() {
// 		// 	if (viewport.is("lg")) {
// 		// 		return false;
// 		// 	};
// 		// });
//     }
//     $(document).ready(function() {
// 		resizeWindow();
// 	});
// 	$(window).bind("resize", function() {
// 		viewport.changed(function(){
// 			resizeWindow();
// 		});
// 	});
// })(jQuery, document, window, ResponsiveBootstrapToolkit);
