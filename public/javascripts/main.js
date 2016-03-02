$(function () {
        $(".poga").click(function(){
            $.get("clients/showAll", function(result){
                $.each(result, function(key, client){
                console.log(client);
                    $(".dati").append(client.name + " " + client.discount + " <br> ");

                });
            });
        });
    });