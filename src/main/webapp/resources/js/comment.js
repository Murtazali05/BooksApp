$(document).ready(function() {

    $('.listcomment .vote .like button, .reviews .vote .like button').click(function(){
       var $button = $(this);
       var commentId = this.value; // обрежем id чтобы получить число

       $.get('/comment/like/' + commentId, function( serverResponse ){

           if( serverResponse === 'OK' ) {
               var count = parseInt($button.parent().children("span#count_like").html());
               $button.parent().children("span#count_like").html(count+1);
           } else {
               alert("Error! Вы уже оценивали этот комментарий!");
           }
       });

       return false;
    });

    $('.listcomment .vote .dislike button, .reviews .vote .dislike button').click(function(){
       var $button = $(this);
       var commentId = this.value; // обрежем id чтобы получить число

       $.get('/comment/dislike/' + commentId, function( serverResponse ){
           if( serverResponse === 'OK' ) {
               var count = parseInt($button.parent().children("span#count_dislike").html());
               $button.parent().children("span#count_dislike").html(count+1);
           } else {
               alert("Error! Вы уже оценивали этот комментарий!");
           }
       });

       return false;
    });

    $(".listcomment .like .unauthorized button, .reviews .like .unauthorized button").click(function () {
       alert("Войдите, чтобы оценить рецензию!");
    });

    $('.comment .close, .reviews .close').click(function(){
        var $button = $(this);
        var commentId = this.value; // обрежем id чтобы получить число

        // делаем POST запрос на сервер
        $.get('/deleteComment/' + commentId, function( serverResponse ){
            $button.parent().remove(); // удаляем HTML комментария
        });
    });

    $('form#add-comment').submit(function(event) {
        event.preventDefault();

        var newComment = {};

        newComment["title"] = $("#title").val();
        newComment["content"] = $("#content").val();
        newComment["bookId"] = $("#bookId").val();

        console.log(newComment);

        $.post({
            url: '/saveComment',
            data: JSON.stringify(newComment),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function (commentDTO) {
                console.log("success");

                $(".listcomment").append(`<div>
                                                <div class='comment'>
                                                    <button id='close' class='close' value='${commentDTO.id}'></button>

                                                    <img src='/images/avatar/${commentDTO.user.linkAvatarka}'  alt='alt' />
                                                    <div class='right_head_com'>
                                                        <p><a href='#'>${commentDTO.user.name}</a></p>
                                                        <p class='header_comment'>${commentDTO.title}</p>
                                                    </div>
                                                    <p class='txt_comment'>${commentDTO.content}</p>
                                                    <p class='date'>${commentDTO.createDate}</p>

                                                    <div class='like'>
                                                        <span class='unauthorized'>
                                                            <button id='idc'><i class='fa fa-thumbs-up' aria-hidden='true'></i></button>
                                                            <span id='count_like'>${commentDTO.countLike}</span>
                                                        </span>
                                                        &nbsp;

                                                        <span class='unauthorized'>
                                                            <button id='idcom'><i class='fa fa-thumbs-down' aria-hidden='true'></i></button>
                                                            <span id='count_dislike'>${commentDTO.countDislike}</span>
                                                        </span>
                                                        &nbsp;
                                                    </div>

                                                    <div class='clearfix'></div>
                                                </div>
                                            </div>`);

                console.log(commentDTO);
            },
            error: function (e) {
                console.log("error");
                console.log(e);
            }
        })
    });
});
