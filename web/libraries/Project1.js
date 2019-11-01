var Project1 = ( function() {

    return {

        init: function() {
            
            $("#version").html( "jQuery Version: " + $().jquery );

        },
        
        submitSearchForm: function() {

            $.ajax({

                url: 'registration',
                method: 'GET',
                data: $('#searchform').serialize(),

                success: function(response) {

                    $("#resultsarea").html(response);

                }

            });
            
        },
        
        submitRegForm: function() {

            $.ajax({

                url: 'registration',
                method: 'POST',
                data: $('#regform').serialize(),
                dataType: 'json',

                success: function(response) {

                    alert( JSON.stringify(response) );
                    
                    var code = response["code"];
                    var displayname = response["displayname"];
                    
                    // Insert new paragraphs with the code and display name inside

                }

            });
            
        },
        
        updateDisplayName: function() {
            
            var firstname = $('#firstname').val();
            var lastname = $('#lastname').val();
            
            var displayname = firstname + " " + lastname;
            
            $('#displayname').val(displayname);

        }

    };

}());